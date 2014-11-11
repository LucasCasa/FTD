package ar.edu.itba.TowerDefense.effects;

import ar.edu.itba.TowerDefense.front.InterfaceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StaticEffect  implements Effect {
	SpriteBatch batch;
	private int x,y;
	private boolean dispose;
	private Texture texture;
	private float lifeSpan;
	public StaticEffect(int x, int y, Texture texture){
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.batch = InterfaceManager.getInstance().batch;
	}
	public void draw(){
		lifeSpan+= Gdx.graphics.getDeltaTime();
		if(lifeSpan >= 1f){
			dispose = true;
		}else{
			batch.draw(texture,x,y);
		}
	}
	public boolean getDispose(){
		return dispose;
	}
}
