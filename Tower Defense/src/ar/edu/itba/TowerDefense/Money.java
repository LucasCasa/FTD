package ar.edu.itba.TowerDefense;

public class Money {
	int amount;
	private static final Money purse = new Money(150);
	private Money(int amount){
		this.amount = amount;
	}
	public int getAmount(){
		return amount;
	}
	public void add(int amount){
		if(amount >= 0){
		this.amount+= amount;
		}
	}
	/**
	 * sustrae una cantidad de plata y avisa si pudo o no.
	 * @param amount
	 * @return si se pudo sustraer la plata
	 */
	public boolean subtract(int amount){
		if(this.amount >= amount ){
			this.amount-= amount;
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 
	 * @param amount
	 * @return si la cantidad de plata es mayor que lo que se quiere sacar
	 */
	public boolean canSubstract(int amount){
		if(this.amount >= amount){
			return true;
		}else{
			return false;
		}
	}
	public static Money getInstance(){
		return purse;
	}
}
