package Launcher;


import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ar.edu.itba.TowerDefense.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title ="Tower Defense";
		config.height= 720;
		config.width=1280;
		config.resizable = false;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.vSyncEnabled = false;
		config.addIcon("icon3.fw.png",FileType.Internal);
		config.addIcon("icon4.fw.png",FileType.Internal);
		new LwjglApplication(new Main(), config);
	}
}
