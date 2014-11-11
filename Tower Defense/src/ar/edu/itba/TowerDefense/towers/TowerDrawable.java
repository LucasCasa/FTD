package ar.edu.itba.TowerDefense.towers;

import ar.edu.itba.TowerDefense.front.Drawable;

public interface TowerDrawable extends Drawable {
	
	public void draw();
	public void drawAttack();
	public void stopSound();
}
