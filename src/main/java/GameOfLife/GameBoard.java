package GameOfLife;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameBoard {
	private Map<String, Cell> cells;
	private int boardMaxX;
	private int boardMaxY;
	
	public GameBoard(int x, int y) {
		if (x < 3  || y < 3)
			throw new IllegalArgumentException("Rozmiar boku planszy nie może być mniejszy niż 3");
		if (x > 1000  || y > 1000)
			throw new IllegalArgumentException("Rozmiar boku planszy nie może być większy niż 1000");
		this.boardMaxX = x;
		this.boardMaxY = y;
		this.cells = new HashMap<String, Cell>();
	}
	
	public void addLivingCell(int x, int y) {
		if (!this.isOnTable(x, y))
			throw new IllegalArgumentException("Dodawana komórka jest poza wyznaczoną planszą (pozycja komórki: " +  
					x + ", " + y + ". Rozmiar planszy: " + this.boardMaxX + "x" + this.boardMaxY + ")");
		Cell cell = new Cell(x, y, true);
		this.cells.put(cell.getPositionAsString(), cell);			
}

	public boolean isOnTable(Point point) {
		return this.isOnTable(point.getX(), point.getY());
	}

	public boolean isOnTable(int x, int y) {
		return (x > 0 && y > 0 && x <= this.boardMaxX && y <= this.boardMaxY);
	}
	
	
	public void goToNextGeneration() {
		Map<String, Cell> currentGeneration = new HashMap<String, Cell>();
		
		for(Map.Entry<String, Cell> boardElement : cells.entrySet()) {
			currentGeneration.put(boardElement.getKey(), boardElement.getValue());
		}

		for(Map.Entry<String, Cell> boardElement : currentGeneration.entrySet()) {
			if (boardElement.getValue().amIAlive()) 
				this.markYourLivingInfluence(boardElement.getValue());			
		}

		for(Map.Entry<String, Cell> boardElement : this.cells.entrySet()) {
			boardElement.getValue().goToNextGeneration();
		}				
	}
	
	public void markYourLivingInfluence(Cell cell) {
		for (Point point: cell.getNeightborsPositions()) {
			if (this.isOnTable(point)) {
				if (!this.cells.containsKey(point.getPositionAsString())) 
					this.cells.put(point.getPositionAsString(), new Cell(point.getX(), point.getY(), false));		
				
				this.cells.get(point.getPositionAsString()).addNeighbor(1);
			}
		}
	}
		
	public Set<String> getLivingPositions() {
		Set<String> positions = new HashSet<String>();
		if (this.cells != null) {
			for(Map.Entry<String, Cell> boardElement : this.cells.entrySet()) {
				if (boardElement.getValue().amIAlive())
					positions.add(boardElement.getKey());
			}			
		}	
		return positions;
	}	
}