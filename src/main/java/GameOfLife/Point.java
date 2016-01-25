package GameOfLife;

import java.util.ArrayList;

public class Point {
	private final static String separator = "x";
	private int x;
	private int y;
	
	public static String convertPositionToString(int x, int y) {
		return (Integer.toString(x) + Point.separator + Integer.toString(y));
	}

	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public String getPositionAsString() {
		return Point.convertPositionToString(this.x, this.y);
	}
		
	public ArrayList<Point> getNeightborsPositions() {
		ArrayList<Point> neightbors = new ArrayList<Point>();

		for(int i = this.x - 1; i <= this.x+1; i++) {
			for(int j = this.y - 1; j <= this.y+1; j++) {
				if (!( x==i && y==j)) {
					neightbors.add(new Point(i, j));
				}
			}
		}
		return neightbors;
	}
}
