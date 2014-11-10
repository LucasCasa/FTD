package towers;

import java.util.ArrayList;

import ar.edu.itba.TowerDefense.Assets;
import ar.edu.itba.TowerDefense.Connector;
import ar.edu.itba.TowerDefense.InterfaceManager;
import ar.edu.itba.TowerDefense.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GreenTowerUI implements TowerDrawable {

	private int posX;
	private int posY;
	private float drawTime;
	
	private Array<Sprite> archer;
	private ArrayList<Connector<Proyectile,ProyectileUI>> p;
	private Vector2 archersPos; 
	private int minRange;
	private int maxRange;
	private int draw[];
	private boolean archerAttacking[];
	private boolean startAttack;
	private boolean attacking;
	private GreenTower tower;
	private SpriteBatch batch;
	private int pos = 0;
	
	public GreenTowerUI(int posX, int posY,GreenTower tower){
		this.posX = posX;
		this.posY = posY;
		this.batch = InterfaceManager.getInstance().batch;
		this.tower = tower;
		archer = Assets.verde.createSprites();
		draw = new int[2];
		draw[0] = 9;
		draw[1] = 9;
		archersPos = new Vector2(posX + Main.GRIDSIZE / 2, posY + 50);
		archerAttacking = new boolean[]{true,false};
	}
	public void draw(){
		drawTime += Gdx.graphics.getDeltaTime();
		if( drawTime >= 0.05f && (attacking || tower.getAttacking())){
			drawTime = 0;
			if(tower.getOrientationY() < 0){
				minRange = 0;
				maxRange = 8;
			}else{
				minRange = 9;
				maxRange = 17;
			}
			setAttacker();
			drawArcher(0);
			drawArcher(1);
		}
		archer.get(draw[0]).setScale((float) Main.GRIDSIZE / Assets.archerT.getWidth());
		archer.get(draw[1]).setScale((float) Main.GRIDSIZE / Assets.archerT.getWidth());
		
		//batch.draw(Assets.area,tower.getAttackPos().x - tower.getRange(),tower.getAttackPos().y - tower.getRange(), tower.getRange() * 2 ,tower.getRange() * 2);

		batch.draw(Assets.archerT, posX, posY,Main.GRIDSIZE, Main.GRIDSIZE * ((float) Assets.archerT.getHeight() / Assets.archerT.getWidth()));
		
			
			archer.get(draw[0]).setCenter(archersPos.x - 3 , archersPos.y);
			archer.get(draw[0]).draw(batch);
			archer.get(draw[1]).setCenter(archersPos.x + 10, archersPos.y);
			archer.get(draw[1]).draw(batch);
		
		
	}
	public void setAttacker(){
		startAttack = tower.getAttacking();
		if(startAttack){
			attacking = true;
			tower.setAttacking(false);
			archerAttacking[this.pos] = true;
			pos = Math.abs(pos - 1);
		}
	}
	public void drawArcher(int pos){
		if(archerAttacking[pos]){
			draw[pos]++;
			if(draw[pos] > maxRange){
				draw[pos] = minRange;
				archerAttacking[pos] = false;
				Assets.arrowRelease[pos].play();
			}
			if(draw[pos] < minRange){
				draw[pos] = minRange;
			}
		}
		if(tower.getOrientationX() < 0){
			archer.get(draw[pos]).setFlip(false, archer.get(draw[pos]).isFlipY());
		} else{
			archer.get(draw[pos]).setFlip(true, archer.get(draw[pos]).isFlipY());
		}
	}
	public void drawAttack(){
		p = tower.getProyectiles();
		for(Connector<Proyectile,ProyectileUI> pro : p){
			if(pro != null){
				pro.getFront().draw();
			}
		}
	}
	@Override
	public void stopSound() {
		// TODO Auto-generated method stub
		
	}
}
