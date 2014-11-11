package ar.edu.itba.TowerDefense;

import java.util.ArrayList;

import towers.BlueTower;
import towers.GreenTower;
import towers.PurpleTower;
import towers.Tower;
import towers.TowerDrawable;
import towers.YellowTower;
import units.Unit;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TouchManager {
	private static final TouchManager tm = new TouchManager();
	
	private TouchManager(){
		

	}
	/**
	 * maneja la accion del click del mouse.
	 * @param posicion x del click
	 * @param posicion y del click
	 */
	public void onClick(int x, int y){
		InterfaceManager.getInstance().infoUnit.setTower(null);
		InterfaceManager.getInstance().infoUnit.setUnit(null);
		InterfaceManager.getInstance().setDrawInfo(0, false);
		InterfaceManager.getInstance().setDrawInfo(1, false);
		InterfaceManager.getInstance().setDrawInfo(2, false);
		InterfaceManager.getInstance().setDrawInfo(3, false);
		if(GameEngine.getInstance().getVictory()){
			if(x >= 400 && x<= 400 + Assets.backBTN.getWidth() && y>= 50 && y<= 50 + Assets.backBTN.getHeight()){
				LevelManager.getInstance().gotoMenu();	
			}
		}else if( GameEngine.getInstance().getDefeat()){
			if(x >= 400 && x<= 400 + Assets.backBTN.getWidth() && y>= 100 && y<= 100 + Assets.backBTN.getHeight()){
				LevelManager.getInstance().gotoMenu();	
			}else if(x >= 400 && x<= 400 + Assets.backBTN.getWidth() && y>= 400 && y<= 400 + Assets.backBTN.getHeight()){
				LevelManager.getInstance().setLevel(LevelManager.getInstance().getLevel());
			}
		}else{
			if(InterfaceManager.getInstance().getNewTower()){
				calculateSelectNewTower(x,y);
			}else if(InterfaceManager.getInstance().getTowerUpgrade()){
				calculateTowerUpgrade(x,y);
			}else{
				if(calculateTowerSlot(x,y))
					calculateUnitClick(x,y);
			}
		}
	}
	/**
	 * detecta si se clickeo alguna unidad, en caso afirmativo
	 * setea la clase encargada de mostrar la informacion
	 * de dicha unidad
	 * @param x
	 * @param y
	 */
	private void calculateUnitClick(int x, int y) {
		ArrayList<Unit> u = GameEngine.getInstance().getUnits();
		for(int i=0;i<u.size();i++){
			Vector2 aux = new Vector2(u.get(i).getCenterX(),u.get(i).getCenterY());
			if(aux.dst(x,y) < 20){
				InterfaceManager.getInstance().infoUnit.setUnit(u.get(i));
			}
		}
	}
	/**
	 * detecta si se clikeo un lugar donde hay o puede haber una torre.
	 * si pasa eso entonces se encarga de determinar si dar la opcion de
	 * crear una nueva torre o subir de nivel una existente
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean calculateTowerSlot(int x, int y) {
		int gridX = (x / Main.GRIDSIZE) * Main.GRIDSIZE;
		int gridY = (y / Main.GRIDSIZE) * Main.GRIDSIZE;
		
		boolean flag = true;
		Vector3[] pos = GameEngine.getInstance().getTowersPosition();
		for(int i = 0;i < pos.length && flag; i++){
			if(gridX == pos[i].x && gridY == pos[i]. y){
				Connector<Tower,TowerDrawable> to = GameEngine.getInstance().getTowerByPosition(gridX,gridY);
				System.out.println(to);
				CircleManager.getInstance().setCenter(gridX + Main.GRIDSIZE / 2,gridY + Main.GRIDSIZE / 2);
				if(to == null){
					InterfaceManager.getInstance().towerSlotClicked(true,false);
				} else {
					InterfaceManager.getInstance().towerSlotClicked(false,true);
					InterfaceManager.getInstance().infoUnit.setTower(to.getBack());
				}
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * maneja el click para poder subir de nivel o vender una torre.
	 * @param x
	 * @param y
	 */
	private void calculateTowerUpgrade(int x, int y) {
		Vector2 aux = CircleManager.getInstance().getCircleGrid();
		
		Tower t = GameEngine.getInstance().getTowers().getBack((int)aux.x,(int)aux.y);
		
		if(t.isUpgradeable()){	
			if( CircleManager.getInstance().upgradeClicked(x, y)){
				t.upgrade();
				System.out.println("PUEDO UPGREADEAR");
				Money.getInstance().subtract(t.value() * t.level);
			}
		}
		if(CircleManager.getInstance().sellClicked(x, y)){
			GameEngine.getInstance().getTowers().getBoth((int) aux.x, (int) aux.y).getFront().stopSound();
			GameEngine.getInstance().getTowers().remove((int)aux.x, (int)aux.y);
			Vector3[] pos = GameEngine.getInstance().getTowersPosition();
			for(int j = 0; j<pos.length;j++){
				if(pos[j].x == aux.x * Main.GRIDSIZE && pos[j].y == aux.y * Main.GRIDSIZE){
					pos[j].z = 0;
				}
			}
			Money.getInstance().add((int)(t.value()*0.8f)); // vemos como lo hacemos
				
			
		}
			

		InterfaceManager.getInstance().nothingClicked();
		
	}
	/**
	 * maneja el click para poder crear una nueva torre
	 * @param x
	 * @param y
	 */
	private void calculateSelectNewTower(int x, int y) {
		Vector2 aux = CircleManager.getInstance().getCirclePosition();
		
		
		
		if(CircleManager.getInstance().purpleClicked(x, y)){
			if(Money.getInstance().subtract(PurpleTower.VALUE)){
				GameEngine.getInstance().addTower((int)aux.x,(int)aux.y,"PurpleTower");
				Assets.newPurple.play(0.2f);
			}
		}
		
		if(CircleManager.getInstance().greenClicked(x, y)){
			if(Money.getInstance().subtract(GreenTower.VALUE)){
				GameEngine.getInstance().addTower((int)aux.x,(int)aux.y,"GreenTower");
				Assets.newGreen.play(0.2f);
			}
		}
		
		if(CircleManager.getInstance().yellowClicked(x, y)){
			if(Money.getInstance().subtract(YellowTower.VALUE)){
				GameEngine.getInstance().addTower((int)aux.x,(int)aux.y,"YellowTower");
				Assets.newYellow.play(0.2f);
			}
		}
		
		if(CircleManager.getInstance().blueClicked(x, y)){
			if(Money.getInstance().subtract(BlueTower.VALUE)){
				GameEngine.getInstance().addTower((int)aux.x,(int)aux.y,"BlueTower");
				Assets.newBlue.play(0.2f);
			}
		}
		InterfaceManager.getInstance().nothingClicked();
	}
	/**
	 * se encarga de mostrar( en caso de que sea necesario)
	 * informacion sobre la torre que el usuario desea saber.
	 * @param x
	 * @param y
	 */
	public void showInfo(int x, int y) {
		if(InterfaceManager.getInstance().getNewTower()){
			for(int i=0; i<InterfaceManager.getInstance().drawInfo.length;i++){
				Vector2 aux = CircleManager.getInstance().calculateImagePosition(i*2 + 1,Assets.NEWTOWER_X,Assets.NEWTOWER_Y);
				aux.x = aux.x + Assets.NEWTOWER_X / 2;
				aux.y = aux.y + Assets.NEWTOWER_Y / 2;
				
				if(aux.dst(x,y) <= 40){
					InterfaceManager.getInstance().setDrawInfo(i,true,x,y);
				}else{
					InterfaceManager.getInstance().setDrawInfo(i,false);
				}
			}
			
		}
		
	}
	

	
	public void onRightClick(int x, int y) {
		showInfo(x,y);
		
	}
	
	public static TouchManager getInstance(){
		return tm;
	}

}
