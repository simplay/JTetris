package tiles;

public class LinePiece extends Tile{
	public LinePiece() {
		super(1);
	}
	
	@Override
	public void initShape(){
		this.shape.setXAt(0, 7);
		this.shape.setXAt(1, 7);
		this.shape.setXAt(2, 7);
		this.shape.setXAt(3, 7);
		
		this.shape.setYAt(0, 1);
		this.shape.setYAt(1, 0);
		this.shape.setYAt(2, 2);
		this.shape.setYAt(3, 3);
	}
}
