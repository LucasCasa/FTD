package towers;

import java.util.ArrayList;

import towers.Proyectile;
import towers.ProyectileUI;
import towers.Tower;
import units.Unit;

import ar.edu.itba.TowerDefense.Assets;
import ar.edu.itba.TowerDefense.Connector;
import ar.edu.itba.TowerDefense.InterfaceManager;
import ar.edu.itba.TowerDefense.Logical;
import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.Money;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import effects.MovingEffect;

public class BlueTower extends Tower implements Logical{
	private ArrayList<Connector<Proyectile,ProyectileUI>> p;
	private float attackTime;
	boolean attackReady;
	boolean victimBelow;
	Vector2 drawsMage;
	int targetx = 0;
	int targety = 0;
	public static final int VALUE = 90; 
	private float freezeDuration = 0.5f;
	private float freezeIntensity = 0.5f;
	
	public BlueTower(int x, int y){
		ID = 3;
		attackReady = false;
		attackDamage = 40;
		range = 150;
		pos = new Vector2(x,y);
		attackPos = new Vector2(pos.x + Main.GRIDSIZE / 2,pos.y + Main.GRIDSIZE / 2); 
		p  = new ArrayList<Connector<Proyectile,ProyectileUI>>();
		attacking = false;
	}
	
	
	public void attack(ArrayList<Unit> unit){
		boolean flag = false;
		attackTime +=Gdx.graphics.getDeltaTime();
		if(attackTime >= 1f){
			attacking = true;

			for(int i = 0; i<unit.size() && !flag ;i++){
				targetx = unit.get(i).getX();
				targety = unit.get(i).getY();
				flag = isInRange(targetx,targety);
				if(flag == true && attackReady){
					attackTime = 0f;
					victimBelow = (pos.y >= targety);
					attackReady = false;
					Proyectile pro = new Proyectile(pos.x + Main.GRIDSIZE / 2, pos.y + Main.GRIDSIZE / 2,unit.get(i),attackDamage,false,false); 
					p.add(new Connector<Proyectile,ProyectileUI>(pro,new ProyectileUI(pro,20,20,Assets.frostattack)));
				}
			}
			if(flag == false){
				attacking = false;
			}
		}
		if(unit.size() == 0){
			rdist = 0;
		}

		for(int i = 0; i< p.size();i++){
			p.get(i).getBack().update();
			if(p.get(i).getBack().getHit() == true){
				if(ultimate){
					InterfaceManager.e.add(new MovingEffect(p.get(i).getBack().getVictim(),Assets.iceFullEffect,true,freezeDuration));
					p.get(i).getBack().getVictim().slowDown(0,freezeDuration);
				}else{
					InterfaceManager.e.add(new MovingEffect(p.get(i).getBack().getVictim(),Assets.iceEffect,true,freezeDuration / 2));
					p.get(i).getBack().getVictim().slowDown(freezeIntensity,freezeDuration);
				}
				if(p.get(i).getBack().getVictim().getHealth() <= 0){
					Money.getInstance().add(unit.get(i).getReward());
					unit.remove(p.get(i).getBack().getVictim());
				}
				p.remove(i);
			}else if(!unit.contains(p.get(i).getBack().getVictim())){
				p.remove(i);
			}
		}
	}
		
	public ArrayList<Connector<Proyectile,ProyectileUI>> getProyectiles(){
		return p;
	}
	public void setAttackReady(boolean b){
		attackReady = b;
	}
	public void setAttacking(boolean b){
		attacking = b;
	}
	public boolean getAttacking(){
		return attacking;
	}
	public boolean getVictimBelow(){
		return victimBelow;
	}
	public void upgrade(){
		switch(super.level){
		case 1:
			range +=50;
			System.out.println("LVL2");
			break;
		case 2:
			freezeDuration = 0.7f ;
			System.out.println("LVL3");
			break;
		case 3:
			freezeDuration = 1f;
			System.out.println("LVL4");
			break;
		case 4:
			super.ultimate = true;
			System.out.println("LVLMAX");
			break;
		default:
			System.out.println("MAX LEVEL REACHED");
		}
		level++;
	}
	public int value(){
		return 90;
	}
}
