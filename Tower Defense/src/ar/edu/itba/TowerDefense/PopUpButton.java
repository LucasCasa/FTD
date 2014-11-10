package ar.edu.itba.TowerDefense;

import com.badlogic.gdx.graphics.Texture;

public class PopUpButton implements Button{
	int posx,posy;
	Texture t;
	public PopUpButton(int x, int y, Texture t){
		this.posx = x;
		this.posy = y;
		this.t = t;
	}
	public boolean isClicked(int x, int y){
		if(posx <= x + t.getWidth() && posx >= x && posy <= y + t.getHeight() && posy >= y ){
			onClick();
			return true;
		}
		return false;
	}
	public void onClick(){
		
	}
}
