package ar.edu.itba.TowerDefense.towers;

import java.util.ArrayList;

import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.back.Connector;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.front.InterfaceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YellowTowerUI implements TowerDrawable {
	private float drawTime;
	private int posX;
	private int posY;
	private int drawX;
	private int drawY;
	private ArrayList<Connector<Proyectile,ProyectileUI>> p;
	private YellowTower tower;
	private SpriteBatch batch;
	public YellowTowerUI(int posx, int posy, YellowTower tower){
		this.posX = posx;
		this.posY = posy;
		this.tower = tower;
		this.batch = InterfaceManager.getInstance().batch;
		
	}
	public void draw(){
		drawTime += Gdx.graphics.getDeltaTime();
		if(drawTime >= 0.1f && tower.getAttacking()){  
			drawTime = 0;
			drawX+=130;  
						
		}				
		if(drawX == 780){
			drawX = 0;
			tower.setAttackReady(true);
		}

		batch.draw(Assets.magicY,posX,posY,Main.GRIDSIZE,Main.GRIDSIZE * (97f / 130), (int) drawX,(int) drawY,130,97,false,false); // dibuja la torre
	}
	public void drawAttack(){
		p = tower.getProyectiles();
		for(Connector<Proyectile,ProyectileUI> pro : p){
			if(pro != null){
				pro.getFront().draw();
			}
		}
	}
	@Override
	public void stopSound() {
		
	}
}
