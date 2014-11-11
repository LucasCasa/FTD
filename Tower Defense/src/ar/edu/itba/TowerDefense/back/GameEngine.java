package ar.edu.itba.TowerDefense.back;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;


import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.towers.Tower;
import ar.edu.itba.TowerDefense.towers.TowerArrayList;
import ar.edu.itba.TowerDefense.towers.TowerDrawable;
import ar.edu.itba.TowerDefense.units.Unit;
import ar.edu.itba.TowerDefense.units.UnitArrayList;
import ar.edu.itba.TowerDefense.units.UnitsCreator;

import com.badlogic.gdx.math.Vector3;

public class GameEngine {
	private static final GameEngine ge = new GameEngine();
	Vector3[][] paths = null;
	
	private TowerArrayList towers;
	private UnitArrayList units = new UnitArrayList();
	
	protected Vector3[] pos;
	public int life =5;
	public int waveNumber = 0;
	public int maxWave = 0;
	private boolean victory;
	private boolean defeat;
	
	private GameEngine(){
		towers = new TowerArrayList();
	}
	/**
	 * Looping method that manages all of the level's logic
	 */
	public void update(){
		if(!defeat){
		units.sort();
		if(waveNumber > maxWave){
			
		}
		for(int i = 0; i<units.size();i++){
			units.getBack().get(i).update();	
		}
		
		if(units.size() == 0 && !victory){
			waveNumber++;
			UnitsCreator.getInstance().loadWave(LevelManager.getInstance().getLevel(),waveNumber, paths);
		}
		if( waveNumber > maxWave){
			victory = true;
		}
		
		if(life == 0){
			defeat = true;
			Assets.raySound.stop();
		}
		}
	}
	
	public static GameEngine getInstance(){
		return ge;
	}
	/**
	 * Sets the paths the troops will travel
	 * @param path 1
	 * @param path 2
	 */
	public void setPaths(Vector3[] p1, Vector3[] p2){
		if(paths == null){
			paths = new Vector3[][]{p1,p2};
		}else{
			paths[0] = p1;
			paths[1] = p2;
		}
	}
	/**
	 * Sets the towers positions
	 * @param tower positions
	 */
	public void setTowerPlaces(Vector3[] towers){
		pos = towers;
	}
	/**
	 * Sets the vector value which will warn if a tower already exists on that position
	 * @param x
	 * @param y
	 */
	public  void setPos(int x, int y){
		for(int i = 0; i<pos.length;i++){
			if(pos[i].x == x && pos[i].y == y){
				pos[i].z = 1;
			}
		}
	}
	
	public TowerArrayList getTowers() {
		return towers;

	}
	
	public Vector3[] getTowersPosition() {
		return pos;
	}
	
	public ArrayList<Unit> getUnits(){
		return units.getBack();
	}
	
	public UnitArrayList getUnitArray(){
		return units;
	}
	/**
	 * Receives the tower's name and position and is in charge of
	 * creating and placing it
	 * @param x position
	 * @param y position
	 * @param tower name
	 */
	public void addTower(int newX, int newY, String string) {
		Class cl,c2;
		try {
			cl = Class.forName("ar.edu.itba.TowerDefense.towers." + string);
			Constructor<Tower> con = cl.getConstructor(int.class, int.class);
			Tower t = con.newInstance(newX, newY);
			
			c2 = Class.forName("ar.edu.itba.TowerDefense.towers." + string + "UI");
			Constructor<TowerDrawable> con2 = c2.getConstructor(int.class, int.class, Class.forName("ar.edu.itba.TowerDefense.towers." + string));
			TowerDrawable tUI = con2.newInstance(newX, newY,t);
		
			towers.add(t,tUI);
		} catch (ClassNotFoundException e) {
			System.out.println("La cagaste chabon Class");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("La cagaste chabon Method");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		setPos(newX, newY);
		
	}
	
	public Connector<Tower,TowerDrawable> getTowerByPosition(int x, int y){
		x = (int)x / Main.GRIDSIZE;
		y = (int)y / Main.GRIDSIZE;
		return towers.getBoth(x,y);
	}

	public boolean getVictory() {
		return victory;
	}

	public void towerAttack(Tower back) {
		back.attack(units);		
	}
	/**
	 * Resets all data
	 */
	public void reset(){
		towers.clear();
		units.clear();
		waveNumber = 0;
		Money.getInstance().amount = 150;
		life = 10;
		victory = false;
		defeat = false;
	}
	public boolean getDefeat(){
		return defeat;
	}
	public UnitArrayList getArrayUnits() {
		return units;
	}
}
