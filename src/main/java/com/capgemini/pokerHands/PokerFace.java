package com.capgemini.pokerHands;

public class PokerFace {
	private Hand player1Cards;
	private Hand player2Cards;
	private int player1Wins;
	private int player2Wins;
	
	public int getPlayer1Wins() {
		return player1Wins;
	}

	public int getPlayer2Wins() {
		return player2Wins;
	}

	public String checkResultFromFile(String file) {
		PokerFileReader fileReader = new PokerFileReader(file);
		String line;
		String result;
		
		while (fileReader.hasNextLine()) {
			line = fileReader.getNextLine();
			fillHandsWithCards(line);
			check();
		}
		if (player1Wins > player2Wins)
			result = "First Player won " + Integer.toString(player1Wins) + ":" + Integer.toString(player2Wins);
		else if (player2Wins > player1Wins)
			result = "Second Player won " + Integer.toString(player2Wins) + ":" + Integer.toString(player1Wins);
		else
			result = "The game finished with draw " + Integer.toString(player1Wins) + ":" + Integer.toString(player2Wins);
		return result;
	}
	
	private void fillHandsWithCards(String line) {
		String onePlayersCards = "";
		int index = 0;
		for (int i = 0; i < Hand.NUMBER_CARDS_IN_HAND; i++) {
			index = line.indexOf(" ", index+1);
		}
		onePlayersCards = line.substring(0, index);
		this.player1Cards = new Hand(onePlayersCards);
		onePlayersCards = line.substring(index);
		this.player2Cards = new Hand(onePlayersCards);
	}

	public String checkResultOfSingleMatch(Hand player1Cards, Hand player2Cards) {
		this.player1Cards = player1Cards;
		this.player2Cards = player2Cards;
		return check();
	}
	
	private String check() {
		int player1CombinationStrength;
		int player2CombinationStrength;
		String result = "";
		player1CombinationStrength = player1Cards.getCombinationStrenght();
		player2CombinationStrength = player2Cards.getCombinationStrenght();
		result = compareStrength(player1CombinationStrength, player2CombinationStrength, true);
		return result;
	}
	
	private String compareStrength(int strength1, int strength2, boolean firstComparision) {
		String result = "";
		if (strength1 > strength2) {
			player1Wins++;
			result = "First Player won this match";
		} else if (strength1 < strength2) {
			player2Wins++;
			result = "Second Player won this match";
		} else if (firstComparision) {
			strength1 = player1Cards.getStrengthWhenCombinationIsEqual();
			strength2 = player2Cards.getStrengthWhenCombinationIsEqual();
			result = compareStrength(strength1, strength2, false);
		} else {
			result = "It's draw. Nobody won.";
		}
		return result;
	}

}
