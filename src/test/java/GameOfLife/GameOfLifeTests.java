package GameOfLife;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GameOfLifeTests {
	private Set<String> positions;
	private GameOfLife game;
	
	@Before
	public void doBefore() {
		this.game = new GameOfLife(10, 10);
	}
	
	@Test
	public void shouldNotBeAnyCellAlive() {
		positions = game.getLivingCellPositions();
		assertEquals(0, positions.size());
	}

	@Test
	public void shouldAddLivingCell() {
		game.addLivingCell(4, 5);
		positions = game.getLivingCellPositions();
		assertEquals(1, positions.size());
		assertTrue(positions.contains(Point.convertPositionToString(4, 5)));
	}
	
	@Test
	public void shouldAddManyLivingCell() {
		for(int i = 1; i < 5; i++) {
			game.addLivingCell(i, 5);
		}
		positions = game.getLivingCellPositions();
		assertEquals(4, positions.size());
		assertTrue(positions.contains(Point.convertPositionToString(1, 5)));
		assertTrue(positions.contains(Point.convertPositionToString(2, 5)));
		assertTrue(positions.contains(Point.convertPositionToString(3, 5)));
		assertTrue(positions.contains(Point.convertPositionToString(4, 5)));
	}
	
	@Test
	public void shouldChangeGeneration() {
		for(int i = 1; i < 4; i++) {
			game.addLivingCell(i, 5);
		}
		game.addLivingCell(2, 4);
		positions = game.getLivingCellPositions();
		assertEquals(4, positions.size());
		game.goToNextGeneration();
		positions = game.getLivingCellPositions();
		assertEquals(7, positions.size());
		assertTrue(positions.contains(Point.convertPositionToString(1, 5)));
		assertTrue(positions.contains(Point.convertPositionToString(2, 5)));
		assertTrue(positions.contains(Point.convertPositionToString(3, 5)));
		assertTrue(positions.contains(Point.convertPositionToString(1, 4)));
		assertTrue(positions.contains(Point.convertPositionToString(2, 4)));
		assertTrue(positions.contains(Point.convertPositionToString(3, 4)));
		assertTrue(positions.contains(Point.convertPositionToString(2, 6)));
	}
	
	@Test
	public void shouldMakeCellsDead() {
		for(int i = 1; i <= 4; i++) {
			game.addLivingCell(i, i);
		}
		game.goToNextGeneration();
		positions = game.getLivingCellPositions();
		assertEquals(2, positions.size());
		assertTrue(positions.contains(Point.convertPositionToString(2, 2)));
		assertTrue(positions.contains(Point.convertPositionToString(3, 3)));
		game.goToNextGeneration();
		positions = game.getLivingCellPositions();
		assertTrue(positions.size() == 0);	
	}
	
	@Test
	public void shouldMakeCellsDeadFromCrowd() {
		for(int i = 1; i <= 4; i++) {
			game.addLivingCell(i, i);
			game.addLivingCell(i, i+1);
		}
		game.goToNextGeneration();
		positions = game.getLivingCellPositions();
		assertEquals(10, positions.size());
		assertTrue(positions.contains(Point.convertPositionToString(1, 1)));
		assertTrue(positions.contains(Point.convertPositionToString(1, 2)));
		assertTrue(positions.contains(Point.convertPositionToString(1, 3)));
		assertTrue(positions.contains(Point.convertPositionToString(2, 1)));
		assertTrue(positions.contains(Point.convertPositionToString(2, 4)));
		assertTrue(positions.contains(Point.convertPositionToString(3, 2)));
		assertTrue(positions.contains(Point.convertPositionToString(3, 5)));
		assertTrue(positions.contains(Point.convertPositionToString(4, 3)));
		assertTrue(positions.contains(Point.convertPositionToString(4, 4)));
		assertTrue(positions.contains(Point.convertPositionToString(4, 5)));
	}
	
	@Test
	public void shouldCreateNewCells() {
		for(int i = 1; i <= 3; i++) {
			game.addLivingCell(i, 3);
		}
		game.goToNextGeneration();
		positions = game.getLivingCellPositions();
		assertEquals(3, positions.size());
		assertTrue(positions.contains(Point.convertPositionToString(2, 3)));
		assertTrue(positions.contains(Point.convertPositionToString(2, 2)));
		assertTrue(positions.contains(Point.convertPositionToString(2, 4)));		
	}
}