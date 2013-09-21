package util.datastructures;

public class Shape implements Cloneable{
	protected int[][] shape;

	public Shape(){
		this.shape = new int[4][2]; 
	}
	
	public TilePoint getPointAt(int row){
		return new TilePoint(this.getXAt(row), this.getYAt(row));
	}
	
	public void setPointAt(TilePoint p, int row){
		this.setXAt(row, p.x);
		this.setYAt(row, p.y);
	}
	
	public int getAt(int u, int v){
		return this.shape[u][v];
	}
	
	public int getXAt(int k){
		return this.shape[k][0];
	}
	
	public int getYAt(int k){
		return this.shape[k][1];
	}
	
	public void setAt(int u, int v, int value){
		this.shape[u][v] = value;
	}
	
	public void setXAt(int k, int value){
		this.shape[k][0] = value;
	}
	
	public void setYAt(int k, int value){
		this.shape[k][1] = value;
	}
	
	public void addAt(int u, int v, int value){
		this.shape[u][v] += value;
	}
}
