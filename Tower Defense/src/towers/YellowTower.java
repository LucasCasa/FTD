package towers;

import java.util.ArrayList;

import units.Unit;
import ar.edu.itba.TowerDefense.Assets;
import ar.edu.itba.TowerDefense.Connector;
import ar.edu.itba.TowerDefense.Logical;
import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.Money;

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
	 * Maneja el ataque de la torre
	 */
	public void attack(ArrayList<Unit> unit){
		int unitsHit = 0;
		boolean flag = false;
		boolean someone = false;
		attackTime +=Gdx.graphics.getDeltaTime();
		if(attackTime >= 2){
			attacking = true;
				for(int i = 0; i<unit.size() ;i++){
					int x = unit.get(i).getX();
					int y = unit.get(i).getY();
					flag = isInRange(x,y);
					someone = (someone || flag);
					if(flag == true && attackReady &&  unitsHit < maxHits){
						attackTime = 0;
						attacking = false;
						unitsHit++; 
						Proyectile pro = new Proyectile(attackPos.x, attackPos.y,unit.get(i),attackDamage,false,false);
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
					Money.getInstance().add(unit.get(i).getReward());
					unit.remove(p.get(i).getBack().getVictim());
				}else if(ultimate){
					p.get(i).getBack().getVictim().setYellowMark(true);
					//p.get(i).getBack().getVictim().slowDown(0.5f,1);
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
			System.out.println("LVL2");
			break;
		case 2:
			maxHits = 15;
			System.out.println("LVL3");
			break;
		case 3:
			range +=40;
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
}
