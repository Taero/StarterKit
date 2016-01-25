package com.capgemini.pokerHands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PokerHandsTests {
	private PokerFace game;
	private Hand player1;
	private Hand player2;
	
	@Before
	public void doBefore() {
		this.game = new PokerFace();
	}
	
	@Test
	public void shouldWinHigherNumberCard() {
		player1 = new Hand("8C TS KC 9H 4S");
		player2 = new Hand("7D 2S 5D 3S AC");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer2Wins());
	}
	
	@Test
	public void shouldWinPair() {
		player1 = new Hand("8C TS KC 9H 4S");
		player2 = new Hand("7D 2S 2D 3S AC");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer2Wins());
	}
	
	@Test
	public void shouldWinTwoPairs() {
		player1 = new Hand("TC TS KC KH 4S");
		player2 = new Hand("7D 2S 2D 3S AC");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer1Wins());
	}
	
	@Test
	public void shouldWinThree() {
		player1 = new Hand("8C TS KC 9H 4S");
		player2 = new Hand("7D 2S 3D 3S 3C");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer2Wins());
	}

	@Test
	public void shouldWinThreeWithBiggerNumber() {
		player1 = new Hand("TC TS 2C TH 4S");
		player2 = new Hand("AD KS 9D 9S 9C");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer1Wins());
	}

	@Test
	public void shouldWinStraight() {
		player1 = new Hand("8C 6S 9C 7H TS");
		player2 = new Hand("7D 2S 3D 3S 3C");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer1Wins());
	}

	@Test
	public void shouldWinFlush() {
		player1 = new Hand("TS 2S 4S 6S 8S");
		player2 = new Hand("AD KS 9D AS AC");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer1Wins());
	}

	@Test
	public void shouldWinFlushAgainsStraight() {
		player1 = new Hand("TS 2S 4S 6S 8S");
		player2 = new Hand("8C 6S 9C 7H TS");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer1Wins());
	}

	@Test
	public void shouldWinFullHouse() {
		player1 = new Hand("AC AS TC AH KS");
		player2 = new Hand("2D 2S 9D 9S 9C");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer2Wins());
	}

	@Test
	public void shouldWinFourOfKind() {
		player1 = new Hand("5C 5S TC 5H 5D");
		player2 = new Hand("2D 2S 9D 9S 9C");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer1Wins());
	}

	@Test
	public void shouldWinFourOfKindAgainstStraight() {
		player1 = new Hand("5C 5S TC 5H 5D");
		player2 = new Hand("8C 6S 9C 7H TS");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer1Wins());
	}

	public void shouldWinStraightFlush() {
		player1 = new Hand("8C 6C 9C 7C TC");
		player2 = new Hand("5C 5S TC 5H 5D");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer1Wins());
	}

	public void shouldWinStraightFlushWithHigherNumber() {
		player1 = new Hand("8C 6C 9C 7C TC");
		player2 = new Hand("8S JS 9S QS TS");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer2Wins());
	}

	public void shouldWinRoyalFlush() {
		player1 = new Hand("8C 6C 9C 7C 5C");
		player2 = new Hand("AC QC JC TC KC");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(1, game.getPlayer2Wins());
	}

	public void shouldBeADraw() {
		player1 = new Hand("8C 6C 9C 7C 5C");
		player2 = new Hand("5S 6S 7S 8S 9S");
		System.out.println(game.checkResultOfSingleMatch(player1, player2));	
		assertEquals(0, game.getPlayer1Wins());
		assertEquals(0, game.getPlayer2Wins());
	}

	@Test
	public void shouldReadTheFile() {
		System.out.println(game.checkResultFromFile("src\\main\\java\\com\\capgemini\\pokerHands\\poker.txt"));	
		assertEquals(376, game.getPlayer1Wins());
		assertEquals(624, game.getPlayer2Wins());
	}
	
}
