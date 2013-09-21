package Tiles;

public class Squigeling extends Tile{

	public Squigeling() {
		super(4);
	}

	@Override
	protected void initShape() {
		this.shape.setXAt(0, 7);
		this.shape.setXAt(1, 8);
		this.shape.setXAt(2, 6);
		this.shape.setXAt(3, 7);
		
		this.shape.setYAt(0, 0);
		this.shape.setYAt(1, 0);
		this.shape.setYAt(2, 1);
		this.shape.setYAt(3, 1);
	}
}
