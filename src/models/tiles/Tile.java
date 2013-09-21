package models.tiles;

import util.datastructures.Shape;
import util.datastructures.TilePoint;

public abstract class Tile {
	protected int color;
	protected Shape shape;
	protected Shape clonedShape;
	
	public Tile(int color){
		this.shape = new Shape(); 
		this.color = color;
		initShape();
	}
	
	public int getColor(){
		return this.color;
	}
	
	public void addColor(int value){
		this.color += value;
	}
	
	public int getAt(int u, int v){
		return shape.getAt(u, v);
	}
	
	public void setAt(int u, int v, int value){
		this.shape.setAt(u, v, value);
	}
	
	protected abstract void initShape();
	
	/**
	 * Translate this tile towards p.
	 * @param p translate value.
	 */
	public void move(TilePoint p){
		for(int k = 0; k < 4; k++){
			for(int t = 0; t < 2; t++){
				int value = (t == 0) ? p.x : p.y;
				this.shape.addAt(k, t, value);
			}
		}
	}
	
	/**
	 * Rotate this tile 90 degree clock-wise.
	 */
	public void rotate(){
		for(int t = 1; t < 4; t++) updatePointAt(t);
	}
	
	public void invRotate(){
		for(int t = 0; t < 3; t++) rotate();
	}
	
	private void updatePointAt(int k){
		TilePoint tmp = shape.getPointAt(k);
		tmp = rotatePosition90(new TilePoint(tmp.y, tmp.x));
		shape.setPointAt(tmp, k);
	}
	
	/**
	 * Rotate given point p 90 degrees clock-wise around (x1, y1).
	 * @param p target point to rotate.
	 * @return returns 90deg cw rotated point p.
	 */
	protected TilePoint rotatePosition90(TilePoint p){
		int x0 = shape.getXAt(0);
		int y0 = shape.getYAt(0);
		int y = (x0 - p.y) + y0;
		int x = (p.x - y0) + x0;
		return new TilePoint(x, y);
	}
	
}
