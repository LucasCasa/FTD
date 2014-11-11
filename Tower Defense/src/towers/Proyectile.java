package towers;

import units.Unit;
import ar.edu.itba.TowerDefense.Assets;
import ar.edu.itba.TowerDefense.InterfaceManager;
import ar.edu.itba.TowerDefense.Logical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import effects.MovingEffect;

public class Proyectile implements Logical{
		private Vector2 pos;
		private Unit victim;
		private int damage;
		private boolean hit = false;
		private float speed;
		private double angle;
		private Vector2 sp;
		float last;
		float timetoO = 0;
		private boolean special;
		private Boolean parabolic;
		private boolean critical;
		private boolean miss = false;
		private boolean overVictim = false;
		
		public Proyectile(float x, float y, Unit uni, int damage, boolean ultimate, boolean parabolic){
			pos = new Vector2(x,y);
			victim = uni;
			this.damage = damage;
			speed = 400;
			angle = 0;
			this.parabolic = parabolic;
			if(parabolic){
			sp = new Vector2(calcvelx(),500);
			}
			last = victim.getSpeed().x;
			special = ultimate;
			
		}
		public void update(){
			if(last != victim.getSpeed().x){
				calcvelx();
				last = victim.getSpeed().x;
			}
			if(parabolic)
				parabolicmovement();
			else
				movement();
			
		}
		public void movement(){
			float destx = victim.getCenterX();
			float desty = victim.getCenterY();
			Vector2 enemy = new Vector2(destx,desty);
			
			float deltaX = destx - (pos.x + 14);
			float deltaY = desty - (pos.y + 14);
			angle = Math.atan2(deltaY , deltaX);
			
			pos.x += speed * Math.cos(angle)* Gdx.graphics.getDeltaTime();
			pos.y += speed * Math.sin(angle)* Gdx.graphics.getDeltaTime();
			Vector2 aux = new Vector2(pos.x + 10, pos.y);
			if(aux.dst(enemy) <= 30){
				victim.hit(damage);	
				hit = true;
			}
			if(victim == null){
				hit = true;
			}
		}
		public void parabolicmovement(){
			if(pos.y > victim.getTopY()){
				overVictim = true;
			}
			if(overVictim == true && pos.y < victim.getY()){
				miss = true;
			}
			pos.x+= sp.x * Gdx.graphics.getDeltaTime();
			pos.y += sp.y * Gdx.graphics.getDeltaTime();
			sp.set(sp.x, sp.y - Gdx.graphics.getDeltaTime() * 800);
			
			angle = Math.atan2(sp.y, sp.x);
			float destx = victim.getCenterX();
			float desty = victim.getCenterY();
			

			Vector2 enemy = new Vector2(destx,desty);
			Vector2 aux = new Vector2(pos.x + 10, pos.y);
			if(aux.dst(enemy) <= 30){
				if(special == true && Math.random() >= 0.5f){
					MovingEffect e = new MovingEffect(getVictim(),Assets.crit,false,0.2f);
					InterfaceManager.e.add(e);
					victim.hit(damage*2);
				
				}else{
					
					victim.hit(damage);
				}
				hit = true;
			}
			if(victim == null){
				hit = true;
			}
		}
		public float calcvelx(){
			int destx = victim.getX() +15;
			int desty = victim.getY() +24;
			float deltaY = pos.y - desty;
			
			double time = resolvetime(-400,500,deltaY);
			if(Double.isNaN(time)){
				parabolic = false;
				return 0;
			}
			double deltaX = destx - pos.x + victim.getSpeed().x * time;
			float speedx = (float) (deltaX/ time);
			
			return speedx;
		}
		private double resolvetime(double a, double b, double c){
			double b2 = Math.pow(b, 2);
			double aux = Math.pow(b2 - 4*a*c, 0.5f);
			double option1 = (-b + aux) / (2*a);
			double option2 = (-b - aux) / (2*a);
			if(option1 > option2){
			
					return option1;
			
				
			} else {
				return option2;
			}
		}
		
		public boolean getHit(){
			return hit;
		}
		public Unit getVictim(){
			return victim;
		}
		public float getX(){
			return pos.x;
		}
		public float getY(){
			return pos.y;
		}
		public double getAngle(){
			return angle;
		}
		public int compareTo(Proyectile other){
			return 0;
		}
		public void setCritical(boolean c){
			critical = c;
		}
		public boolean getCritical(){
			return critical;
		}
		public boolean hasMiss(){
			return miss;
		}
}
