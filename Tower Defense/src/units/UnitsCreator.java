package units;


import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ar.edu.itba.TowerDefense.Assets;
import ar.edu.itba.TowerDefense.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector3;

public class UnitsCreator {
	
	private static final UnitsCreator u = new UnitsCreator();
	private Random rand = new Random();
	private final int totalUnitTypes = 15; // The amount of different unit types
	
	private UnitsCreator(){
	}
	/**
	 * Loads an XML file with the amount of waves,
	 * the types of troop and its respective amounts
	 * and the distance between unit spawns
	 * @param Current level
	 * @param Current wave
	 * @param Units paths
	 */
	public void loadWave(int level, int waveNumber, Vector3[][] paths){
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
		    builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		    e.printStackTrace();  
		}
		
		try {
			FileHandle f = Gdx.files.internal("Waves.xml");
		    Document xmlDocument = builder.parse(f.read());
		    XPath xPath =  XPathFactory.newInstance().newXPath();
		       
		    createUnit(level,waveNumber,xPath,xmlDocument,paths);
			   
			   
		} catch (SAXException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
			
			
	}
	/**
	 * Reads the XML file and loads the units
	 * @param level
	 * @param waveNumber
	 * @param xPath
	 * @param xmlDocument
	 * @param paths
	 * @throws NumberFormatException
	 * @throws XPathExpressionException
	 */
	public void createUnit(int level, int waveNumber,XPath xPath, Document xmlDocument,Vector3[][] paths) throws NumberFormatException, XPathExpressionException {
		int posx = 0;
		if(GameEngine.getInstance().maxWave == 0){
			GameEngine.getInstance().maxWave = Integer.parseInt(xPath.compile("//Level"+ level + "/@waves").evaluate(xmlDocument));
		}
		boolean check;
		for(int j = 0; j<totalUnitTypes;j++){
			String expression = "//Level"+ level +"/Wave" + waveNumber + "//@id="+j;
			check = Boolean.parseBoolean(xPath.compile(expression).evaluate(xmlDocument));
			if(check){
				expression ="//TowerDefense/Level"+ level +"/Wave" + waveNumber + "//*[@id = "+ j +"]/text()";
				int quantity = Integer.parseInt((xPath.compile(expression).evaluate(xmlDocument)));
				expression = "//TowerDefense/Level"+ level +"/Wave" + waveNumber + "//*[@id = "+ j +"]/@jump";
				int jump = Integer.parseInt((xPath.compile(expression).evaluate(xmlDocument)));
				int unitReference = j;
				int top = GameEngine.getInstance().getUnits().size();
				for(int i = top; i< quantity + top;i++){
					int aux = rand.nextInt(2);
					int mx,my;
					if(paths[aux][0].z == 1 ||paths[aux][0].z == 2 ){
						mx = 1;
						my = 0;
					}else{
						mx = 0;
						my = 1;
					}
					Unit u = createUnit(unitReference,(int)paths[aux][0].x - i*jump*mx,(int)paths[aux][0].y + i*jump*my);
					u.setnodes(4, paths[aux]);
					GameEngine.getInstance().getUnits().add(u);
					UnitUI uUI = createUnitUI(unitReference,posx, 350, GameEngine.getInstance().getUnits().get(i)); 
					GameEngine.getInstance().getUnitArray().add(u,uUI);
					posx-=jump;
				}
			} else {
			
			}
		}
	}
	/**
	 * Calls the methods that will create the units based on the ID received
	 * @param unitReference
	 * @param posx
	 * @param posy
	 * @return Unit
	 */
	public Unit createUnit(int unitReference, int posx, int posy){
		switch(unitReference){
		case Assets.YETTI_REFERENCE:
			return createYetti(posx, posy);
		case Assets.DEMON_REFERENCE:
			return createDemon(posx,posy);
		case Assets.BLUE_SPIDER_REFERENCE:
			return createBlueSpider(posx, posy);
		case Assets.GREEN_SPIDER_REFERENCE:
			return createGreenSpider(posx, posy);
		case Assets.BLACK_GARGOYLE_REFERENCE:
			return createBlackGargoyle(posx, posy);
		case Assets.RED_GARGOYLE_REFERENCE:
			return createRedGargoyle(posx, posy);
		case Assets.TREE_REFERENCE:
			return createTree(posx, posy);
		case Assets.ZOMBIE_REFERENCE:
			return createZombie(posx, posy);
		case Assets.OGRE_REFERENCE:
			return createOgre(posx, posy);
		case Assets.BASIC_DEMON_REFERENCE:
			return createRedGoblin(posx, posy);
		case Assets.BASIC_REFERENCE:
			return createBrownGoblin(posx, posy);
		case Assets.ASSASSINS_REFERENCE:
			return createAssasin(posx, posy);
		case Assets.GLADIATIOR_REFERENCE:
			return createGladiator(posx,posy);
		case Assets.ARMORED_DEMON_REFERENCE:
			return createArmoredDemon(posx,posy);
		default:
			return null;
		}
		
	}
	/**
	 * Calls the methods that will create the units UI based on the ID received
	 * @param unitReference
	 * @param posx
	 * @param posy
	 * @param Unit
	 * @return UnitUI
	 */
	public UnitUI createUnitUI(int unitReference, int posx, int posy, Unit u){
		switch(unitReference){
		case Assets.YETTI_REFERENCE:
			return createYettiUI(posx, posy,u);
		case Assets.DEMON_REFERENCE:
			return createDemonUI(posx,posy,u);
		case Assets.BLUE_SPIDER_REFERENCE:
			return createBlueSpiderUI(posx, posy,u);
		case Assets.GREEN_SPIDER_REFERENCE:
			return createGreenSpiderUI(posx, posy,u);
		case Assets.BLACK_GARGOYLE_REFERENCE:
			return createBlackGargoyleUI(posx, posy,u);
		case Assets.RED_GARGOYLE_REFERENCE:
			return createRedGargoyleUI(posx, posy,u);
		case Assets.TREE_REFERENCE:
			return createTreeUI(posx, posy,u);
		case Assets.ZOMBIE_REFERENCE:
			return createZombieUI(posx, posy,u);
		case Assets.OGRE_REFERENCE:
			return createOgreUI(posx, posy,u);
		case Assets.BASIC_DEMON_REFERENCE:
			return createRedGoblinUI(posx, posy,u);
		case Assets.BASIC_REFERENCE:
			return createBrownGoblinUI(posx, posy,u);
		case Assets.ASSASSINS_REFERENCE:
			return createAssasinUI(posx, posy,u);
		case Assets.GLADIATIOR_REFERENCE:
			return createGladiatorUI(posx,posy,u);
		case Assets.ARMORED_DEMON_REFERENCE:
			return createArmoredDemonUI(posx,posy,u);
		default:
			return null;
		}
		
	}
	public static UnitsCreator getInstance(){
		return u;
	}
	public  Unit createAssasin( int posx,int  posy){
		Unit u = new Unit(posx, posy, 16,22,175,50,1,15,Assets.ASSASSINS_REFERENCE,"Assasin");
		return u;
	}
	public UnitUI createAssasinUI(int posx, int posy, Unit u){
		float[] width = {16,16,16,16,20};
		float[] heigth = {22,22,22,21,18};
		int[] drawPosY = {60,0,0,30,90};
		int[] drawPosX = {16,16,16,16,20}; 
		float[] animcant = {12,12,12,12,6};
		UnitUI ui = new UnitUI(u,Assets.assasin,Assets.assasin,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.1f,175);
		return ui;
	}
	public  Unit createYetti( int posx,int  posy){
		Unit u = new Unit(posx, posy, 50,45,500,20,0.8f,60,Assets.YETTI_REFERENCE,"Yetti");
		return u;
	}
	public UnitUI createYettiUI(int posx, int posy, Unit u){
		float[] width = {52,53,53,52,54};
		float[] heigth = {47,48,48,47,47};
		int[] drawPosY = {100,0,0,50,150};
		int[] drawPosX = {52,53,53,52,54}; 
		float[] animcant = {12,16,16,13,11};
		UnitUI ui = new UnitUI(u,Assets.yetti,Assets.yetti,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.1f,500);
		return ui;
	}
	public  Unit createOgre( int posx,int  posy){
		Unit u = new Unit(posx, posy, 35,37,250,40,0.6f,50,Assets.OGRE_REFERENCE,"Ogro");
		return u;
	}
	
