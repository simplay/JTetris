package Tiles;

public class RevSquigeling extends Tile{

	public RevSquigeling() {
		super(5);
	}
	
	@Override
	public void initShape(){
		this.shape.setXAt(0, 7);
		this.shape.setXAt(1, 8);
		this.shape.setXAt(2, 6);
		this.shape.setXAt(3, 7);
		
		this.shape.setYAt(0, 0);
		this.shape.setYAt(1, 1);
		this.shape.setYAt(2, 0);
		this.shape.setYAt(3, 1);
	}
}
