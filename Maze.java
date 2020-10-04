import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Maze extends JPanel implements KeyListener {
	//Images
	ImageIcon grass = new ImageIcon(getClass().getResource("Images/grass1.jpg"));
	ImageIcon stoneblock = new ImageIcon(getClass().getResource("Images/Stoneblock.jpg"));
	ImageIcon sans = new ImageIcon(getClass().getResource("Images/sans.jpg"));
	ImageIcon winning = new ImageIcon(getClass().getResource("Images/win.jpg"));

	//Declarations
	String words = "";
	int x,y = 0;
	boolean win = false;
	
	//Array for the Maze
	int[][] m =  {{1,1,1,1,1,1,1,1,1,1},
			{2,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,1,0,0,1},
			{1,0,1,1,0,0,0,0,0,1},
			{1,0,1,1,0,1,1,1,1,1},
			{1,0,0,0,0,1,0,0,0,1},
			{1,1,1,1,0,1,0,0,0,1},
			{1,0,0,1,0,1,1,1,1,1},
			{1,0,0,1,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,3,1}};

	public static void main(String[]args ){
		Maze d = new Maze();
		JFrame window = new JFrame();
		window.setSize(640,670);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBackground(Color.BLUE);
		window.add(d);
		window.setVisible(true);
		d.requestFocus();
		
	}

	public Maze(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//Paints  Maze and Character
		for (int x = 0; x < 10; x++){
			for (int y = 0; y < 10; y++){
				if (m[y][x] == 1){
					stoneblock.paintIcon(this,g,x*64,y*64);
				}else if(m[y][x] == 0 || m[y][x] == 3){
					grass.paintIcon(this,g,x*64,y*64);
				}else if(m[y][x] == 2){
					sans.paintIcon(this,g,x*64,y*64);
				}

			}
			repaint();
		}

		//Warning
		g.setFont(new Font("Times New Roman",Font.PLAIN,40));
		g.drawString(words, 50, 300);
		repaint();

		//Win Screen
		if(win == true){
			winning.paintIcon(this,g,x*10,y*10);
		}
	}

	public void keyPressed(KeyEvent e){
		int xcor = 0;
		int ycor = 0;
		int c = e.getKeyCode();

		for (int x = 0; x < 10; x++){
			for (int y = 0; y < 10; y++){	
				if (m[x][y] == 2){
					xcor = x;
					ycor = y;

				}
			}
		}
		
		//Right Movement
		if (c == KeyEvent.VK_D ){
			if (ycor+1 < 10){
				if(m[xcor][ycor+1] == 0){

					m[xcor][ycor] = 0;
					m[xcor][ycor+1] = 2;
					words = ("");
				}else{
					words = ("You can't do that");
				} 
			}else{
				words = ("You can't do that");
			}

			//Left Movement
		}else if( c == KeyEvent.VK_A ){
			if(ycor-1 > 0){
				if(m[xcor][ycor-1] == 0){
					m[xcor][ycor] = 0;
					m[xcor][ycor-1] = 2;
					words = ("");
				}else{
					words = ("You can't do that");
				}
			}else{
				words = ("You can't do that");
			}
			
			//Up Movement
		}else if(c == KeyEvent.VK_W ){
			if (xcor-1>0){
				if(m[xcor-1][ycor] == 0){
					m[xcor][ycor] = 0;
					m[xcor-1][ycor] = 2;
					words = ("");
				}else{
					words = ("You can't do that");
				}
			}else{
				words = ("You can't do that");
			}

			//Down Movement
		}else if (c == KeyEvent.VK_S ){
			if (xcor+1<10 ){
				if(m[xcor + 1][ycor] == 0){
					m[xcor][ycor] = 0;
					m[xcor+1][ycor] = 2;
					words = ("");

				}else if (m[xcor + 1][ycor] == 3){
					win = true;
				}else{

					words = ("You can't do that");
				}
			}else{
				words = ("You can't do that");
			}
			repaint();
		}



	}


	public void keyTyped(KeyEvent e){
	}

	public void keyReleased(KeyEvent e){
	}
}