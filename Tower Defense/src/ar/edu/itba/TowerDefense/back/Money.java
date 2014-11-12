package ar.edu.itba.TowerDefense.back;

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
	 * Subtracts a given amount of money
	 * and returns if the operation was achievable
	 * @param amount
	 * @return subtract successful
	 */
	public boolean subtract(int amount){
		if(this.amount >= amount ){
			this.amount-= amount;
			return true;
		}else {
			System.out.println( this.amount + " ERROR " + amount);
			return false;
		}
	}
	/**
	 * 
	 * @param amount
	 * @return if the current money is greater than what is to be subtracted
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
