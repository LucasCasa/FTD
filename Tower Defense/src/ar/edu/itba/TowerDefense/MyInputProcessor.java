package ar.edu.itba.TowerDefense;

import menu.MenuTouchManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {
		int lastx;
		int lasty;
		public MyInputProcessor(){
		}
	   @Override
	   public boolean keyDown (int keycode) {
	      return false;
	   }

	   @Override
	   public boolean keyUp (int keycode) {
	      return false;
	   }

	   @Override
	   public boolean keyTyped (char character) {
	      return false;
	   }

	   @Override
	   public boolean touchDown (int x, int y, int pointer, int button) {
		   y = Gdx.graphics.getHeight() - y;
	      if(button == Buttons.LEFT){
	    	 if(LevelManager.getInstance().getLevel() == 0){
	    		 MenuTouchManager.getInstance().onClick(x,y);
	    	 }else{
	    		 TouchManager.getInstance().onClick(x, y);
	    	 }
	      }else if(button == Buttons.RIGHT){
	    	  TouchManager.getInstance().onRightClick(x,y);
	      }
	    	
	      return true;
	   }

	   @Override
	   public boolean touchUp (int x, int y, int pointer, int button) {

	      return false;
	      
	      
		   
	   }

	   @Override
	   public boolean touchDragged (int x, int y, int pointer) {
		   return false;
	   }

	   @Override
	   public boolean mouseMoved (int x, int y) {
		  
		   
	      return false;
	   }

	   @Override
	   public boolean scrolled (int amount) {
		   Main.cam.zoom += amount* 0.1f;
	      return false;
	   }
	}