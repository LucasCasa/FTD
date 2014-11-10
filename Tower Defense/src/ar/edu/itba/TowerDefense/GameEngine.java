package ar.edu.itba.TowerDefense;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import towers.Tower;
import towers.TowerArrayList;
import towers.TowerDrawable;
import units.Unit;
import units.UnitArrayList;
import units.UnitsCreator;

import com.badlogic.gdx.math.Vector3;

public class GameEngine {
	private static final GameEngine ge = new GameEngine();
	Vector3[][] paths = null;
	ArrayList<Unit> uni = new ArrayList<Unit>(100);
	
	protected TowerArrayList towers;
	UnitArrayList units = new UnitArrayList();
	
	protected Vector3[] pos;
	public int life =5;
	public int waveNumber = 0;
	public int maxWave = 0;
	private boolean victory;
	
	private GameEngine(){
		towers = new TowerArrayList();
	//	setTowerPlaces();
	}
	/**
	 * metodo que se ejecuta en loop y que se encarga de manejar toda la logica del nivel.
	 */
	public void update(){
		Collections.sort(uni);
		if(waveNumber > maxWave){
			
		}
		for(int i = 0; i<units.size();i++){
			units.getBack().get(i).update();	
		}
		
		if(units.size() == 0){
			waveNumber++;
			UnitsCreator.getInstance().loadWave(LevelManager.getInstance().getLevel(),waveNumber, paths);
		}
		if( waveNumber > maxWave){
			victory = true;
			LevelManager.getInstance().gotoMenu();
		}
		
		if(life == 0){
			LevelManager.getInstance().gotoMenu();
		}
		
	}
	
	public static GameEngine getInstance(){
		return ge;
	}
	/**
	 * setea los caminos que van a reccorer las tropas
	 * @param camino 1
	 * @param camino 2
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
	 * setea las posiociones de las torres
	 * @param posiciones de las torres
	 */
	public void setTowerPlaces(Vector3[] towers){
		pos = towers;
	}
	/**
	 * setea el vector que se encarga de avisar si hay una torre creada en
	 * ese espacio o no.
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
		return uni;
	}
	
	public UnitArrayList getUnitArray(){
		return units;
	}
	/**
	 * recibe el nombre de la torre y la posicion donde la va a crear y se encarga
	 * de crearla y ponerla en la posicion.
	 * @param posicion en x
	 * @param posiocion en y
	 * @param nombre de la torre
	 */
	public void addTower(int newX, int newY, String string) {
		Class cl,c2;
		try {
			cl = Class.forName("towers." + string);
			Constructor<Tower> con = cl.getConstructor(int.class, int.class);
			Tower t = con.newInstance(newX, newY);
			
			c2 = Class.forName("towers." + string + "UI");
			Constructor<TowerDrawable> con2 = c2.getConstructor(int.class, int.class, Class.forName("towers." + string));
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
		back.attack(uni);		
	}
	/**
	 * resetea todos los datos.
	 */
	public void reset(){
		uni.clear();
		towers.clear();
		units.clear();
		waveNumber = 0;
		Money.getInstance().amount = 150;
		life = 10;
		victory = false;
	//	for(int i = 0; i<pos.length;i++){
	//		pos[i].z = 0;
	//	}
	}
}
