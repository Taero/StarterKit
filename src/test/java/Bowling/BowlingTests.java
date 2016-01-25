package Bowling;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BowlingTests {
	private BowlingResultCalculator game;
	private int result;
	
	@Before
	public void doBefore() {
		this.game = new BowlingResultCalculator();
	}

	@After
	public void doCleanupAfterTest() {
	}
	
	@Test
	public void shouldNotBeFinishAtStart() {
		assertFalse(this.game.isFinished());
	}

	@Test
	public void shouldReturnScoreAfterOneRoll() {
		game.roll(4);
		result = game.score();
		assertEquals(4, result);
		assertFalse(this.game.isFinished());
	}

	@Test
	public void shouldReturnScoreAfterTwoRolls() {
		game.roll(4);
		game.roll(5);
		result = game.score();
		assertEquals(9, result);
		assertFalse(this.game.isFinished());
	}
	
	@Test
	public void shouldReturnScoreAfterFiveRolls() {
		game.roll(4);
		game.roll(5);
		game.roll(6);
		game.roll(4);
		game.roll(9);
		result = game.score();
		assertEquals(37, result);
		assertFalse(this.game.isFinished());
	}

	@Test
	public void shouldReturnScoreAfterSixRollsWithStrikes() {
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(4);
		game.roll(4);
		game.roll(4);
		result = game.score();
		assertEquals(84, result);
		assertFalse(this.game.isFinished());
	}
	
	@Test
	public void shouldReturnScoreAfterSevenRollsWithStrikes() {
		game.roll(5);
		game.roll(0);
		game.roll(10);
		game.roll(8);
		game.roll(2);
		game.roll(9);
		game.roll(1);
		result = game.score();
		assertEquals(54, result);
		assertFalse(this.game.isFinished());
	}
	
	@Test
	public void shouldReturnScoreAfterFullGame() {
		game.roll(5);
		game.roll(0);		//1
		game.roll(10);		//2
		game.roll(8);
		game.roll(2);		//3
		//44
		game.roll(9);	
		game.roll(1);		//4	
		game.roll(8);
		game.roll(2);		//5
		//+35 = 79
		game.roll(7);
		game.roll(2);		//6
		game.roll(9);
		game.roll(0);		//7
		//+18 = 97
		game.roll(10);		//8
		game.roll(3);
		game.roll(7);		//9
		//+33 = 130
		game.roll(3);
		game.roll(3);		//10
		result = game.score();
		assertEquals(136, result);
		assertTrue(this.game.isFinished());
	}
	
	@Test
	public void shouldAddMultiplierAfterFullGame() {
		game.roll(5);
		game.roll(0);		//1
		game.roll(10);		//2
		game.roll(8);
		game.roll(2);		//3
		//44
		game.roll(9);	
		game.roll(1);		//4	
		game.roll(8);
		game.roll(2);		//5
		//+35 = 79
		game.roll(7);
		game.roll(2);		//6
		game.roll(9);
		game.roll(0);		//7
		//+18 = 97
		game.roll(10);		//8
		game.roll(3);
		game.roll(3);		//9
		//+22 = 119
		game.roll(9);
		game.roll(1);		//10
		result = game.score();
		assertEquals(139, result);		//129+10 punktów za Strike w ostatniej rundzie
		assertTrue(this.game.isFinished());
	}

	@Test
	public void shouldAddMultipliersAfterFullGame() {
		game.roll(5);
		game.roll(0);		//1
		game.roll(10);		//2
		game.roll(8);
		game.roll(2);		//3
		//44
		game.roll(9);	
		game.roll(1);		//4	
		game.roll(8);
		game.roll(2);		//5
		//+35 = 79
		game.roll(7);
		game.roll(2);		//6
		game.roll(9);
		game.roll(0);		//7
		//+18 = 97
		game.roll(10);		//8
		game.roll(3);
		game.roll(3);		//9
		//+22 = 119
		game.roll(10);		//10
		result = game.score();
		assertEquals(149, result);		//129+20 punktów za Strike w ostatniej rundzie
		assertTrue(this.game.isFinished());
	}
	
	@Test
	public void shouldNotAddMoreRolls() {
		for (int i = 0; i < 22; i++)
		{
			game.roll(4);
		}
		result = game.score();
		assertEquals(80, result);	
		assertTrue(this.game.isFinished());
	}
	
	@Test
	public void shouldNotAcceptOutOfRangePoints() {
		game.roll(-4);
		game.roll(-4);
		game.roll(14);
		game.roll(14);
		game.roll(0);
		game.roll(0);
		result = game.score();
		assertEquals(30, result);		
	}
}
