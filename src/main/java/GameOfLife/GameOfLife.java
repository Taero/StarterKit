package GameOfLife;

import java.util.Set;

public class GameOfLife {
	private int generation;
	private GameBoard board;
	
	public GameOfLife(int x, int y) {
		this.board = new GameBoard(x, y);
		this.generation = 1;
	}
		
	public int getGeneration() {
		return this.generation;
	}
	
	public void addLivingCell(int x, int y) {
		board.addLivingCell(x, y);
	}
	
	public Set<String> getLivingCellPositions() {
		return this.board.getLivingPositions();
	}
	
	public void goToNextGeneration() {
		this.board.goToNextGeneration();			
		this.generation++;
	}
}