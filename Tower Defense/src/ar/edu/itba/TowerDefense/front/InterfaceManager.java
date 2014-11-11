package ar.edu.itba.TowerDefense.front;

import java.util.ArrayList;


import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.back.Connector;
import ar.edu.itba.TowerDefense.back.GameEngine;
import ar.edu.itba.TowerDefense.back.LevelManager;
import ar.edu.itba.TowerDefense.effects.DeathEffect;
import ar.edu.itba.TowerDefense.effects.Effect;
import ar.edu.itba.TowerDefense.towers.TowerArrayList;
import ar.edu.itba.TowerDefense.units.Unit;
import ar.edu.itba.TowerDefense.units.UnitArrayList;
import ar.edu.itba.TowerDefense.units.UnitUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class InterfaceManager {
	
		private static final InterfaceManager instance = new InterfaceManager();
		public SpriteBatch batch;
		public boolean draw = false;
		public static int level = 1;
		public static ArrayList<Effect> e = new ArrayList<Effect>();
		protected boolean drawCircle = false;
		protected boolean drawNew = false;
		protected boolean drawUpgrade = false;
		protected boolean upgradeable = true;
		protected boolean[] drawInfo = new boolean[4];
		protected Vector2 drawInfoPos = new Vector2(0,0);
		protected InfoUI infoUnit; 
		protected TowerArrayList towers;
		UnitArrayList units = new UnitArrayList();
		private Texture background;
		Vector3[] pos;
		private InterfaceManager(){
			towers = GameEngine.getInstance().getTowers();
			units = GameEngine.getInstance().getUnitArray();
			infoUnit = new InfoUI(Assets.FONT);
			pos = GameEngine.getInstance().getTowersPosition();
			background = null;
		}
		

		/*
		 * Looping method, is in charge of all the drawing logic
		 * and calls upon the methods that do the actual drawing
		 */
		public void draw(){
			batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			if(!GameEngine.getInstance().getDefeat()){
				for(int i = 0; i<pos.length;i++){
					if(pos[i].z == 0){
						int l = (LevelManager.getInstance().getLevel() - 1) / 2;
						batch.draw(Assets.newt[l], pos[i].x, pos[i].y, Main.GRIDSIZE, ((float) Main.GRIDSIZE / Assets.newt[l].getWidth()) * Assets.newt[l].getHeight() );
					}
				}
				
				for(int i=0; i< towers.size();i++){
					if(towers.getBack().get(i) != null){
						GameEngine.getInstance().towerAttack(towers.getBack().get(i));
						towers.getFront().get(i).draw();
					}
				}
				for(int i = 0; i<units.size();i++){
					units.getFront().get(i).draw();
				}
			
				checkUnitshealth();
			
				for(int i = 0;i<towers.size();i++){
					towers.getFront().get(i).drawAttack();
				}
				if(drawNew){
					drawNewTowerCircle();
				}else if(drawUpgrade){
					drawUpgradeCircle();
				}
				
				manageEffects();
		
				manageTowerInfo();
			
				infoUnit.draw();
			
				if(GameEngine.getInstance().getVictory()){
					batch.draw(Assets.victory, Gdx.graphics.getWidth() / 2 - Assets.victory.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Assets.victory.getHeight() / 2);
					batch.draw(Assets.backBTN,400, 50);
				}
			
			}else{
				batch.draw(Assets.retryBTN, 400, 400);
				batch.draw(Assets.menuBTN,  400, 100);
			}
		}
		/*
		 * Manages the drawing the towers information
		 */
		private void manageTowerInfo() {
			for(int i=0;i<drawInfo.length;i++){
				if(drawInfo[i]){
					batch.draw(Assets.info[i],drawInfoPos.x, drawInfoPos.y,Assets.info[i].getWidth() /2, Assets.info[i].getHeight() / 2);
				}
			}
			
		}
		/*
		 * Manages the game's effects
		 */
		private void manageEffects() {
			for(int i = 0; i<e.size();i++){
				if(e.get(i).getDispose() ){
					e.remove(i);
				}else{
					e.get(i).draw();
				}
			}
			
		}
		/* 
		 * Checks the unit's health.
		 * If it is equal or less than zero
		 * it will create effect with the death animation
		 * and eliminate the unit from the array
		 */
		private void checkUnitshealth() {
			for (int i = 0;i<units.size();i++){
				if(units.getBack().get(i).getHealth() <= 0){
					loadDeathEffect(units.getBoth(i));
					units.remove(i);
				}else if(units.getBack().get(i).hasWon()){
					GameEngine.getInstance().life--;
					units.remove(i);
				}
			}
			
		}
	/*
	 * Draws the options for upgrading or selling a tower
	 */
	private void drawUpgradeCircle() {
		CircleManager.getInstance().drawCircleUpgrade();
	}
	/*
	 * Draws the options for creating a new tower
	 */
	private void drawNewTowerCircle() {
		CircleManager.getInstance().drawNewCircle();
	
	}
	/*
	 * Called upon when a unit dies,
	 * it loads its death animation
	 */
	public void loadDeathEffect(Connector<Unit,UnitUI> u){
		Texture text = u.getFront().getDeathTexture();
		int posX = u.getBack().getX();
		int posY = u.getBack().getY();
		int width = u.getFront().getDeathWidth();
		int height = u.getFront().getDeathHeight();
		int drawY = u.getFront().getDeathY();
		int drawX = u.getFront().getDeathX();
		int animCant = u.getFront().getDeathAnimCant();
		e.add(new DeathEffect(text, posX, posY, drawX, drawY, width, height, animCant));
		}
	
	public void setBatch(SpriteBatch batch){
		this.batch = batch;
	}

	public void setTowerUpgrade(boolean b) {
		drawUpgrade = b;
	}

	public void setNewTower(boolean b) {
		drawNew = b;
	}

	public void setCircle(boolean b) {
		drawCircle = b;
	}

	public boolean getTowerUpgrade() {
		return drawUpgrade;
	}

	public boolean getNewTower() {
		return drawNew;
	}

	public void setDrawInfo(int pos, boolean b, int x, int y) {
		drawInfo[pos] = b;
		drawInfoPos.x = x;
		drawInfoPos.y = y;
		
	}

	public void setDrawInfo(int pos, boolean b){
		drawInfo[pos] = b;
	}
	
	/*
	 * Called upon if the click is not on the location of a tower
	 */
	public void nothingClicked() {
		drawNew = false;
		drawUpgrade = false;
	}
	/*
	 * First parameter is true if the selected location has no tower yet.
	 * Second parameter is true if there already is a tower in that location.
	 * 
	 * Note: Both can't be true at the same time,
	 * 		 drawCircle is true if any of the two parameters are true.
	 */
	public void towerSlotClicked(boolean newTower, boolean alreadyTower) {
		if(!(newTower && alreadyTower)){
			drawNew = newTower;
			drawUpgrade = alreadyTower;
			drawCircle = newTower || alreadyTower;
		}
		
	}
	
	public void setBackground(Texture t){
		background = t;
	}
	
	public static InterfaceManager getInstance(){
		return instance;
	}
	
	public TowerArrayList getTowers(){
		return towers;
	}
	/**
	 * Resets all variables for the next level
	 */
	public void reset() {
		towers.clear();
		units.clear();
		pos = null;
		e.clear();
	}
	/**
	 * Loads all data through GameEngine
	 */
	public void loadFromGE(){
		towers = GameEngine.getInstance().getTowers();
		units = GameEngine.getInstance().getArrayUnits();
		pos = GameEngine.getInstance().getTowersPosition();
	}
}
