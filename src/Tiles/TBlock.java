package Tiles;

public class TBlock extends Tile{

	public TBlock() {
		super(2);
	}
	
	@Override
	public void initShape(){
		this.shape.setXAt(0, 7);
		this.shape.setXAt(1, 7);
		this.shape.setXAt(2, 6);
		this.shape.setXAt(3, 8);
		
		this.shape.setYAt(0, 1);
		this.shape.setYAt(1, 0);
		this.shape.setYAt(2, 1);
		this.shape.setYAt(3, 1);
	}

}
