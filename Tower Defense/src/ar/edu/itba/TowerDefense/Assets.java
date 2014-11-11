package ar.edu.itba.TowerDefense;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets { // todas las texturas
	
	/* Texturas del menu */
	public static Texture backMenu = new Texture("MenuBack.fw.png");
	public static Texture logo = new Texture("LOGO.png");
	public static Texture playBTN = new Texture("jugarBTN.fw.png");
	public static Texture exitBTN = new Texture("SALIRBTN.fw.png");
	public static Texture backBTN = new Texture("volverBTN.fw.png");
	public static Texture retryBTN = new Texture("retryBTN.png");
	public static Texture menuBTN = new Texture("menuBTN.png");
	public static Texture lvl1BackThumb = new Texture("lvl1backThumb.jpg");
	public static Texture lvl2BackThumb = new Texture("lvl2backThumb.jpg");
	public static Texture lvl3BackThumb = new Texture("lvl3backThumb.jpg");
	public static Texture lvl4BackThumb = new Texture("lvl4backThumb.jpg");
	public static Texture lvl5BackThumb = new Texture("lvl5backThumb.jpg");
	public static Texture lvl6BackThumb = new Texture("lvl6backThumb.jpg");
	
	/*Fondos*/
	public static Texture[] backlvl = {new Texture("backlvl1.png"),new Texture("backlvl2.png"),new Texture("backlvl3.jpg"),new Texture("backlvl4.jpg"),new Texture("backlvl5.jpg"),new Texture("backlvl6.jpg")};
	
	/* Texturas de las torres*/
	public static Texture magicP = new Texture("magic.fw.png");
	public static Texture magicY = new Texture("YTower.fw.png");
	public static Texture archerT = new Texture("GTower.png");
	public static Texture magicAtackY = new Texture("BolaDeSabiduria.fw.png");
	public static Texture magicAtack = new Texture("magicAtack.fw.png");
	public static Texture area = new Texture("Area.png");
	public static TextureAtlas verde = new TextureAtlas("v.txt");
	public static TextureAtlas golpe = new TextureAtlas("golpemagia.txt");
	public static Texture[] newt = {new Texture("01.png"),new Texture("02.png"),new Texture("03.png")};
	public static Texture arrow = new Texture("arrow2.fw.png");
	public static Texture arrowMiss = new Texture("arrowMiss.fw.png");
	public static Texture sell = new Texture("sell.fw.png");
	public static Texture lvlup = new Texture("lvlup.fw.png");
	public static Texture lvlupNot = new Texture("lvlupNot2.fw.png");
	public static Texture blueT = new Texture("BlueTower2.fw.png");
	public static Texture frostattack = new Texture("blueAttack.fw.png");
	public static Texture iceEffect = new Texture("ice.fw.png");
	public static Texture iceFullEffect = new Texture("fullIce2.fw.png");
	public static Texture yellowEffect = new Texture("yellowEffect.fw.png");
	public static Texture crit = new Texture("crit.fw.png"); 
	
	/* Texturas de seleccion de torres */
	public static Texture tSelect = new Texture("towerselect2.fw.png"); 
	public static Texture newMagicP = new Texture("newmagicP.fw.png");
	public static Texture newArcher = new Texture("newarcher2.fw.png");
	public static Texture newMagicY = new Texture("newmagicY.fw.png");
	public static Texture newMagicB = new Texture("newmagicB.fw.png");
	public static Texture newMagicPNot = new Texture("newmagicPNot.fw.png");
	public static Texture newArcherNot = new Texture("newarcherNot.fw.png");
	public static Texture newMagicYNot = new Texture("newmagicYNot.fw.png");
	public static Texture newMagicBNot = new Texture("newmagicBNot2.fw.png");
	
	/* Texturas de las enemigos */
	public static Texture health = new Texture("health.fw.png");
	public static Texture healthb = new Texture("healthb.fw.png");
	public static Texture yetti = new Texture("Yetti.fw.png");
	public static Texture blueSpider = new Texture("AranaAzul.fw.png");
	public static Texture greenSpider = new Texture("AranaVerde.fw.png");
	public static Texture zombie = new Texture("zombiestest.fw.png");
	public static Texture blackGargoyle = new Texture("Gargola.fw.png");
	public static Texture assasin = new Texture("Assasins.fw.png");
	public static Texture redGargoyle = new Texture("GargolaR.fw.png");
	public static Texture ogros = new Texture("Ogros.fw.png");
	public static Texture gladiator = new Texture("Gladiador.fw.png");
	public static Texture demon = new Texture("Demon.fw.png");
	public static Texture armoredDemon = new Texture("DemonioArmadura.fw.png");
	public static Texture redGoblin = new Texture("goblinsRojos.fw.png");
	public static Texture brownGoblin = new Texture("goblins.fw.png");
	public static Texture tree = new Texture("Arbol.fw.png");
	public static Texture demonDeath = new Texture("demonsDeath3.fw.png");
	
	/* Texutras de informacion */
	public static Texture infoBack = new Texture("UnitInfoBack.fw.png");
	public static Texture infoBackFull = new Texture("infoBack.fw.png");
	public static Texture life = new Texture("life.png");
	public static Texture coins = new Texture("coins.png");
	public static Texture waves = new Texture("waves.png");
	public static Texture victory = new Texture("victory.png");
	public static Texture armor = new Texture("Resistance.png");
	public static Texture magicArmor = new Texture("magicResistance.png");
	public static Texture[] info = {new Texture("rayo laser.fw.png"),new Texture("infoArchers.fw.png"),new Texture("BDS.fw.png"), new Texture("infoFrio.fw.png")};
	public static Texture avatar = new Texture("avatars.fw.png");
	public static Texture avatarTowers = new Texture("avatarsTower.fw.png");
	public static Texture rangeIcon = new Texture("rangeIcon.png");
	public static Texture magicIcon = new Texture("magicIcon.png");
	public static Texture noMagicIcon = new Texture("noMagicIcon.png");
	
	/* Sonidos */
	public static Sound[] arrowRelease = {Gdx.audio.newSound(Gdx.files.internal("Sounds/ArrowRelease.mp3")),
		  Gdx.audio.newSound(Gdx.files.internal("Sounds/ArrowRelease2.mp3"))};
	public static Sound raySound = Gdx.audio.newSound(Gdx.files.internal("Sounds/raySound.mp3"));
	public static Sound newPurple = Gdx.audio.newSound(Gdx.files.internal("Sounds/NewPurple.mp3"));
	public static Sound newGreen = Gdx.audio.newSound(Gdx.files.internal("Sounds/NewArcher.mp3"));
	public static Sound newYellow = Gdx.audio.newSound(Gdx.files.internal("Sounds/NewYellow.mp3"));
	public static Sound newBlue = Gdx.audio.newSound(Gdx.files.internal("Sounds/NewBlue.mp3"));
	
	/* Constantes utiles */
	public static int MAGICW = 118;
	public static int MAGICH = 106;
	public static final int NEWTOWER_X = 51; 
	public static final int NEWTOWER_Y = 45;
	public static final int NEWTOWER_X2 = Assets.newArcher.getWidth(); 
	public static final int NEWTOWER_Y2 = Assets.newArcher.getHeight();
	public static final int SELL_X = sell.getWidth();
	public static final int SELL_Y = sell.getHeight();
	
	/* Referencias de las tropas*/
	public static final int TREE_REFERENCE = 0;	
	public static final int YETTI_REFERENCE = 1; 
	public static final int DEMON_REFERENCE = 2; 
	public static final int ARMORED_DEMON_REFERENCE = 3; 
	public static final int BLUE_SPIDER_REFERENCE = 4; 
	public static final int GREEN_SPIDER_REFERENCE = 5;
	public static final int BLACK_GARGOYLE_REFERENCE = 6; 
	public static final int RED_GARGOYLE_REFERENCE = 7; 
	public static final int GLADIATIOR_REFERENCE = 8; 
	public static final int ASSASSINS_REFERENCE = 9; 
	public static final int BASIC_REFERENCE = 10; 
	public static final int BASIC_DEMON_REFERENCE = 11;
	public static final int OGRE_REFERENCE = 12; 
	public static final int ZOMBIE_REFERENCE = 13;
	
	/* Fuentes */
	public static final BitmapFont FONT = new BitmapFont(Gdx.files.internal("font.fnt"));
	public static final BitmapFont SMALL_FONT = new BitmapFont(Gdx.files.internal("smallfont.fnt"));
}
