package ar.edu.itba.TowerDefense.towers;

import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.back.Logical;
import ar.edu.itba.TowerDefense.back.Money;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.units.Unit;
import ar.edu.itba.TowerDefense.units.UnitArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PurpleTower extends Tower implements Logical{
	private boolean  chargingAttack = false;
	public static final int VALUE = 120;
	private Vector2 attack = new Vector2(0,0);
	private Vector2 attack2 = new Vector2(0,0);
	private double ang2;
	private double rdist2;
	boolean attacking2 = false;
	private Vector2 rayStart;
	private float chargingTime = 1f;
	public PurpleTower(int posx, int posy){
		ID = 0;
		attackDamage = 100;
		range = 100;
		pos = new Vector2(posx,posy);
		rayStart = new Vector2(posx+65 *((float)Main.GRIDSIZE / Assets.MAGICW), posy + 80 * ((float) Main.GRIDSIZE / Assets.MAGICW));
		attackPos = new Vector2(posx + Main.GRIDSIZE / 2, posy + Main.GRIDSIZE / 2);
	}
	public void update(){
	}
	public void attack(UnitArrayList unit){
		boolean attack1 = false,attack2 = true;
		if(ultimate){
			attack2 = false;
		}
		for(int i = 0; i<unit.size() && (attack1 == false || attack2 == false);i++){
			int x = (int) unit.getBack(i).getCenterX();
			int y = (int) unit.getBack(i).getCenterY() -10;
			boolean lookingToAttack2 = false;
			if(attack1 == false){
				attack1 = isInRange(x,y);
			} else { 
				attack2 = isInRange(x,y);
				lookingToAttack2 = true;
			}
			
			if(attack1 == true && (attack2 == false|| ultimate == false) && lookingToAttack2 == false){
				chargingAttack = true;
				attack.x = x;
				attack.y = y;
				rdist = Math.pow(Math.pow(attack.x - rayStart.x ,2) + Math.pow(attack.y - rayStart.y,2), 0.5);
				float deltaX = x - rayStart.x;
				float deltaY = y - rayStart.y;
				ang = Math.atan2(deltaY , deltaX) * 180 / Math.PI;
				if(attacking){
					if(damage(unit.getBack(i))){
						Money.getInstance().add(unit.getBack(i).getReward());
						unit.remove(i);
					}
				}
			}else if(attack2 == true && ultimate == true){
				attacking2 = true;
				this.attack2.x = x;
				this.attack2.y = y;
				rdist2 = Math.pow(Math.pow(this.attack2.x - rayStart.x ,2) + Math.pow(this.attack2.y - rayStart.y,2), 0.5);
				float deltaX2 = x - rayStart.x;
				float deltaY2 = y - rayStart.y;
				ang2 = Math.atan2(deltaY2 , deltaX2) * 180 / Math.PI;
				
				if(attacking){
					System.out.println(attack2 + " Unit Attacking: " + i);
					if(damage(unit.getBack(i))){
						Money.getInstance().add(unit.getBack(i).getReward());
						unit.remove(i);
					}
				}
			}
		}
		if(attack1 == false){
			attacking = false;
			chargingAttack = false;
		}
		if(attack2 == false){
			attacking2 = false;
		}
		if(unit.size() == 0){
			rdist = 0;
			rdist2 = 0;
		}
		
	}
	public Vector2 getRayStart(){
		return rayStart;
	}
	public boolean getAttacking(){
		return attacking;
	}
	public boolean getAttacking2(){
		return attacking2;
	}
	public boolean getUltimate(){
		return ultimate;
	}
	public void setAttacking(boolean a){
		attacking = a;
	}
	public boolean getChargingAttack(){
		return chargingAttack;
	}
	public Vector2 getAttack1(){
		return attack;
	}
	public Vector2 getAttack2(){
		return attack2;
	}
	public double getDistanceToTarget1(){
		return rdist;
	}
	public double getDistanceToTarget2(){
		return rdist2;
	}
	public double getAngleToTarget1(){
		return ang;
	}
	public double getAngleToTarget2(){
		return ang2;
	}
	public boolean damage(Unit u){
		u.hit(attackDamage * Gdx.graphics.getDeltaTime());
		if(u.getHealth() < 0)
			return true;
		else
			return false;
	}
	public float getChargingTime(){
		return chargingTime;
	}
	public void upgrade(){
		switch(super.level){
		case 1:
			chargingTime = 0.5f;
			break;
		case 2:
			attackDamage +=50;
			break;
		case 3:
			range +=50;
			break;
		case 4:
			super.ultimate = true;
			attackDamage = 100;
			break;
		}
		level++;
		
	}
	public int hashCode() {
		int result = 1;
		result = (int) (pos.x/ Main.GRIDSIZE * 100 + pos.y / Main.GRIDSIZE);
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurpleTower other = (PurpleTower) obj;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		return true;
	}
	public int value(){
		return 120;
	}
}
