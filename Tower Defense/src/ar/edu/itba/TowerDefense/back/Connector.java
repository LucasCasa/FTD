package ar.edu.itba.TowerDefense.back;

import ar.edu.itba.TowerDefense.front.Drawable;

public class Connector<T extends Logical,S extends Drawable>{
	private T back;
	private S front;
	public Connector(T back, S front){
		this.back = back;
		this.front = front;
	}
	public void update(){
		back.update();
		front.draw();
	}
	public T getBack(){
		return back;
	}
	public S getFront(){
		return front;
	}
}
