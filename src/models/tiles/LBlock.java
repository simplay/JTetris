package models.tiles;

public class LBlock extends Tile{

	public LBlock() {
		super(6);
	}

	@Override
	protected void initShape() {
		this.shape.setXAt(0, 7);
		this.shape.setXAt(1, 7);
		this.shape.setXAt(2, 7);
		this.shape.setXAt(3, 8);
		
		this.shape.setYAt(0, 1);
		this.shape.setYAt(1, 0);
		this.shape.setYAt(2, 2);
		this.shape.setYAt(3, 2);
	}
}
