package ar.edu.itba.TowerDefense;


public class TowerButton implements Button{
	int x,y;
	public TowerButton(int x, int y){
		this.x = x;
		this.y = y;
	}
	public boolean isClicked(int x, int y){
		if(x <= this.x + Main.GRIDSIZE && x >= this.x && y <= this.y + Main.GRIDSIZE && y>= this.y){
			onClick();
			return true;
		}
		return false;
	}
	public void onClick(){
		  Object to = GameEngine.getInstance().getTowerByPosition(x / Main.GRIDSIZE, y / Main.GRIDSIZE);
		  if(to == null){
				InterfaceManager.getInstance().towerSlotClicked(true,false);
			} else {
				InterfaceManager.getInstance().towerSlotClicked(false,true);
			}
	}
}
