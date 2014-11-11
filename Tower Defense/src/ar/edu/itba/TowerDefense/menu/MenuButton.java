package ar.edu.itba.TowerDefense.menu;

import com.badlogic.gdx.graphics.Texture;

public class MenuButton {
	private Texture t;
	private int x;
	private int y;
	public MenuButton(Texture t, int x, int y){
		this.t = t;
		this.x = x;
		this.y = y;
	}
	public void draw(){
		MenuInterfaceManager.getInstance().batch.draw(t,x,y);
	}
	public  boolean isClicked(int cx, int cy){
		if(cx >= x && cx <= x + t.getWidth() && cy>= y && cy <= y + t.getHeight()){
			action();
			return true;
		}
		return false;
	}
	
	public void action(){
		
	}
}
