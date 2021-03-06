package ar.edu.itba.TowerDefense.towers;

import ar.edu.itba.TowerDefense.effects.StaticEffect;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.front.Drawable;
import ar.edu.itba.TowerDefense.front.InterfaceManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class ProyectileUI implements Drawable{
	SpriteBatch batch;
	Proyectile p;
	int width;
	int height;
	private TextureRegion TX;
	
	public ProyectileUI(Proyectile p, int width, int height, Texture t){
		TX= new TextureRegion(t);
		this.batch = InterfaceManager.getInstance().batch;
		this.width = width;
		this.height = height;
		this.p = p;
	}
	public void draw(){
		if(p.hasMiss()){
			InterfaceManager.e.add(new StaticEffect((int)p.getX(),(int)p.getY(),Assets.arrowMiss));
		}
		batch.draw(TX, p.getX(), p.getY(), width / 2,height / 2, width, height, 0.5f, 0.5f,(float)( p.getAngle() * 180 / Math.PI));
	}

}
