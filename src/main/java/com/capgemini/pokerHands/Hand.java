package com.capgemini.pokerHands;

import java.util.ArrayList;

public class Hand {
	private PokerCardCombinationsStrenght pokerCombinations;
	public static int NUMBER_CARDS_IN_HAND = 5;
	private ArrayList<Card> cards;
	private boolean isColor;
	private boolean sorted;

	public Hand(String cardSet) {
		String[] symbols = cardSet.trim().split(" ");
		char color;
		this.cards = new ArrayList<Card>();
		isColor = true;
		this.cards.add(new Card(symbols[0]));
		color = cards.get(0).getColor();
		for (int i = 1; i < NUMBER_CARDS_IN_HAND; i++) {
			this.cards.add(new Card(symbols[i]));
			if (color != cards.get(i).getColor()) {
				this.isColor = false;
			}
		}
		this.sorted = false;
		pokerCombinations = new PokerCardCombinationsStrenght();
	}

	public boolean isStraight() {
		if (!sorted)
			this.sortAndMoveCards();
		for (int i = 1; i < NUMBER_CARDS_IN_HAND; i++) {
			if (cards.get(i - 1).getNumber() != cards.get(i).getNumber() + 1)
				return false;
		}
		return true;
	}

	public boolean isTheSameColor() {
		if (!sorted)
			this.sortAndMoveCards();
		return this.isColor;
	}

	public void sortAndMoveCards() {
		this.sort();
		this.moveCombinationsToFront();
	}

	private void sort() {
		int max;
		int place;
		Card card;
		for (int i = 0; i < NUMBER_CARDS_IN_HAND-1; i++) {
			max = cards.get(i).getNumber();
			place = i;
			for (int j = i+1; j < NUMBER_CARDS_IN_HAND; j++) {
				if (max < cards.get(j).getNumber()) {
					max = cards.get(j).getNumber();
					place = j;
				}
			}
			if (place != i) {
				card = cards.get(i);
				cards.set(i, cards.get(place));
				cards.set(place, card);
			}
		}
		sorted = true;
	}

	private void moveCombinationsToFront() {
		int max;
		int place = 0;
		Card card;
		int iterator = 0;
		while (iterator < NUMBER_CARDS_IN_HAND){
			max = this.countCardsOfThisKind(cards.get(iterator), iterator);
			if (max > 1) {
				if (iterator > 0)
				{
					for (int i=0; i<max;i++){
						card = cards.get(iterator + i);
						cards.remove(iterator + i);
						cards.add(place, card);
					}
				}
				iterator += max;
				place +=max;
			} else
				iterator++;
		}
	}

	private int countCardsOfThisKind(Card card, int begin) {
		int sum = 0;
		for (int i = begin; i < NUMBER_CARDS_IN_HAND; i++) {
			if (this.cards.get(i).equals(card)) 
				sum++;
		}
		return sum;
	}

	public int getCombinationStrenght() {
		if (!sorted)
			this.sortAndMoveCards();

		return pokerCombinations.getCombinationStrength(getCombination());
	}
	
	private POKER_CARDS_COMBINATIONS getCombination() {
		int count;

		if (isStraight())
			return findCombinationOfSTRAIGHT();
		count = countCardsOfThisKind(cards.get(0), 0);
		if (count > 2 || isColor) 
			return findCombinationFromThreeToFullHouse(count);		
		if (count == 2) 
			return findCombinationOfPairs(count);
		return POKER_CARDS_COMBINATIONS.NOTHING;
	}

	private POKER_CARDS_COMBINATIONS findCombinationOfSTRAIGHT() {
		POKER_CARDS_COMBINATIONS result;
		if (isColor) {
			if (cards.get(0).getNumber() == Card.ACE)
				result = POKER_CARDS_COMBINATIONS.ROYAL_FLUSH;
			else
				result = POKER_CARDS_COMBINATIONS.STRAIGHT_FLUSH;
		} else
			result = POKER_CARDS_COMBINATIONS.STRAIGHT;
		return result;
	}


	private POKER_CARDS_COMBINATIONS findCombinationFromThreeToFullHouse(int count) {
		POKER_CARDS_COMBINATIONS result;
		if (count == 4) 
			result = POKER_CARDS_COMBINATIONS.FOUR_OF_KIND;
		else if (countCardsOfThisKind(cards.get(3), 3) == 2)
			result = POKER_CARDS_COMBINATIONS.FULL_HOUSE;
		else if (!isColor)
			result = POKER_CARDS_COMBINATIONS.THREE_OF_KIND;
		else
			result = POKER_CARDS_COMBINATIONS.FLUSH;

		return result;
	}
	
	private POKER_CARDS_COMBINATIONS findCombinationOfPairs(int count) {
		POKER_CARDS_COMBINATIONS result;
		if (countCardsOfThisKind(cards.get(2), 2) == 2)
			result = POKER_CARDS_COMBINATIONS.TWO_PAIRS;
		else
			result = POKER_CARDS_COMBINATIONS.ONE_PAIR;
		return result;
	}
	
	
	public int getStrengthWhenCombinationIsEqual() {
		if (!sorted)
			this.sortAndMoveCards();

		int multipler = 1;
		int strength = 0;		
		for (int i=NUMBER_CARDS_IN_HAND-1; i>=0; i--) {
			strength += multipler*cards.get(i).getNumber();
			multipler *= 8;	// 8, ponieważ to najniższa wartość, która przy mnożeniu przez 2 (najniższa karta to "Dwójka") daje wynik większy od wartości przypisanej Asowi (14)
		}
		return strength;
	}
}
