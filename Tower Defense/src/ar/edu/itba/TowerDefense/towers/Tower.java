package ar.edu.itba.TowerDefense.towers;

import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.back.Logical;
import ar.edu.itba.TowerDefense.back.Money;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.units.UnitArrayList;

import com.badlogic.gdx.math.Vector2;

public abstract class Tower implements Logical {
	public int ID = 0;
	public int VALUE = 50; 
	protected Vector2 pos;
	protected int attackDamage;
	protected int range;
	protected double rdist;
	protected boolean attacking;
	protected double ang;
	public int level = 1;
	protected boolean ultimate;
	protected Vector2 attackPos;
	private int soundID;
	public Vector2 getPos(){
		return pos;
	}
	
	public void setX(int x){
		pos.x = x;
	}
	
	public void setY(int y){
		pos.y = y;
	}

	public void attack(UnitArrayList unit){
	}
	
	public void update(){
		
	}
	/**
	 * 
	 * @param Units x position
	 * @param Units y position
	 * @return If unit is in range or not
	 */
	protected boolean isInRange(int x, int y){
		double aux;
		aux = Math.pow(Math.pow(x - attackPos.x,2) + Math.pow(y - attackPos.y, 2), 0.5);
		if(aux <= this.range){
			return true;
		}else{
			return false;
		}
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
		Tower other = (Tower) obj;
		if (pos == null) {
			if (other.getPos() != null)
				return false;
		} else if (!pos.equals(other.getPos()))
			return false;
		return true;
	}
	
	public void upgrade(){
		attackDamage *= 10;
	}
	
	public boolean isUpgradeable(){
		return !ultimate && Money.getInstance().canSubstract(value() * level);
	}
	
	public int getRange(){
		return range;
	}
	
	public Vector2 getAttackPos(){
		return attackPos;
	}

	public  int value(){
		return 0;
	}
	public int getDamage(){
		return attackDamage;
	}
	public int getLevel(){
		return level;
	}
	public void stopSound(){
		Assets.raySound.stop(soundID);
	}
}
