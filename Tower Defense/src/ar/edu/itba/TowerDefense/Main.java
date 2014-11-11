package ar.edu.itba.TowerDefense;


import ar.edu.itba.TowerDefense.back.LevelManager;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.front.InterfaceManager;
import ar.edu.itba.TowerDefense.front.MyInputProcessor;
import ar.edu.itba.TowerDefense.menu.MenuInterfaceManager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Main extends ApplicationAdapter {
	public static final int GRIDSIZE = 72;
	SpriteBatch batch;
	Array<Sprite> d;
	BitmapFont font;
	public static OrthographicCamera cam;
	public boolean inMenu = true;
	@Override
	public void create () {
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		InterfaceManager.getInstance().batch = batch;
		MenuInterfaceManager.getInstance().batch = batch;
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.setScale(0.5f);
		MyInputProcessor MYP = new MyInputProcessor();
		Gdx.input.setInputProcessor(MYP); // The input manager
		d = Assets.verde.createSprites();
		

	}

	@Override
	public void render () {
		
		
		
		
		cam.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin(); // All draws must be between batch.begin and batch.end
		LevelManager.getInstance().update();
		CharSequence str = "FPS: " + Gdx.graphics.getFramesPerSecond();
		font.draw(batch, str, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() -50);
		batch.end();
	}

}
