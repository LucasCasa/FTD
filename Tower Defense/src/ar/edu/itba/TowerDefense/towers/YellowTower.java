package ar.edu.itba.TowerDefense.towers;

import java.util.ArrayList;

import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.back.Connector;
import ar.edu.itba.TowerDefense.back.Logical;
import ar.edu.itba.TowerDefense.back.Money;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.units.UnitArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class YellowTower extends Tower implements Logical{
	private float attackTime;
	private ArrayList<Connector<Proyectile,ProyectileUI>> p;
	boolean attackReady;
	private int maxHits = 5;
	public static final int VALUE = 100;
	public YellowTower(int x, int y){
		ID = 2;
		attackDamage = 25;
		range =150;
		pos = new Vector2(x,y);
		attackPos = new Vector2(pos.x + Main.GRIDSIZE / 2,pos.y + Main.GRIDSIZE / 2); 
		p  = new ArrayList<Connector<Proyectile,ProyectileUI>>();
		attacking = false;
	}
	public void update(){
		
	}
	/**
	 * Manages the tower's attack
	 */
	public void attack(UnitArrayList unit){
		int unitsHit = 0;
		boolean flag = false;
		boolean someone = false;
		attackTime +=Gdx.graphics.getDeltaTime();
		if(attackTime >= 1f){
			attacking = true;
				for(int i = 0; i<unit.size() ;i++){
					int x = unit.getBack(i).getX();
					int y = unit.getBack(i).getY();
					flag = isInRange(x,y);
					someone = (someone || flag);
					if(flag == true && attackReady &&  unitsHit < maxHits){
						attackTime = 0;
						attacking = false;
						unitsHit++; 
						Proyectile pro = new Proyectile(attackPos.x, attackPos.y,unit.getBack(i),attackDamage,false,false);
						ProyectileUI proUI = new ProyectileUI(pro,20,20,Assets.magicAtackY);
						p.add(new Connector<Proyectile,ProyectileUI>(pro,proUI));
					}
				}
				if(attackReady){
					attackReady = false;
				}
				if(!someone){
					attacking = false;
				}
		}
		if(unit.size() == 0){
			rdist = 0;
		}

		for(int i = 0; i< p.size();i++){
			p.get(i).getBack().update();
			if(p.get(i).getBack().getHit() == true){
				if(p.get(i).getBack().getVictim().getHealth() <= 0){
					Money.getInstance().add(unit.getBack(i).getReward());
					unit.remove(p.get(i).getBack().getVictim());
				}else if(ultimate){
					p.get(i).getBack().getVictim().setYellowMark(true);
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
	public int value(){
		return 100;
	}
	public void upgrade(){
		switch(super.level){
		case 1:
			attackDamage+= 10;
			break;
		case 2:
			maxHits = 15;
			break;
		case 3:
			range +=40;
			break;
		case 4:
			super.ultimate = true;
			break;
		}
		level++;
	}
}