	public UnitUI createOgreUI(int posx, int posy, Unit u){
		float[] width = {35,36,36,34,45};
		float[] heigth = {37,40,40,38,38};
		int[] drawPosY = {100,0,0,50,150};
		int[] drawPosX = {35,36,36,34,45}; 
		float[] animcant = {13,14,14,13,10};
		UnitUI ui = new UnitUI(u,Assets.ogros,Assets.ogros,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.1f,250);
		return ui;
	}
	
	public  Unit createZombie( int posx,int  posy){
		Unit u = new Unit(posx, posy, 18,24,80,40,1,10,Assets.ZOMBIE_REFERENCE,"Zombie");
		return u;
	}
	
	public UnitUI createZombieUI(int posx, int posy, Unit u){
		float[] width = {16,18,18,17,25};
		float[] heigth = {23,25,25,25,28};
		int[] drawPosY = {60,0,0,30,90};
		int[] drawPosX = {16,18,18,17,25}; 
		float[] animcant = {12,12,12,12,9};
		UnitUI ui = new UnitUI(u,Assets.zombie,Assets.zombie,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.1f,80);
		return ui;
	}
	
	public Unit createDemon(int posx, int posy){
		Unit u = new Unit(posx, posy,27,37,120,40,0.9f,15,Assets.DEMON_REFERENCE,"Demonio");
		return u;
	}
	public UnitUI createDemonUI(int posx, int posy, Unit u){
		float[] width = {27,27,27,27,41};
		float[] heigth = {35,37,37,35,35};
		int[] drawPosY = {80,0,0,40,0};
		int[] drawPosX = {27,27,27,27,82};
		float[] animcant = {12,12,12,12,12};
		UnitUI ui = new UnitUI(u,Assets.demon,Assets.demonDeath,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.05f,120);
		return ui;
	}
	public Unit createBlueSpider(int posx, int posy){
		return new Unit(posx, posy, 54,39,150,50,0.8f,20,Assets.BLUE_SPIDER_REFERENCE,"Araña Azul");
	}
	public UnitUI createBlueSpiderUI(int posx, int posy, Unit u){
		float[] width = {50,54,54,50,65};
		float[] heigth = {45,39,39,50,42};
		int[] drawPosY = {100,0,0,50,150};
		int[] drawPosX = {50,54,54,50,65};
		float[] animcant = {13,13,13,13,6};
		return new UnitUI(u,Assets.blueSpider,Assets.blueSpider,posx,posy,width,heigth,drawPosX, drawPosY,animcant,0.05f,150);
	}
	public Unit createGreenSpider(int posx, int posy){
		return new Unit(posx, posy, 44,33,100,60,1,20,Assets.GREEN_SPIDER_REFERENCE,"Araña Verde");
	}
	public UnitUI createGreenSpiderUI(int posx, int posy, Unit u){
		float[] width = {43,46,46,44,56};
		float[] heigth = {39,38,38,33,36};
		int[] drawPosY = {100,0,0,50,150};
		int[] drawPosX = {50,50,50,50,60};
		float[] animcant = {13,13,13,13,15};
		return new UnitUI(u,Assets.greenSpider,Assets.greenSpider,posx,posy,width,heigth,drawPosX, drawPosY,animcant,0.05f,100);
	}
	
