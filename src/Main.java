import models.BoardObserver;
import modelview.Controller;
import modelview.View;


public class Main {
	public static void main(String[] args) {
		BoardObserver model = new BoardObserver();
		View view = new View(model);
		new Controller(model, view);
	}
}
