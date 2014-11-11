package ar.edu.itba.TowerDefense.towers;

import java.util.ArrayList;

import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.back.Connector;
import ar.edu.itba.TowerDefense.back.Logical;
import ar.edu.itba.TowerDefense.back.Money;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.units.UnitArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GreenTower extends Tower implements Logical{
	Array<Sprite> archer;
	private float attackTime;
	private ArrayList<Connector<Proyectile,ProyectileUI>> p;
	boolean attackReady;
	Vector2 archersPos; 
	int archerOrientationX;
	int archerOrientationY;
	int minRange;
	int maxRange;
	int draw[];
	int archerAttacking;
	float cooldown;
	
	public static final int VALUE = 70;
	
	public GreenTower(int x, int y){
		ID = 1;
		range = 200;
		pos = new Vector2(x,y);
		attackPos = new Vector2(pos.x + Main.GRIDSIZE / 2,pos.y + Main.GRIDSIZE / 1.7f); 
		p  = new ArrayList<Connector<Proyectile,ProyectileUI>>();
		archersPos = new Vector2(x + Main.GRIDSIZE / 2, y + 50);

		attackTime = 4;
		attackDamage = 30;
		cooldown = 0.6f;
		level = 1;
		super.ultimate = false;
	}
	public void update(){
		
	}
	public void attack(UnitArrayList unit){
		boolean flag = false;
		attackTime +=Gdx.graphics.getDeltaTime();
		if(attackTime >= cooldown){
			for(int i = 0; i<unit.size() && flag == false ;i++){
					int x = unit.getBack(i).getX();
					int y = unit.getBack(i).getY();
					flag = isInRange(x,y);
					if(flag == true){
						archerAttacking = (archerAttacking * -1) + 1;
						attackTime = 0;
						
						archerOrientationX = (int)archersPos.x - x;
						archerOrientationY = (int)archersPos.y - y;
						attacking = true;

						Proyectile pro = new Proyectile(attackPos.x - archerAttacking * 20, attackPos.y,unit.getBack(i),attackDamage,super.ultimate,true);
						p.add(new Connector<Proyectile,ProyectileUI>(pro,new ProyectileUI(pro,30,9,Assets.arrow)));
						
					}
				}
			}
		if(unit.size() == 0){
			rdist = 0;
		}

		for(int i = 0; i< p.size();i++){
			if(p.get(i).getBack().hasMiss()){
				p.remove(i);
			}else{
				p.get(i).getBack().update();
				if(p.get(i).getBack().getHit() == true){
					if(p.get(i).getBack().getVictim().getHealth() <= 0){
						Money.getInstance().add(p.get(i).getBack().getVictim().getReward());
						unit.remove(p.get(i).getBack().getVictim());
					}
					p.remove(i);
				}
			}
		}
	}
public void upgrade()	{
	switch(super.level){
	case 1:
		cooldown /=2;
		break;
	case 2:
		attackDamage +=5;
		break;
	case 3:
		range +=50;
		break;
	case 4:
		super.ultimate = true;
		break;
	}
	level++;
}
public int getOrientationX(){
	return archerOrientationX;
}
public int getOrientationY(){
	return archerOrientationY;
}
public boolean getAttacking(){
	return attacking;
}
public void setAttacking(boolean a){
	attacking = a;
}
public ArrayList<Connector<Proyectile,ProyectileUI>> getProyectiles(){
	return p;
}
public int getlevel(){
	return 0;
}
public int value(){
	return 70;
}
}
	
