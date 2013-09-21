package models;


import java.util.TimerTask;

public class Task extends TimerTask{
	private int counter = 0;
	private Board board;
	public Task(Board b){
		board = b;
	}
	
	@Override
	public void run() {
		board.nach_unten();	
		counter++;
		if(counter % 16 == 0)board.points++;
	}
}