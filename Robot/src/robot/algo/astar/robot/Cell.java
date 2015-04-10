package robot.algo.astar.robot;

import java.awt.Color;

public class Cell {
	
	private int x;
	private int y;
	private Color color;

	
	public Cell() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.color = new Color(255, 0, 0);
	}
	public Cell(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public int getColor() {
		return color.getRGB();
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", color=" + color + "]";
	}
	
}
