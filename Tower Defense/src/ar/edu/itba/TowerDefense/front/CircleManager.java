package ar.edu.itba.TowerDefense.front;


import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.back.GameEngine;
import ar.edu.itba.TowerDefense.back.Money;
import ar.edu.itba.TowerDefense.towers.BlueTower;
import ar.edu.itba.TowerDefense.towers.GreenTower;
import ar.edu.itba.TowerDefense.towers.PurpleTower;
import ar.edu.itba.TowerDefense.towers.Tower;
import ar.edu.itba.TowerDefense.towers.YellowTower;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CircleManager {
	private static CircleManager instance;
	private Vector2 center;
	private Vector2[] pos;
	private SpriteBatch batch = InterfaceManager.getInstance().batch;
	public static CircleManager getInstance(){
		if(instance == null){
			instance = new CircleManager();
		}
		return instance;
	}
	private CircleManager(){
		center = new Vector2();
		calculateAllPositions();
	}
	public void setCenter(Vector2 c){
		center = c;
		calculateAllPositions();
	}
	public void setCenter(float x, float y){
		center.x = x;
		center.y = y;
		calculateAllPositions();
	}
	public boolean blueClicked(int x, int y){
		return touched(pos[3], Assets.NEWTOWER_X2,Assets.NEWTOWER_Y2, x, y);
	}
	public boolean greenClicked(int x, int y){
		return touched(pos[1], Assets.NEWTOWER_X2, Assets.NEWTOWER_Y2, x, y);
	}
	public boolean purpleClicked(int x, int y){
		return touched(pos[0], Assets.NEWTOWER_X2, Assets.NEWTOWER_Y2, x, y);
	}
	public boolean yellowClicked(int x, int y){
		return touched(pos[2], Assets.NEWTOWER_X2, Assets.NEWTOWER_Y2, x, y);
	}
	public boolean upgradeClicked(int x, int y){
		return touched(pos[4], Assets.NEWTOWER_X2, Assets.NEWTOWER_Y2, x, y);
	}
	public boolean sellClicked(int x, int y){
		return touched(pos[5], Assets.SELL_X, Assets.SELL_Y, x, y);
	}
	private boolean touched(Vector2 pos, int w, int h, int x, int y){
		if(x>= pos.x && x<= pos.x + w && y<= pos.y + h && y>= pos.y){
			return true;
		}else{
			return false;
		}
	}
	
	public void draw(){	
	}
	public void drawCircleUpgrade(){
		SpriteBatch batch = InterfaceManager.getInstance().batch;
		Tower t = GameEngine.getInstance().getTowerByPosition( (int) getCirclePosition().x,(int) getCirclePosition().y).getBack();
		batch.draw(Assets.tSelect, center.x - Assets.tSelect.getWidth() / 2, center.y - Assets.tSelect.getHeight() / 2);
		if(t.isUpgradeable()){
			batch.draw(Assets.lvlup,pos[4].x,pos[4].y);
		} else {
			batch.draw(Assets.lvlupNot,pos[4].x,pos[4].y);
		}
		if(t.level == 5){
		Assets.SMALL_FONT.draw(batch, "MAX",pos[4].x + 10, pos[4].y +17);
		}else{
		Assets.SMALL_FONT.draw(batch,t.value() * t.level+ "" ,pos[4].x + 10, pos[4].y + 17);
		}
		batch.draw(Assets.sell,pos[5].x , pos[5].y);
		
		batch.draw(Assets.area,t.getAttackPos().x - t.getRange(),t.getAttackPos().y - t.getRange(), t.getRange() * 2 ,t.getRange() * 2);
	}
	public void drawNewCircle(){
	batch.draw(Assets.tSelect, center.x - Assets.tSelect.getWidth() / 2, center.y - Assets.tSelect.getHeight() / 2);
		
		if(Money.getInstance().getAmount() >= PurpleTower.VALUE){
			batch.draw(Assets.newMagicP,pos[0].x,pos[0].y);
		}else{
			batch.draw(Assets.newMagicPNot,pos[0].x,pos[0].y);
		}
		Assets.SMALL_FONT.draw(batch, PurpleTower.VALUE + "",pos[0].x + 13,pos[0].y + 17);
		
		if(Money.getInstance().getAmount() >= GreenTower.VALUE){
			batch.draw(Assets.newArcher,pos[1].x,pos[1].y);
		}else{
			batch.draw(Assets.newArcherNot,pos[1].x,pos[1].y);
		}
		Assets.SMALL_FONT.draw(batch, GreenTower.VALUE + "",pos[1].x + 13,pos[1].y + 17);
		
		if(Money.getInstance().getAmount() >= YellowTower.VALUE){
			batch.draw(Assets.newMagicY,pos[2].x,pos[2].y);
		}else{
			batch.draw(Assets.newMagicYNot,pos[2].x,pos[2].y);
		}
		Assets.SMALL_FONT.draw(batch, YellowTower.VALUE + "",pos[2].x + 13,pos[2].y + 17);
		
		if(Money.getInstance().getAmount() >= BlueTower.VALUE){
			batch.draw(Assets.newMagicB,pos[3].x,pos[3].y);
		}else{
			batch.draw(Assets.newMagicBNot,pos[3].x,pos[3].y);
		}
		Assets.SMALL_FONT.draw(batch, BlueTower.VALUE + "",pos[3].x + 13,pos[3].y + 17);
		
	}
	private void calculateAllPositions(){
	if(pos == null){
		pos = new Vector2[6];
	}
	pos[0] = calculateImagePosition(1,Assets.NEWTOWER_X,Assets.NEWTOWER_Y);
	pos[1] = calculateImagePosition(3,Assets.NEWTOWER_X,Assets.NEWTOWER_Y);
	pos[2] = calculateImagePosition(5,Assets.NEWTOWER_X,Assets.NEWTOWER_Y);
	pos[3] = calculateImagePosition(7,Assets.NEWTOWER_X,Assets.NEWTOWER_Y);
	
	pos[4] = calculateImagePosition(2,Assets.NEWTOWER_X,Assets.NEWTOWER_Y);
	pos[5] = calculateImagePosition(6,Assets.SELL_X,Assets.SELL_Y);
	
		
	}
	public Vector2 calculateImagePosition(int angle,int w, int h){
		float auxX = (float) (center.x + (Math.cos((Math.PI / 4) * angle) * Assets.tSelect.getWidth() / 2)- w / 2);
		float auxY = (float) (center.y +  (Math.sin((Math.PI / 4) * angle) * Assets.tSelect.getWidth() / 2) - h / 2);;
		return new Vector2(auxX,auxY);
		}
	public Vector2 getCircleGrid() {
		return new Vector2((int)center.x / Main.GRIDSIZE, (int)center.y / Main.GRIDSIZE);
	}
	public Vector2 getCirclePosition() {
		Vector2 aux = new Vector2(((int)center.x / Main.GRIDSIZE)* Main.GRIDSIZE, ((int)center.y / Main.GRIDSIZE) * Main.GRIDSIZE);
		return aux;
		
	}
}
