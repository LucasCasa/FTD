package ar.edu.itba.TowerDefense;

import java.util.ArrayList;

public abstract class CustomArrayList<T extends Logical,S extends Drawable> {
	
	protected ArrayList<T> back;
	protected ArrayList<S> front;
	
	public void add(T u, S uUI){
		back.add(u);
		front.add(uUI);
	}
	public void remove(int index){
		back.remove(index);
		front.remove(index);
	}
	public ArrayList<T> getBack(){
		return back;
	}
	public ArrayList<S> getFront(){
		return front;
	}
	public Connector<T,S> getBoth(int index){
		return new Connector<T ,S>(back.get(index),front.get(index));
	}
	public int size(){
		if(back.size() == front.size()){
			return back.size();
		}else{
			System.out.println("Eror en la cantidad de elementos de elementos");
			return 0;
		}
	}
	public void clear(){
		back.clear();
		front.clear();
	}
}
