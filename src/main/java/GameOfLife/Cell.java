package GameOfLife;

import java.util.ArrayList;
import java.util.Map;

public class Cell {
	private Point position;
	private boolean imAlive;
	private int neighborsNumber;

	public Cell(int x, int y, boolean amIAlive) {
		this.imAlive = amIAlive;
		this.position = new Point(x, y);
		this.neighborsNumber = 0;
	}

	public void addNeighbor(int number) {
		if (number > 0 && number < 9)
			this.neighborsNumber += number;
	}
	
	public ArrayList<Point> getNeightborsPositions() {
		return this.position.getNeightborsPositions();
	}

	public void goToNextGeneration() {
		this.imAlive = (this.neighborsNumber == 3 || (this.neighborsNumber == 2 && this.imAlive));
		this.neighborsNumber = 0;
	}

	public boolean amIAlive() {
		return this.imAlive;
	}

	public String getPositionAsString() {
		return this.position.getPositionAsString();
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof String){
			String toCompare = (String) o;
			return toCompare.equals(this.getPositionAsString());
		}
		if(o instanceof Cell){
			Cell toCompare = (Cell) o;
			return this.getPositionAsString().equals(toCompare.getPositionAsString());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 1001*this.position.getX() + this.position.getY(); 
	}
}
