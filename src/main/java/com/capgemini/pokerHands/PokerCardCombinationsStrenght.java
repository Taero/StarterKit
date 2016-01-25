package com.capgemini.pokerHands;

import java.util.EnumMap;

public class PokerCardCombinationsStrenght {
	private EnumMap<POKER_CARDS_COMBINATIONS, Integer> cardCombinationStrength;

	public PokerCardCombinationsStrenght() {
		cardCombinationStrength = new EnumMap<POKER_CARDS_COMBINATIONS, Integer>(POKER_CARDS_COMBINATIONS.class);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.NOTHING, 0);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.ONE_PAIR, 1);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.TWO_PAIRS, 2);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.THREE_OF_KIND, 3);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.STRAIGHT, 4);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.FLUSH, 5);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.FULL_HOUSE, 6);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.FOUR_OF_KIND, 7);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.STRAIGHT_FLUSH, 8);
		cardCombinationStrength.put(POKER_CARDS_COMBINATIONS.ROYAL_FLUSH, 9);
	}
	
	public int getCombinationStrength(POKER_CARDS_COMBINATIONS combination) {
		return this.cardCombinationStrength.get(combination);
	}
}
