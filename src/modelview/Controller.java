package modelview;

import interfaces.Listener;
import interfaces.MoreLines;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import models.BoardObserver;
import models.*;

public class Controller implements Listener, MoreLines{
	private BoardObserver model;
	private View view;
	private int killed_lines;
	private int previous_killed_lines;
	private int count = 5;
	private volatile int speed;
	
	public Controller(BoardObserver model, View view){
		this.model = model;
		this.view = view;

		model.subscribeListener(this);
		model.board.subscribe_line_Listener(this);
		define_EventHandling();
	}
	
	private void define_EventHandling(){
		view.field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event){
				int key = event.getKeyCode();
				if(key == KeyEvent.VK_S) model.board.new_figure();
				if(key == KeyEvent.VK_LEFT) model.board.nach_links();
				if(key == KeyEvent.VK_RIGHT) model.board.nach_rechts();
				if(key == KeyEvent.VK_DOWN) model.board.nach_unten();
				if(key == KeyEvent.VK_UP) model.board.rotate();
				if(key == KeyEvent.VK_SPACE) model.board.fall();
			}
		});
		
		
		
		view.start.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						view.start.setEnabled(false);
						view.stop.setEnabled(true);
						view.pause.setEnabled(true);
						view.field.requestFocusInWindow();
						model.build_timer();
						model.board.new_figure();
						model.board.notify_observers();
						count = 5;
						speed = 200;
						model.timer.schedule(new Task(model.board), 50, speed);
						model.timer_is_running = true;
					}
				}
		);
		
		
		view.stop.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						view.start.setEnabled(true);
						view.stop.setEnabled(false);
						view.pause.setEnabled(false);
						model.timer.cancel();
						model.board.clear_all();
						model.timer_is_running = false;
					}
				}
		);
		
		view.pause.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {		
						if(model.timer_is_running){
							model.timer_is_running = false;
							model.timer.cancel();
							model.timer.purge();
						}else{
							model.timer_is_running = true;
							model.build_timer();
							model.timer.schedule(new Task(model.board), 50, speed);
						}
						view.field.requestFocusInWindow();
					}
				}
		);
	}
	
	@Override
	public void handleEvent() {
		// wie reagieren, wenn uns das model miteilt,
		// dass es neue daten hat?
		
		
		view.field.repaint();
		if(model.board.zero_line_fixed()){
			System.out.println("brich ab...");
			model.timer.cancel();
			model.timer.purge();
			view.pause.setEnabled(false);
			model.board.clear_all();
			view.paint_lost();
		}
		
		view.time.setText("Points: " + model.board.points + " " + "Lines: " + model.board.line_count+ " speed: " + speed);
	}

	@Override
	public void handle_line_Event() {
		// TODO Auto-generated method stub
	//	System.out.println("new lines");
		previous_killed_lines = killed_lines;
		killed_lines = model.board.line_count;
		count -= (killed_lines - previous_killed_lines);
	//	System.out.println("count" + count);
		if(count == 0){
			count = 5;
			System.out.println("be faster...");
			if(speed > 100) speed -= 50;
			else 
				if(speed > 25) speed -= 10;
			
			model.timer.cancel();
			model.timer.purge();
			model.build_timer();
			model.timer.schedule(new Task(model.board), 50, speed);
		}
		view.time.setText("Points: " + model.board.points + " " + "Lines: " + model.board.line_count  + " speed: " + speed);
	}
}
