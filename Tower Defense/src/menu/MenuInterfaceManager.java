package menu;

import java.util.ArrayList;

import ar.edu.itba.TowerDefense.Assets;
import ar.edu.itba.TowerDefense.LevelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuInterfaceManager {
	ArrayList<MenuButton> b = new ArrayList<MenuButton>();
	private static MenuInterfaceManager instance;
	private int state;
	private int lastState = 0;
	public SpriteBatch batch;
	private MenuInterfaceManager(){
		state = 0;
		createMenuButtons();
	}
	
	public void createMenuButtons(){
		b.add(new MenuButton(Assets.playBTN,Gdx.graphics.getWidth() / 2 - Assets.playBTN.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Assets.playBTN.getHeight() / 2){
			public void action(){
				MenuInterfaceManager.getInstance().nextState();
			}
		});
		b.add(new MenuButton(Assets.exitBTN, Gdx.graphics.getWidth() / 2 - Assets.playBTN.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Assets.playBTN.getHeight()  / 2- 200){
			public void action (){
				Gdx.app.exit();
			}
		});
	}
	public  static MenuInterfaceManager getInstance(){
		if(instance == null){
			instance = new MenuInterfaceManager();
		}
		return instance;
	}
	public void draw(){
		if(state != lastState){
			stateChanged();
			lastState = state;
		}
		switch(state){
		case 0:
			drawStartMenu();
			break;
		case 1:
			drawLevels();
			break;
		}
	}
	private void drawLevels() {
		batch.draw(Assets.backMenu, 0, 0, 1280, 720);
		batch.draw(Assets.logo,0, Gdx.graphics.getHeight() - Assets.logo.getHeight());
		for(int i = 0; i<b.size();i++){
			b.get(i).draw();
		}
	}
	private void drawStartMenu(){
		batch.draw(Assets.backMenu, 0, 0, 1280, 720);
		batch.draw(Assets.logo, Gdx.graphics.getWidth() / 2 - Assets.logo.getWidth() / 2, Gdx.graphics.getHeight() - Assets.logo.getHeight());
		for(int i = 0; i<b.size();i++){
			b.get(i).draw();
		}
		
	}
	public void setBatch(SpriteBatch b){
		batch = b;
	}
	public void nextState(){
		state++;
	}
	public void changeState(int s){
		state = s;
	}
	private void stateChanged(){
		switch(state){
		case 0:
			createMenuButtons();
			break;
		case 1:
			createLLB();
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			LevelManager.getInstance().setLevel(state -1 );
			break;
		}
	}
	private void createLLB() {
			b.add(new MenuButton(Assets.lvl1BackThumb,200,400){
				public void action(){
					MenuInterfaceManager.getInstance().changeState(2);
				}
			});
			b.add(new MenuButton(Assets.lvl2BackThumb,550,400){
				public void action(){
					MenuInterfaceManager.getInstance().changeState(3);
				}
			});
			b.add(new MenuButton(Assets.lvl3BackThumb,900,400){
				public void action(){
					MenuInterfaceManager.getInstance().changeState(4);
				}
			});
			b.add(new MenuButton(Assets.lvl4BackThumb,200,200){
				public void action(){
					MenuInterfaceManager.getInstance().changeState(5);
				}
			});
			b.add(new MenuButton(Assets.lvl5BackThumb,550,200){
				public void action(){
					MenuInterfaceManager.getInstance().changeState(6);
				}
			});
			b.add(new MenuButton(Assets.lvl6BackThumb,900,200){
				public void action(){
					MenuInterfaceManager.getInstance().changeState(7);
				}
			});
	}
}
