package towers;

import java.util.ArrayList;

import ar.edu.itba.TowerDefense.Assets;
import ar.edu.itba.TowerDefense.Connector;
import ar.edu.itba.TowerDefense.InterfaceManager;
import ar.edu.itba.TowerDefense.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BlueTowerUI implements TowerDrawable{
	
	private float drawTime;
	private int posX;
	private int posY;
	private Vector2 drawsTower = new Vector2(0,0);
	private ArrayList<Connector<Proyectile,ProyectileUI>> p;
	private BlueTower tower;
	private SpriteBatch batch;
	
	public BlueTowerUI(int posx, int posy, BlueTower tower){
		this.posX = posx;
		this.posY = posy;
		this.tower = tower;
		this.batch = InterfaceManager.getInstance().batch;
		
	}
	
	public void draw(){
		drawTime += Gdx.graphics.getDeltaTime();
		if(drawTime >= 0.05f && tower.getAttacking()){
			drawTime = 0;
			if(tower.getVictimBelow()){
				drawsTower.y = 0;
				drawsTower.x+=72;
			}
			else{
				drawsTower.y = 100;
				drawsTower.x+=72;
			}
			if(drawsTower.x == 288){
				tower.setAttackReady(true);
			}
			if(drawsTower.x == 648){
				tower.setAttacking(false);
				drawsTower.x = 0;
			}
		}
		batch.draw(Assets.blueT,posX,posY,Main.GRIDSIZE,66, (int) drawsTower.x,(int) drawsTower.y,72,66,false,false); // dibuja la torre
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
		// TODO Auto-generated method stub
		
	}

}
