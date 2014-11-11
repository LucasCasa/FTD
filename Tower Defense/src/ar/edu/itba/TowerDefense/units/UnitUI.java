package ar.edu.itba.TowerDefense.units;


import ar.edu.itba.TowerDefense.effects.ParticleMovingEffect;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.front.Drawable;
import ar.edu.itba.TowerDefense.front.InterfaceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class UnitUI implements Drawable,Comparable<UnitUI>{
	Texture texture;
	Texture deathTexture;
	SpriteBatch batch;
	
	float posx;
	float posy;
	float[] width;
	float[] height;
	float[] animcant;
	int[] drawPosY;
	int[] drawPosX;
	float animvel;
	float pause;
	
	int direction;
	boolean changeDirection;
	float totalHealth;
	float actualHealth;
	
	int drawx;
	int drawy;
	
	Unit unit;
	private boolean flipped;
	private boolean alreadyMarked;
	
	public UnitUI( Unit unit,Texture texture,Texture deathTexture, float posx, float posy, float[] width, float[] height, int drawPosX[], int[] drawPosY ,float[] animcant, float animvel, float totalHealth){
		this.batch = InterfaceManager.getInstance().batch;
		this.unit = unit;
		this.texture = texture;
		this.deathTexture = deathTexture;
		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
		this.drawPosX = drawPosX;
		this.drawPosY = drawPosY;
		this.animcant = animcant;
		this.animvel = animvel;
		this.totalHealth = totalHealth;
		flipped = false;
		direction = unit.getDirection();
	}
	public void setNewDirection(){
		if(unit.isNewDirection()){
			direction = unit.getDirection();
			changeDirection = true;
			if(direction == 1){
				flipped = true;
			}else{
				flipped = false;
			}
		}
		
	}
	public void draw(){
		if(unit.getHealth() > 0){
			if(unit.getYellowMark() && !alreadyMarked){
				alreadyMarked = true;
				InterfaceManager.e.add(new ParticleMovingEffect(Assets.yellowEffect,unit,4));
			}else if(!unit.getYellowMark()){
				alreadyMarked = false;
			}
			setNewDirection();
			if(changeDirection){
				drawx = 0;
				changeDirection = false;
			}
			pause+= Gdx.graphics.getDeltaTime();
		
			if(pause  * (unit.speed / unit.fullSpeed) >= animvel){
				pause = 0;
			
				drawy = drawPosY[direction];
				drawx += drawPosX[direction];
			
				if(drawx >= drawPosX[direction] * animcant[direction])
					drawx = 0;
			
			}
			posx = unit.getX();
			posy = unit.getY();
			actualHealth = unit.getHealth();
		
			InterfaceManager.getInstance().batch.draw(texture,(int) posx,(int) posy,width[direction] ,height[direction] ,drawx, drawy,(int)width[direction],(int)height[direction],flipped,false);
			batch.draw(Assets.healthb,posx,posy + 48,totalHealth *(20 / totalHealth),5,0,0,(int) (100 *(48 / 100f)),5,false,false);
			batch.draw(Assets.health,posx,posy + 48,actualHealth * (20 / totalHealth),5,0,0,(int) (actualHealth *(48 / 100f)),5,false,false);
		}	
	}
	public Texture getTexture(){
		return texture;
	}
	public int getDeathWidth(){
		return (int) width[4];	
	}
	public int getDeathHeight(){
		return (int)height[4];
	}
	public int getDeathX(){
		return (int)drawPosX[4];
	}
	public int getDeathY(){
		return drawPosY[4];
	}
	public int getDeathAnimCant(){
		return (int)animcant[4];
	}
	public Texture getDeathTexture(){
		return deathTexture;
	}
	public Unit getUnit(){
		return unit;
	}
	@Override
	public int compareTo(UnitUI other) {
		return (int) (other.getUnit().getDistanceTravelled() - this.getUnit().getDistanceTravelled());
	}
	
}
