package ar.edu.itba.TowerDefense;

import java.util.ArrayList;

import towers.TowerArrayList;
import units.Unit;
import units.UnitArrayList;
import units.UnitUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import effects.DeathEffect;
import effects.Effect;

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
		 * Metodo en Loop, se encarga de toda la logica del dibujo 
		 * del juego y llama a los demas metodos y clases para que 
		 * dibujen.
		 */
		public void draw(){
			batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			for(int i = 0; i<pos.length;i++){
				if(pos[i].z == 0){
					int l = (LevelManager.getInstance().getLevel() - 1) / 2;
					batch.draw(Assets.newt[l], pos[i].x, pos[i].y + Main.GRIDSIZE / 3, Main.GRIDSIZE, ((float) Main.GRIDSIZE / Assets.newt[l].getWidth()) * Assets.newt[l].getHeight() );
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
			
			
		}
		/*
		 * Maneja el dibujo de la informacion de las torres
		 */
		private void manageTowerInfo() {
			for(int i=0;i<drawInfo.length;i++){
				if(drawInfo[i]){
					batch.draw(Assets.info[i],drawInfoPos.x, drawInfoPos.y,Assets.info[i].getWidth() /2, Assets.info[i].getHeight() / 2);
				}
			}
			
		}
		/*
		 * Maneja los efectos del juego.
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
		 * se fija en la vida de los enemigos.
		 * si la vida es igual o menos a cero
		 * crea un efecto con la animacion de la muerte 
		 * del enemigo y los elimina del array.
		 *
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
	 * dibuja las opciones para subir de nivel, o 
	 * vender una torre
	 */
	private void drawUpgradeCircle() {
		CircleManager.getInstance().drawCircleUpgrade();
	}
	/*
	 * Dibuja las opciones de crear una nueva
	 * torre
	 */
	private void drawNewTowerCircle() {
		CircleManager.getInstance().drawNewCircle();
	
	}
	/* Se llama cuando se muere una tropa
	 * carga la animacion de la muerte de
	 * esa tropa
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
	 * Se llama si no se clickeo en un espacio de una torre
	 */
	public void nothingClicked() {
		drawNew = false;
		drawUpgrade = false;
	}
	/*
	 * el primer parametro es true si todavia no hay una torre en el 
	 * espacio seleccionado, y el segundo es true si ya hay una torre
	 * en el espacio.
	 * Notese que las 2 no pueden ser true a la vez.
	 * drawCircle es true si alguna de las 2 es true
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
	 * reseta todas las variables para poder empezar un nuevo nivel
	 */
	public void reset() {
		towers.clear();
		units.clear();
		pos = null;
		e.clear();
	}
	/**
	 * carga todos los datos desde el GameEngine.
	 */
	public void loadFromGE(){
		towers = GameEngine.getInstance().towers;
		units = GameEngine.getInstance().units;
		pos = GameEngine.getInstance().getTowersPosition();
	}
}
