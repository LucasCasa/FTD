package ar.edu.itba.TowerDefense.units;

import ar.edu.itba.TowerDefense.back.Logical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Unit implements Logical , Comparable<Unit>{
	private Vector2 pos; 
	
	private float health;
	private Vector2 dim;
	protected float speed;
	protected float fullSpeed;
	private Vector2 mov;
	
	private Vector3[] path;
	private int actpos;
	private int reward;
	private boolean newDirection;
	private float distanceTravelled;
	private float totalHealth;
	private int ID;
	private String name;
	private boolean yellowMark;
	private int finishLine;
	
	private float armor;
	private float actualArmor;
	
	private float freezeDuration;
	private float timeFreezed;
	public Unit(float posx,float posy , float width, float height,float health, float speed,float armor, int reward, int ID,String name){
		this.pos = new Vector2(posx,posy);
		this.dim = new Vector2(width,height);
		this.health = health;
		totalHealth = health;
		this.speed = speed;
		this.fullSpeed = speed;
		this.reward = reward;
		this.ID = ID;
		this.armor = armor;
		actualArmor = armor;
		mov = new Vector2(speed,0);
		this.name = name;
	}
	public void update(){
		caminar();
	}
	public void caminar(){
		distanceTravelled += speed * Gdx.graphics.getDeltaTime();
		if(pos.x < 0 || pos.y > 720){
			distanceTravelled = 0;
		}
		timeFreezed+= Gdx.graphics.getDeltaTime();
		if(timeFreezed >= freezeDuration){
			speed = fullSpeed;
			
		}
		if(pos.x <= path[actpos].x + 5 && pos.x >= path[actpos].x - 5 && pos.y <= path[actpos].y + 5 && pos.y >= path[actpos].y - 5){
			if(actpos < path.length -1){
				actpos++;
			}
			newDirection = true;
		}
		switch( (int) path[actpos].z){
		case 0: // Downwards
			pos.x += 0;
			pos.y -= speed * Gdx.graphics.getDeltaTime();
			mov.set(0, -speed);
		break;
		
		case 1: // Left
			pos.x -= speed * Gdx.graphics.getDeltaTime();
			pos.y += 0;
			mov.set(-speed, 0);
		break;
		
		case 2: // Right
			pos.x += speed * Gdx.graphics.getDeltaTime();
			pos.y += 0;
			mov.set(speed,0);
		break;
		case 3: // Upwards
			pos.x += 0;
			pos.y += speed * Gdx.graphics.getDeltaTime();
			mov.set(0,speed);
		break;
		}
	}
	public void setnodes(int cant, Vector3[] path){
		this.path = path;
		if(path[path.length - 1].x >= 1280 || path[path.length - 1].x <= 0){
		finishLine = (int)path[path.length -1].x;
		}else{
			finishLine = 10000;
		}
		actpos = 0;
		
	}
	public boolean hit(float damage){
		
		health-= damage * actualArmor;
		actualArmor = armor;
		if(health <= 0){
			return true;
		} else{
			return false;
		}
	}
	public int getReward(){
		return reward;
	}
	public float getDistanceTravelled(){
		return distanceTravelled;
	}
	public int getX(){
		return (int) this.pos.x;
	}
	public int getY(){
		return (int) this.pos.y;
	}
	public float getCenterX(){
		return pos.x + dim.x / 2;
	}
	public float getCenterY(){
		return pos.y + dim.y / 2;
	}
	public float getHealth(){
		return this.health;
	}
	public boolean isNewDirection(){
		if(newDirection){
			newDirection = false;
			return true;
		}else{
			return false;
		}
	}
	public int getDirection(){
		return (int)path[actpos].z;
	}
	public Vector2 getSpeed(){
		return mov;
	}
	public int compareTo(Unit other){
		return (int) (other.distanceTravelled - this.distanceTravelled);
	}
	public float getTotalHealth(){
		return totalHealth;
	}
	public int getID(){
		return ID;
	}
	public float getTopY(){
		return pos.y + dim.y;
	}
	public void slowDown(float amount, float time){
		if(this.name != "Yetti"){
		speed *= amount;
		timeFreezed = 0;
		freezeDuration = time;
		}
	}
	public boolean hasWon(){
		if(finishLine <= 0){
			return pos.x <= finishLine || pos.y < -10 ;
		}else{
			return pos.x >= finishLine || pos.y < -10;
		}
	}
	public String getName(){
		return name;
	}
	public boolean getYellowMark(){
		return yellowMark;
	}
	public void setYellowMark(boolean state){
		if(state){
			actualArmor += 0.5f;
		}else{
			actualArmor = armor;
		}
	}
	public float getArmor(){
		return armor;
	}
}
