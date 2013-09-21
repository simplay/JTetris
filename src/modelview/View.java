package modelview;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.BoardObserver;


class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private BoardObserver model; 
	private int kbreite = 15;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red.darker().darker());
		for (int i = 0; i <= model.board.length; i++) {
			g.drawLine(i * kbreite, 0, i * kbreite, model.board.height * kbreite);
		}
		for (int j = 0; j <= model.board.height; j++) {
			g.drawLine(0, j * kbreite, model.board.length * kbreite, j * kbreite);
		}
		for (int i = 0; i < model.board.length; i++ ) {
			for (int j = 0; j < model.board.height; j++ ) {
				Color Farbe;
				int decider = model.board.field[i + 1][j + 1];
				if(decider > 8) decider -= 8;
				switch (decider) {
				case 0:
					Farbe = Color.black;
					break;
				case 1:
					Farbe = Color.cyan;
					break;
				case 2:
					Farbe = Color.magenta.darker();
					break;
				case 3:
					Farbe = Color.yellow;
					break;
				case 4:
					Farbe = Color.green;
					break;
				case 5:
					Farbe = Color.red;
					break;
				case 6:
					Farbe = Color.ORANGE.darker();
					break;
				default:
					Farbe = Color.blue;
					break;
				}
					
				g.setColor(Farbe);
				zeichneKaestchen(g, i, j);
			}
		}
	}
	
	private void zeichneKaestchen(Graphics g, int x, int y) {
		g.fillRect(x * kbreite + 1, y * kbreite + 1, kbreite - 1, kbreite - 1);
	}
	
	public void set_model(BoardObserver model){
		this.model = model;	
	}
	
	public void paint_lost(){
		// L
		model.board.field[3][5] = 1;
		model.board.field[3][6] = 1;
		model.board.field[3][7] = 1;
		model.board.field[4][7] = 1;
		
		// O
		model.board.field[5][5] = 2;
		model.board.field[5][6] = 2;
		model.board.field[5][7] = 2;
		model.board.field[6][7] = 2;
		model.board.field[7][7] = 2;
		model.board.field[7][6] = 2;
		model.board.field[7][5] = 2;
		model.board.field[6][5] = 2;
		
		// S
		model.board.field[8][7] = 3;
		model.board.field[9][7] = 3;
		model.board.field[9][6] = 3;
		model.board.field[9][5] = 3;
		model.board.field[10][5] = 3;
		
		// T
		model.board.field[11][5] = 4;
		model.board.field[12][5] = 4;
		model.board.field[13][5] = 4;
		model.board.field[12][6] = 4;
		model.board.field[12][6] = 4;
		model.board.field[12][7] = 4;

		repaint();
	}
}


public class View {
	@SuppressWarnings("unused")
	private BoardObserver model;
	public final int MAXX ;
	public final int MAXY ;
	public JPanel controls;
	public JButton start;
	public JButton stop;
	public JButton pause;
	public MyPanel field;
	public JLabel time;

	public View(BoardObserver model){
		this.model = model;
		
		
		MAXX = model.board.length;
		MAXY = model.board.height;
		
		JFrame frame = new JFrame("TETRIS");
		Container cont = frame.getContentPane();
		controls = new JPanel(); // contains all the buttons	
		field = new MyPanel(); // contains the canvas
		
		start = new JButton("start");
		stop = new JButton("stop");
		pause = new JButton("pause");
		time = new JLabel();
		//time.setText("Points: " + model.board.points + " " + "Lines: " + model.board.line_count);
		
		// Add buttons to their panel
		controls.setLayout(new FlowLayout(FlowLayout.LEFT));
		controls.add(start);
		controls.add(stop);
		controls.add(pause);
		controls.add(Box.createRigidArea(new Dimension(25, 0)));
		controls.add(time);
		
		stop.setEnabled(false);
		field.set_model(model);
		
		cont.setLayout(new BorderLayout());
		cont.add(controls, BorderLayout.SOUTH);
		//cont.add(my_stats, BorderLayout.EAST);
		cont.add(field, BorderLayout.CENTER);
		
		
		
		frame.setContentPane(cont);
		frame.setMinimumSize(new Dimension(460, 550));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		field.requestFocusInWindow(); // focus auf canvas
	}
	
	public void paint_lost(){
		field.paint_lost();
	}	
}
