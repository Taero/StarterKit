package com.capgemini.pokerHands;

public class Card {
	public static final int TEN = 10;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;
	private char color;
	private int number;
	
	public Card(String symbol) {
		this.color = symbol.charAt(1);
		this.number = getNumberFromSymbol(symbol);
	}
	
	private int getNumberFromSymbol(String symbol) {
		String firstSign = symbol.substring(0, 1);
		int number = 0;
		
		try {  
			number = Integer.parseInt(firstSign) ;
		}  
		catch(NumberFormatException notANumber) {  
			  if (firstSign.equals("T"))
				   number = TEN; 
			  if (firstSign.equals("J"))
				   number = JACK; 
			  if (firstSign.equals("Q"))
				   number = QUEEN; 
			  if (firstSign.equals("K"))
				   number = KING; 
			  if (firstSign.equals("A"))
				   number = ACE;
		}  
		return number;
	}
	
	public char getColor() {
		return this.color;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (number != other.number)
			return false;
		return true;
	}
}