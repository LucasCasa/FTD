package ar.edu.itba.TowerDefense;

import menu.MenuInterfaceManager;

import com.badlogic.gdx.math.Vector3;

public class LevelManager {
	private static LevelManager instance;
	private int levelSelected;
	private int lastLevelSelected;
	private LevelManager(){
		
	}
	public static LevelManager getInstance(){
		if(instance == null){
			instance = new LevelManager();
		}
		return instance;
	}
	public void setLevel(int l){
		levelSelected = l;
	}
	
	public void update(){
		if(levelSelected != lastLevelSelected){
			loadNewLevel();
			lastLevelSelected = levelSelected; 
		}
		if(levelSelected == 0){
			MenuInterfaceManager.getInstance().draw();
		}else{
		GameEngine.getInstance().update();
		InterfaceManager.getInstance().draw();
		}
		
	}
	public void loadNewLevel(){
		GameEngine.getInstance().reset();
		InterfaceManager.getInstance().reset();
		if(levelSelected> 0){
			InterfaceManager.getInstance().setBackground(Assets.backlvl[levelSelected - 1]);
		}
		loadTowers();
		loadPaths();
		InterfaceManager.getInstance().loadFromGE();
	}
	public void gotoMenu(){
		levelSelected = 0;
		MenuInterfaceManager.getInstance().changeState(0);
	}
	
	public void loadPaths(){
		switch(levelSelected){
		case 1:
			Vector3[] aux1 = {new Vector3(0,350,2),new Vector3(290,350,2),new Vector3(290,620,3),new Vector3(690,620,2),new Vector3(690,190,0),new Vector3(0,0,2)};
			Vector3[] aux2 = {new Vector3(0,300,2),new Vector3(320,300,2),new Vector3(320,590,3),new Vector3(650,590,2),new Vector3(650,150,0),new Vector3(0,0,2)};
			GameEngine.getInstance().setPaths(aux2, aux1);
			break;
		case 2:
			Vector3[] aux3 = {new Vector3(0,420,2),new Vector3(320,420,2),new Vector3(320,204,0),new Vector3(506,204,2),new Vector3(506,492,3),new Vector3(752,492,2),new Vector3(752,276,0),new Vector3(0,0,2)};
			Vector3[] aux4 = {new Vector3(0,370,2),new Vector3(290,370,2),new Vector3(290,154,0),new Vector3(536,154,2),new Vector3(536,442,3),new Vector3(722,442,2),new Vector3(722,226,0),new Vector3(0,0,2)};
			GameEngine.getInstance().setPaths(aux4, aux3);
			break;
		case 3:
			Vector3[] aux5 = {new Vector3(360,720,0),new Vector3(360,630,0),new Vector3(160,630,1),new Vector3(160,200,0),new Vector3(1300,200,2)};
			Vector3[] aux6 = {new Vector3(390,720,0),new Vector3(390,630,0),new Vector3(740,630,2),new Vector3(740,410,0),new Vector3(440,410,1),new Vector3(440,-20,0)};
			GameEngine.getInstance().setPaths(aux5,aux6);
			break;
		case 4:
			Vector3[] aux7 = {new Vector3(0,410,2),new Vector3(220,410,2),new Vector3(220,630,3),new Vector3(810,630,2),new Vector3(810,130,0),new Vector3(1300,130,2)};
			Vector3[] aux8 = {new Vector3(0,410,2),new Vector3(220,410,2),new Vector3(220,200,0),new Vector3(660,200,2),new Vector3(660,410,3),new Vector3(440,410,1),new Vector3(440,740,0)};
			GameEngine.getInstance().setPaths(aux7,aux8);
			break;
		case 5:
			Vector3[] aux9 = {new Vector3(0,325,2),new Vector3(250,325,2),new Vector3(250,469,3),new Vector3(610,469,2),new Vector3(610,253,0),new Vector3(754,253,2),new Vector3(754,397,3),new Vector3(1042,397,2),new Vector3(1042,325,0),new Vector3(0,0,2)};
			Vector3[] aux10 = {new Vector3(0,325,2),new Vector3(250,325,2),new Vector3(250,109,0),new Vector3(610,109,2),new Vector3(610,253,3),new Vector3(754,253,2),new Vector3(754,181,0),new Vector3(1042,181,2),new Vector3(1042,325,3),new Vector3(0,0,2)};
			GameEngine.getInstance().setPaths(aux9,aux10);
			break;
		case 6:
			Vector3[] aux11 = {new Vector3(645,720,0),new Vector3(645,596,0),new Vector3(568,596,1),new Vector3(568,452,0),new Vector3(645,452,2),new Vector3(645,164,0),new Vector3(505,164,1),new Vector3(505,308,3),new Vector3(225,308,1),new Vector3(225,164,0),new Vector3(0,0,1)};
			Vector3[] aux12 = {new Vector3(675,720,0),new Vector3(675,596,0),new Vector3(742,596,2),new Vector3(742,452,0),new Vector3(675,452,1),new Vector3(675,164,0),new Vector3(825,164,2),new Vector3(825,308,3),new Vector3(1105,308,2),new Vector3(1105,164,0),new Vector3(0,0,2)};
			GameEngine.getInstance().setPaths(aux11,aux12);
			break;
		}
	}
	