	public Unit createBlackGargoyle(int posx, int posy){
		return new Unit(posx, posy, 33,42,80,60,0.5f,10,Assets.BLACK_GARGOYLE_REFERENCE,"Gargola Negra");
	}
	public UnitUI createBlackGargoyleUI(int posx, int posy,Unit u){
		float[] width = {46,33,33,46,47};
		float[] heigth = {40,42,42,41,47};
		int[] drawPosY = {100,0,0,50,150};
		int[] drawPosX = {46,33,33,46,47};
		float[] animcant = {7,7,7,7,6};
		return new UnitUI(u,Assets.blackGargoyle,Assets.blackGargoyle,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.05f,80);
	}
	public Unit createRedGoblin(int posx, int posy){
		return new Unit(posx, posy, 17,22,50,50,1f,5,Assets.BASIC_DEMON_REFERENCE,"Goblin Rojo");
	}
	public UnitUI createRedGoblinUI(int posx, int posy, Unit u){
		float[] width = {16,18,18,16,41};
		float[] heigth = {22,22,22,22,35};
		int[] drawPosY = {60,0,0,30,0};
		int[] drawPosX = {16,18,18,16,82};
		float[] animcant = {12,12,12,12,12};
		return new UnitUI(u,Assets.redGoblin,Assets.demonDeath,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.05f,50);
	}
	public Unit createBrownGoblin(int posx, int posy){
		return new Unit(posx, posy, 20,24,60,50,1f,5,Assets.BASIC_REFERENCE,"Goblin Comun");
	}
	public UnitUI createBrownGoblinUI(int posx, int posy, Unit u){
		float[] width = {17,20,20,19,23};
		float[] heigth = {23,25,25,23,26};
		int[] drawPosY = {60,0,0,30,90};
		int[] drawPosX = {17,20,20,19,23};
		float[] animcant = {12,12,12,12,5};
		return new UnitUI(u,Assets.brownGoblin,Assets.brownGoblin,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.05f,60);
	}
	
