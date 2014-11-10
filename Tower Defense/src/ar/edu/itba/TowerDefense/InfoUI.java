package ar.edu.itba.TowerDefense;

import towers.Tower;
import units.Unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InfoUI {

	private SpriteBatch batch;
	private BitmapFont font;
	private Unit u;
	private Tower t;
	private float infoY;
	private int pos1,pos2,pos3;
	
	public InfoUI(BitmapFont font){
		this.font = font;
		u = null;
		t = null;
		infoY = -100;
		pos1 = 5;
		pos2 = Gdx.graphics.getWidth() / 2 - Assets.infoBack.getWidth() / 2;
		pos3 = Gdx.graphics.getWidth() - Assets.infoBack.getWidth() - 20;
	}
	public void draw(){
		batch = InterfaceManager.getInstance().batch;
		drawInfo();
		if(u != null){
			drawInfoUnit();
		}else if(t != null){
			drawInfoTower();
		}
		if(infoY < 0){
			infoY+= 400*Gdx.graphics.getDeltaTime();
		}else{
			infoY = 0;
		}

	}
	private void drawInfoTower() {
		InterfaceManager.getInstance().batch.draw(Assets.infoBack,pos1,infoY);
		InterfaceManager.getInstance().batch.draw(Assets.infoBack,pos2,infoY);
		InterfaceManager.getInstance().batch.draw(Assets.infoBack,pos3,infoY);
		
		InterfaceManager.getInstance().batch.draw(Assets.avatarTowers, pos1 + 20, 5 + infoY, t.ID * 37, 0, 37, 39);
		InterfaceManager.getInstance().batch.draw(Assets.rangeIcon, pos3 + 20, 20 + infoY);
		if(t.ID != 1){
			InterfaceManager.getInstance().batch.draw(Assets.magicIcon, pos2 + 30, 10 + infoY);
		}else{
			InterfaceManager.getInstance().batch.draw(Assets.noMagicIcon, pos2 + 30, 10 + infoY);
		}
		
		font.draw(batch,"Nivel: " +  t.getLevel() + "" , pos1 + 80, 40 + infoY);
		font.draw(batch, t.getDamage()+ "" , pos2 + 65, 40 + infoY);
		font.draw(batch, t.getRange() + "" , pos3 + 65, 40 + infoY);
		
	}
	public void drawInfoUnit(){
		String aux = (int)u.getHealth() + " / " + (int)u.getTotalHealth();
		InterfaceManager.getInstance().batch.draw(Assets.infoBack,pos1,infoY);
		InterfaceManager.getInstance().batch.draw(Assets.infoBack,pos2,infoY);
		InterfaceManager.getInstance().batch.draw(Assets.infoBack,pos3,infoY);
		
		InterfaceManager.getInstance().batch.draw(Assets.magicArmor,pos3 + 20 ,20 + infoY);
		InterfaceManager.getInstance().batch.draw(Assets.life,pos2 + 30,10 + infoY);
		InterfaceManager.getInstance().batch.draw(Assets.avatar, pos1 + 20, 5 + infoY,u.getID() * 53,0,53,53);
		
		font.draw(batch, u.getName() , pos1 + 80, 40 + infoY);
		font.draw(batch, aux, pos2 + 65, 40 + infoY);
		
		if(u.getHealth() <= 0){
			u = null;
		}
	}
	public void drawInfo(){
		InterfaceManager.getInstance().batch.draw(Assets.infoBackFull,20,Gdx.graphics.getHeight() - Assets.infoBackFull.getHeight() / 2 - 17,Assets.infoBackFull.getWidth() / 2, Assets.infoBackFull.getHeight() / 2);
		InterfaceManager.getInstance().batch.draw(Assets.coins,50,Gdx.graphics.getHeight() - 30 - 26, 26, 26);
		font.draw(batch,String.valueOf(Money.getInstance().getAmount()), 100, Gdx.graphics.getHeight() - 30);
		InterfaceManager.getInstance().batch.draw(Assets.life,50,Gdx.graphics.getHeight() - 60 - Assets.life.getHeight());
		font.draw(batch,String.valueOf(GameEngine.getInstance().life), 100, Gdx.graphics.getHeight() - 60);
		InterfaceManager.getInstance().batch.draw(Assets.waves,50,Gdx.graphics.getHeight() - 90 - Assets.waves.getHeight());
		font.draw(batch,GameEngine.getInstance().waveNumber + " / " + GameEngine.getInstance().maxWave,100,Gdx.graphics.getHeight() - 90);
	}
	public void setUnit(Unit u){
		infoY = -100;
		this.u = u;
		t = null;
	}
	public void setTower(Tower t){
		u = null;
		infoY = -100;
		this.t = t;
	}
	
}
