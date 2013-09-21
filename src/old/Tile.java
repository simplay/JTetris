package old;
import java.util.Random;

public class Tile {
	public int farbe;
	public int x1; public int y1;
	public int x2; public int y2;
	public int x3; public int y3;
	public int x4; public int y4;
	
	public Tile(){
		// erzeuge random eine neue figur, welche am punkt (7,0) spawned.
		int i = 0;
		Random ran = new Random();
		i = ran.nextInt() % 7;
		switch (i) {
		case 0:
			// line piece
			x1 = 7; y1 = 1;
			x2 = 7; y2 = 0;
			x3 = 7; y3 = 2;
			x4 = 7; y4 = 3;
			break;
			
		case 1:
			// t-block
			x1 = 7; y1 = 1;
			x2 = 7; y2 = 0;
			x3 = 6; y3 = 1;
			x4 = 8; y4 = 1;
			break;
			
		case 2:
			// square
			x1 = 7; y1 = 0;
			x2 = 8; y2 = 0;
			x3 = 7; y3 = 1;
			x4 = 8; y4 = 1;
			break;
			
		case 3:
			// squigeling
			x1 = 7; y1 = 0;
			x2 = 8; y2 = 0;
			x3 = 6; y3 = 1;
			x4 = 7; y4 = 1;
			break;
			
		case 4:
			// reverse squigeling 
			// squigeling
			x1 = 7; y1 = 0;
			x2 = 8; y2 = 1;
			x3 = 6; y3 = 0;
			x4 = 7; y4 = 1;
			break;
			
		case 5:
			// L-block
			// squigeling
			x1 = 7; y1 = 1;
			x2 = 7; y2 = 0;
			x3 = 7; y3 = 2;
			x4 = 8; y4 = 2;
			break;
			
		case 6:
			// reverse L-block
			x1 = 7; y1 = 1;
			x2 = 7; y2 = 0;
			x3 = 7; y3 = 2;
			x4 = 6; y4 = 2;
			break;
		}	
		farbe = i+1;
	}
	
	private void getRandomTile(){
		
	}
	
	public void move(int x, int y){
		// x und y sind relative werte, um die verschoben wird
		// translation der koordinaten
		x1 += x; y1 += y;
		x2 += x; y2 += y;
		x3 += x; y3 += y;
		x4 += x; y4 += y;
	}
	
	public Tile(Tile other){
		// create by other figure
		x1 = other.x1; y1 = other.y1;
		x2 = other.x2; y2 = other.y2;
		x3 = other.x3; y3 = other.y3;
		x4 = other.x4; y4 = other.y4;
	}
	
	private int rotateXCoordinate(int y){
		// Drehung um (x1,y1)
		return (y - y1) + x1;
	}
	
	private int rotateYCoordinate(int x){
		// Drehung um (x1,y1)
		return (x1 - x) + y1;
	}
	
	public void drehen(){
		// im Uhrzeigersinn um 90 grad
		// wenn um 0punkt gedreht wird, 
		// dann (x_neu, y_neu) = (y_alt, -x_neu)
		// wenn Drehung um bezugspunkt (x_o, y_o)
		// dann (x_neu, y_neu) = ([y_alt - y_o] + x_o, [x_o - x_neu] + y_o)
		// bezugspunkt wehrt sich gegen verschiebung
		// [y_alt - y_o] von x_neu: y_o nach y_alt
		// [x_o - x_neu] von y_neu: x_alt nach x_o
		// jewels + x_o bzw y_o, damit durch "neuen" nullpunkt (bezugspunkt der drehung).
		
		int x = 0; int y = 0;
		x = rotateXCoordinate(y2); y = rotateYCoordinate(x2);
		x2 = x; y2 = y;
		x = rotateXCoordinate(y3); y = rotateYCoordinate(x3);
		x3 = x; y3 = y;
		x = rotateXCoordinate(y4); y = rotateYCoordinate(x4);
		x4 = x; y4 = y;
	}
}