	public Unit createRedGargoyle(int posx, int posy){
		return new Unit(posx, posy, 36,48,60,80,0.4f,20,Assets.RED_GARGOYLE_REFERENCE,"Gargola Roja");
	}

	public UnitUI createRedGargoyleUI(int posx, int posy, Unit u){
		float[] width = {48,36,36,48,41};
		float[] heigth = {47,48,48,48,35};
		int[] drawPosY = {100,0,0,50,0};
		int[] drawPosX = {48,36,36,48,82};
		float[] animcant = {7,7,7,7,12};
		return new UnitUI(u,Assets.redGargoyle,Assets.demonDeath,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.05f,60);
	}
	public Unit createTree(int posx, int posy){
		return new Unit(posx, posy, 36,47,1000,20,0.2f,200,Assets.TREE_REFERENCE,"Arbol");
	}
	public UnitUI createTreeUI(int posx, int posy, Unit u){
		float[] width = {35,36,36,35,34};
		float[] heigth = {48,47,47,47,38};
		int[] drawPosY = {100,0,0,50,150};
		int[] drawPosX = {35,36,36,35,34};
		float[] animcant = {8,8,8,8,6};
		return new UnitUI(u,Assets.tree,Assets.tree,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.1f,1000);
	}
	public Unit createGladiator(int posx, int posy){
		return new Unit(posx, posy, 33,40,120,50,1f,20,Assets.GLADIATIOR_REFERENCE,"Gladiador");
	}
	public UnitUI createGladiatorUI(int posx, int posy, Unit u){
		float[] width = {33,33,33,31,37};
		float[] heigth = {42,40,40,41,36};
		int[] drawPosY = {100,0,0,50,150};
		int[] drawPosX = {33,33,33,31,37};
		float[] animcant = {13,12,12,12,9};
		return new UnitUI(u,Assets.gladiator,Assets.gladiator,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.1f,120);
	}
	public Unit createArmoredDemon(int posx, int posy){
		return new Unit(posx, posy, 30,39,150,35,0.4f,40,Assets.ARMORED_DEMON_REFERENCE,"Demonio con Armadura");
	}
	public UnitUI createArmoredDemonUI(int posx, int posy, Unit u){
		float[] width = {30,30,30,30,40};
		float[] heigth = {38,39,39,38,42};
		int[] drawPosY = {100,0,0,50,150};
		int[] drawPosX = {30,30,30,30,40};
		float[] animcant = {12,13,13,12,8};
		return new UnitUI(u,Assets.armoredDemon,Assets.armoredDemon,posx,posy,width,heigth,drawPosX,drawPosY,animcant,0.1f,150);
	}
}
