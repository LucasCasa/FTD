package towers;

import ar.edu.itba.TowerDefense.Drawable;

public interface TowerDrawable extends Drawable {
	
	public void draw();
	public void drawAttack();
	public void stopSound();
}
