package ar.edu.itba.TowerDefense.effects;

import ar.edu.itba.TowerDefense.front.InterfaceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class DeathEffect implements Effect{
	boolean dispose;
	Texture texture;
	int posX, posY,drawX,drawY,width,height,animcant;
	int drawPos;
	float timer;
	public DeathEffect(Texture texture,int posx, int posy,int drawX, int drawY,int width, int height, int animCant){
		this.texture = texture;
		posX = posx;
		posY = posy;
		this.drawX = drawX;
		this.drawY = drawY;
		this.width = width;
		this.height = height;
		this.animcant = animCant;
	}
	public void draw(){
		timer += Gdx.graphics.getDeltaTime();
		
		if(timer >= 0.1f){
			timer =0;
			if(drawPos == animcant){
				dispose = true;
				return;
			}else{	
				drawPos++;
			}
		}
		InterfaceManager.getInstance().batch.draw(texture, posX, posY, drawPos * drawX, drawY, width, height);
	}
	public boolean getDispose(){
		return dispose;
	}
}
