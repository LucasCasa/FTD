package towers;

import java.util.ArrayList;

import ar.edu.itba.TowerDefense.Connector;
import ar.edu.itba.TowerDefense.CustomArrayList;
import ar.edu.itba.TowerDefense.Main;


public class TowerArrayList extends CustomArrayList<Tower, TowerDrawable> {
	
	public TowerArrayList(){
		back = new ArrayList<Tower>(15);
		front= new ArrayList<TowerDrawable>(15);
	}
	
	public Tower getTower(int posx, int posy){
		posx = posx / Main.GRIDSIZE * Main.GRIDSIZE;
		posy = posy / Main.GRIDSIZE * Main.GRIDSIZE;
		
		for(Tower t: back){
			if((int) t.getPos().x == posx &&  (int) t.getPos().y == posy){
				return t;
			}
		}
		return null;
	}
	
	public TowerDrawable getTowerDrawable(int posx, int posy){
		posx = posx / Main.GRIDSIZE * Main.GRIDSIZE;
		posy = posy / Main.GRIDSIZE * Main.GRIDSIZE;
		
		for(int i = 0; i<back.size();i++ ){
			if((int) back.get(i).getPos().x == posx &&  (int) back.get(i).getPos().y == posy){
				return front.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Tower> getAllTowers(){
		return back;
	}
	
	public ArrayList<TowerDrawable> getAllTowerDrawable(){
		return front;
	}
	
	public Connector<Tower,TowerDrawable> getBoth(int x, int y){
		for(int i = 0;i<back.size();i++){
			int aux1 = (int)(back.get(i).getPos().x) / Main.GRIDSIZE;
			int aux2 = (int)(back.get(i).getPos().y) / Main.GRIDSIZE;
			if(aux1 == x && aux2 == y){
				return getBoth(i);
			}
		}
		return null;
	}
	
	public Tower getBack(int x, int y){
		for(int i= 0; i < back.size();i++){
			int aux1 = (int)(back.get(i).getPos().x) / Main.GRIDSIZE;
			int aux2 = (int)(back.get(i).getPos().y) / Main.GRIDSIZE;
			if( aux1 == x && aux2 == y){
				return back.get(i);
			}
		}
		return null;
	}
	
	public void remove(int x, int y){
		for(int i= 0; i < back.size();i++){
			int aux1 = (int)(back.get(i).getPos().x) / Main.GRIDSIZE;
			int aux2 = (int)(back.get(i).getPos().y) / Main.GRIDSIZE;
			if(aux1 == x && aux2 == y){
				remove(i);
				return;
			}
		}
	}
}
