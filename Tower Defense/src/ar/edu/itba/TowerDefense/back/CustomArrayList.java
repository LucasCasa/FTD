package ar.edu.itba.TowerDefense.back;

import java.util.ArrayList;

import ar.edu.itba.TowerDefense.front.Drawable;

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
	public T getBack(int index){
		return back.get(index);
	}
	public S getFront(int index){
		return front.get(index);
	}
	public ArrayList<S> getFront(){
		return front;
	}
	public void remove(T obj){
		int i = back.indexOf(obj);
		remove(i);
	}
	public boolean contains(T obj){
		return back.contains(obj);
	}
	public Connector<T,S> getBoth(int index){
		return new Connector<T ,S>(back.get(index),front.get(index));
	}
	public int size(){
		if(back.size() == front.size()){
			return back.size();
		}else{
			System.out.println("ERORR EN EL BALANCE");
			return 0;
		}
	}
	public void clear(){
		back.clear();
		front.clear();
	}
	private void balanceArray(){
		
	}
}
