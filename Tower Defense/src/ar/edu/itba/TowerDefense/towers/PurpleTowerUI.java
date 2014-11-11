package ar.edu.itba.TowerDefense.towers;

import ar.edu.itba.TowerDefense.Main;
import ar.edu.itba.TowerDefense.front.Assets;
import ar.edu.itba.TowerDefense.front.InterfaceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PurpleTowerUI implements TowerDrawable{
	private Array<Sprite> d;
	private TextureRegion TX = new TextureRegion();
	private float drawTime;
	private float drawTimeAttack;
	private int posX;
	private int posY;
	private int drawX;
	private int drawY;
	private int attackX;
	private boolean chargingAttack;
	private boolean attacking;
	private boolean ultimate;
	private float drawParticle1;
	private float drawParticle2;
	private SpriteBatch batch;
	private PurpleTower tower;
	private long soundID;
	public PurpleTowerUI(int posX, int posY, PurpleTower tower){
		this.posX = posX;
		this.posY = posY;
		this.batch = InterfaceManager.getInstance().batch;
		this.tower = tower;
		drawX = 0;
		drawY = 0;
		attackX = 0;
		this.TX.setTexture(Assets.magicAtack);
	    this.TX.setRegion(0, 0, 176, 14);
	    this.d = Assets.golpe.createSprites();
	    soundID = Assets.raySound.play(0.01f);
	    Assets.raySound.pause(soundID);
	}
	public void draw(){
		drawTime+= Gdx.graphics.getDeltaTime();
		drawTimeAttack += Gdx.graphics.getDeltaTime();
		chargingAttack = tower.getChargingAttack();
		if(drawTime >= tower.getChargingTime() / 14  && chargingAttack && !attacking){                         
			drawTime = 0;
			drawX+=118; 
				
			if(drawX == 1180){						
				drawX = 0;							
				drawY =Math.abs(drawY - 106);		
			}
		}											
									
		if(attacking && drawTime >= 0.1f){
			drawTime = 0;
			drawX+=118;
			if(drawX == 590){
				drawX = 236;
			}
		}
		if(drawY == 106 && drawX == 472 && attacking == false){
			attacking = true;
			tower.setAttacking(true);
			drawX = 236;
		}
		if(drawX >= 1062 && drawY == 106){			
			drawX =0;								
			drawY = 0;								
			}
		if(attacking == false && chargingAttack == false){
			drawX = 0;
			drawY = 0;
		}
		if(drawTimeAttack >= 0.1f){			
		TX.setRegionX(attackX);				
		attackX+=176;						
		drawTimeAttack = 0;					
		}											
		if(attackX == 528){								
			attackX =0;									
		}
		attacking = tower.getAttacking();
		batch.draw(Assets.magicP,posX,posY,Main.GRIDSIZE,Main.GRIDSIZE * (106f / 118), (int) drawX,(int) drawY,118,106,false,false); // dibuja la torre
		if(attacking){
			Assets.raySound.resume(soundID);
			Assets.raySound.setLooping(soundID, true);
			ultimate = tower.getUltimate();
		}else{
			Assets.raySound.pause(soundID);
		}

	}
	
	public void drawAttack(){
		if(attacking == true){
			Vector2 attack = tower.getAttack1();
			double rdist = tower.getDistanceToTarget1();
			double ang = tower.getAngleToTarget1();
			batch.draw(TX, tower.getRayStart().x, tower.getRayStart().y, 0, 10, (int)rdist,14, 1, 1, (int) ang); // dibuja el ataque
			if(ultimate && tower.getAttacking2()){
				Vector2 attack2 = tower.getAttack2();
				double rdist2 = tower.getDistanceToTarget2();
				double ang2 = tower.getAngleToTarget2();
				batch.draw(TX, tower.getRayStart().x, tower.getRayStart().y, 0, 10, (int)rdist2,14, 1, 1, (int) ang2); // dibuja el ataque
				d.get((int) drawParticle2).setCenter(attack2.x, attack2.y);
				d.get((int) drawParticle2).draw(batch);
			}
			d.get((int) drawParticle1).setCenter(attack.x, attack.y);
			d.get((int) drawParticle1).draw(batch);
			drawParticle1+= 0.5;
			drawParticle2+= 0.5;
			if(drawParticle1 == 10){
				drawParticle1 = 0;
			}
			if(drawParticle2 == 10){
				drawParticle2 = 0;
			}
		}
	}
	public void stopSound(){
		Assets.raySound.stop(soundID);
	}
	
}
