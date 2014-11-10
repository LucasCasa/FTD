package effects;

import java.util.Random;

import units.Unit;

import ar.edu.itba.TowerDefense.InterfaceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MovingEffect implements Effect {
	private SpriteBatch batch;
	private Unit victim;
	private int width;
	private int height;
	private Texture texture;
	private boolean dispose;
	private float timer = 0;
	private boolean center;
	private float duration;
	Random rand;
	int angle;
	public MovingEffect(Unit u, Texture texture, boolean center, float duration){
		victim = u;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
		this.texture = texture;
		rand = new Random();
		angle = rand.nextInt(90) - 45;
		this.center = center;
		this.duration = duration;
		this.batch = InterfaceManager.getInstance().batch;
	}
	public void draw(){
		timer+= Gdx.graphics.getDeltaTime();
		if(timer > duration || victim.getHealth() <= 0){
			dispose = true;
		}else{
			if(center){
				batch.draw(texture, victim.getCenterX() - this.width / 2, victim.getCenterY() - this.height / 2);
			}else{
				batch.draw(texture, victim.getCenterX(), victim.getCenterY(), texture.getWidth() / 2, texture.getHeight() / 2, width, height, 1, 1, angle, 0, 0, width, height, false, false);
			}
		}
	}
	public boolean getDispose(){
		return dispose;
	}
}