	public void loadTowers(){
		Vector3[] pos = null;
		switch(levelSelected){
		case 1:
			pos = new Vector3[8];
			pos[0] = new Vector3(Main.GRIDSIZE * 3, Main.GRIDSIZE * 5,0);
			pos[1] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 4,0);
			pos[2] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 5,0);
			pos[3] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 7,0);
			pos[4] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 6,0);
			pos[5] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 7,0);
			pos[6] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 3,0);
			pos[7] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 3,0);
		break;
		case 2:
			pos = new Vector3[10];
			pos[0] = new Vector3(Main.GRIDSIZE * 3, Main.GRIDSIZE * 4, 0);
			pos[1] = new Vector3(Main.GRIDSIZE * 6, Main.GRIDSIZE * 3, 0);
			pos[2] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 5, 0);
			pos[3] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 7, 0);
			pos[4] = new Vector3(Main.GRIDSIZE * 12, Main.GRIDSIZE * 4, 0);
			pos[5] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 7, 0);
			pos[6] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 5, 0);
			pos[7] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 2, 0);
			pos[8] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 3, 0);
			pos[9] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 3, 0);
		break;
		case 3:
			pos = new Vector3[10];
			pos[0] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 3, 0);
			pos[1] = new Vector3(Main.GRIDSIZE * 7, Main.GRIDSIZE * 3, 0);
			pos[2] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 1, 0);
			pos[3] = new Vector3(Main.GRIDSIZE * 7, Main.GRIDSIZE * 1, 0);
			pos[4] = new Vector3(Main.GRIDSIZE * 4, Main.GRIDSIZE * 7, 0);
			pos[5] = new Vector3(Main.GRIDSIZE * 6, Main.GRIDSIZE * 7, 0);
			pos[6] = new Vector3(Main.GRIDSIZE * 11, Main.GRIDSIZE * 3, 0);
			pos[7] = new Vector3(Main.GRIDSIZE * 3, Main.GRIDSIZE * 3, 0);
			pos[8] = new Vector3(Main.GRIDSIZE * 9, Main.GRIDSIZE * 6, 0);
			pos[9] = new Vector3(Main.GRIDSIZE * 9, Main.GRIDSIZE * 7, 0);
			break;
		case 4:
			pos = new Vector3[11];
			pos[0] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 4, 0);
			pos[1] = new Vector3(Main.GRIDSIZE * 7, Main.GRIDSIZE * 3, 0);
			pos[2] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 1, 0);
			pos[3] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 2, 0);
			pos[4] = new Vector3(Main.GRIDSIZE * 4, Main.GRIDSIZE * 7, 0);
			pos[5] = new Vector3(Main.GRIDSIZE * 6, Main.GRIDSIZE * 7, 0);
			pos[6] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 4, 0);
			pos[7] = new Vector3(Main.GRIDSIZE * 4, Main.GRIDSIZE * 3, 0);
			pos[8] = new Vector3(Main.GRIDSIZE * 12, Main.GRIDSIZE * 5, 0);
			pos[9] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 7, 0);
			pos[10] = new Vector3(Main.GRIDSIZE * 12, Main.GRIDSIZE * 2, 0);
			break;
		case 5:
			pos = new Vector3[10];
			pos[0] = new Vector3(Main.GRIDSIZE * 4, Main.GRIDSIZE * 4, 0);
			pos[1] = new Vector3(Main.GRIDSIZE * 6, Main.GRIDSIZE * 2, 0);
			pos[2] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 7, 0);
			pos[3] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 7, 0);
			pos[4] = new Vector3(Main.GRIDSIZE * 13, Main.GRIDSIZE * 4, 0);
			pos[5] = new Vector3(Main.GRIDSIZE * 12, Main.GRIDSIZE * 6, 0);
			pos[6] = new Vector3(Main.GRIDSIZE * 9, Main.GRIDSIZE * 4, 0);
			pos[7] = new Vector3(Main.GRIDSIZE * 11, Main.GRIDSIZE * 3, 0);
			pos[8] = new Vector3(Main.GRIDSIZE * 9, Main.GRIDSIZE * 2, 0);
			pos[9] = new Vector3(Main.GRIDSIZE * 4, Main.GRIDSIZE * 2, 0);
			break;
		case 6:
			pos = new Vector3[11];
			pos[0] = new Vector3(Main.GRIDSIZE * 4, Main.GRIDSIZE * 3, 0);
			pos[1] = new Vector3(Main.GRIDSIZE * 6, Main.GRIDSIZE * 2, 0);
			pos[2] = new Vector3(Main.GRIDSIZE * 5, Main.GRIDSIZE * 5, 0);
			pos[3] = new Vector3(Main.GRIDSIZE * 9, Main.GRIDSIZE * 7, 0);
			pos[4] = new Vector3(Main.GRIDSIZE * 14, Main.GRIDSIZE * 3, 0);
			pos[5] = new Vector3(Main.GRIDSIZE * 12, Main.GRIDSIZE * 2, 0);
			pos[6] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 3, 0);
			pos[7] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 5, 0);
			pos[8] = new Vector3(Main.GRIDSIZE * 8, Main.GRIDSIZE * 3, 0);
			pos[9] = new Vector3(Main.GRIDSIZE * 10, Main.GRIDSIZE * 5, 0);
			pos[10] = new Vector3(Main.GRIDSIZE * 13, Main.GRIDSIZE * 5, 0);
			break;
		}
		GameEngine.getInstance().setTowerPlaces(pos);
		
	}
	
	public int getLevel() {
		return levelSelected;
	}
}
