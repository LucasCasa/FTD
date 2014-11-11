package ar.edu.itba.TowerDefense.effects;


import ar.edu.itba.TowerDefense.front.InterfaceManager;
import ar.edu.itba.TowerDefense.units.Unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParticleMovingEffect implements Effect {
	private SpriteBatch batch;
	private Unit victim;
	private int width;
	private int height;
	private Texture texture;
	private boolean dispose;
	private int animcant;
	private float drawTime;
	private int drawPosX;
	
	public ParticleMovingEffect(Texture t, Unit u, int animcant){
		this.texture = t;
		this.victim = u;
		this.width = t.getWidth() / animcant;
		this.height = t.getHeight();
		this.animcant = animcant;
		batch = InterfaceManager.getInstance().batch;
	}
	
	public void draw() {
		drawTime+= Gdx.graphics.getDeltaTime();
		
		if(drawTime >= 0.1f){
			drawTime = 0;
			drawPosX+=width;
			if(drawPosX == width * animcant){
				drawPosX = 0;
			}	
		}
		batch.draw(texture, victim.getCenterX() - width / 2 , victim.getCenterY() - height / 2, drawPosX, 0, width, height);
		calculateDispose();
	}
	public void calculateDispose(){
		if(!victim.getYellowMark() || victim.getHealth() <= 0){
			dispose = true;
		}
	}
	public boolean getDispose() {
		return dispose;
	}
}
