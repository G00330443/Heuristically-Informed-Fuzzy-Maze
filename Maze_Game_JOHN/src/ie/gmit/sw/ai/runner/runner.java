package ie.gmit.sw.ai.runner;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.JFrame;


import ie.gmit.sw.ai.Maze.Maze_Generate;
import ie.gmit.sw.ai.Maze.Maze_Node;
import ie.gmit.sw.ai.characters.Boom;
import ie.gmit.sw.ai.characters.Door;
import ie.gmit.sw.ai.characters.Enemy;
import ie.gmit.sw.ai.characters.Hero;
import ie.gmit.sw.ai.characters.Road;
import ie.gmit.sw.ai.characters.Wall;
import ie.gmit.sw.ai.characters.Wepon;
import ie.gmit.sw.ai.characters.break_wall;
import ie.gmit.sw.ai.characters.characters;

public class runner extends JFrame implements KeyListener{
	static boolean ccc=false;
	private static final long serialVersionUID = 1L;

	public static int[][] mazeData;
	private int currentRow;
	private int currentCol;
	private GameView view;
	private Maze_Node[][] node;
	private boolean flag;
	//  public static int hero_blood=1;

	public static Hero hero=new Hero(1);
	public static Wepon wepon=new Wepon();
	public static break_wall wallbreak=new break_wall();
	public Maze_Generate mg=new Maze_Generate();
	public static Boom bomb=new Boom();
	//characters
	private characters[][] character=new characters[100][100];
	
	// 
	public static boolean keeprunning=true;
	public runner(){

	}

	public runner(Maze_Generate mazeUtils) throws Exception {
		//hero.setBlood(1);
		mazeData = mazeUtils.Maze(20, 20);
		mmm=mg.Getset();
		view = new GameView(this.mazeData);
		set_other_character();
		placePlayer(1,0);

		Dimension d=new Dimension(GameView.DEFAULT_VIEW_SIZE,GameView.DEFAULT_VIEW_SIZE+100);
		view.setPreferredSize(d);
		view.setMinimumSize(d);
		view.setMaximumSize(d);
		//   checks = mazeUtils.getChecks();
		JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(this);
		f.getContentPane().setLayout(new FlowLayout());
		f.add(view);
		f.setSize(1000,1000);
		f.setLocation(100,0);
		f.pack();
		f.setVisible(true);


		/*mazeData = mazeUtils.Maze(20, 20);
		mmm=mg.getChecks();
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭当前窗体时关闭程序
	        this.setBounds(10, 10, 800, 700);
	        this.setVisible(true);*/
	}

	// check blood 
	public void play(){
		while(keeprunning){
			
		}
		
		if(hero.getBlood()==0){
			System.out.println("You are dead. GAME OVER");
			keeprunning = false;
			
		}
	}
	
	
	//set other characters 
	public void set_other_character(){
		for(int i=0;i<mazeData.length;i++){
			for(int j=0;j<mazeData[i].length;j++){
				if(mazeData[i][j]==1){

					Random random=new Random();
					if(random.nextInt(2)==1){
						mazeData[i][j]=10;
					}

					if(random.nextInt(800)>(800-100)){
						switch(random.nextInt(5)){
						case 0:
							mazeData[i][j]=1;
							//			character[i][j]=new Wall(1);
							break;
						case 1:
							mazeData[i][j]=2;
							//			character[i][j]=new Wepon(2);
							//	System.out.println(character[i][j].getIndex()+"```````````````");
							break;
						case 2:
							mazeData[i][j]=3;
							//			character[i][j]=new Boom(3);
							break;

						case 5:
							mazeData[i][j]=7;
							//		character[i][j]=new Door(8);
							break;

						}
					}
				}else if(mazeData[i][j]==0 || mazeData[i][j]==999){ 
					// this function used to generate different character and can set possibility of this
					if(new Random().nextInt(100)<1){
						mazeData[i][j]=11;
					}

					if(new Random().nextInt(100)<2){
						//	mazeData[i][j]=4;
						new Enemy(i,j);
					}
					
					if(new Random().nextInt(100)<1){
						//	mazeData[i][j]=4;
						mazeData[i][j]=19;
					}


				}
			}
		}

	}

	//place player by curretrow and currentcol
	public void placePlayer(int r,int c){   	
		this.currentRow =r;
		this.currentCol = c;
		mazeData[this.currentRow][this.currentCol] = 0;
		updateView(); 		
	}

	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

	
	// keyListener to listen keyboard 
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < 100 - 1) {
			if (isValidMove(currentRow, currentCol + 1)) currentCol++;   		
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
			if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
		}else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
			if (isValidMove(currentRow - 1, currentCol)) currentRow--;
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < 100 - 1) {
			if (isValidMove(currentRow + 1, currentCol)) currentRow++;        	  	
		}else if (e.getKeyCode() == KeyEvent.VK_Z){
			System.out.println(currentRow);
			view.toggleZoom();

		}else if (e.getKeyCode() == KeyEvent.VK_ENTER){
			System.out.println("676767");
			view.setShortpath();

		}else if (e.getKeyCode() == KeyEvent.VK_C){
		//	System.out.println("676767");
			if(bomb.getNumber()>0){

				
				bomb.number--;
				try {
					System.out.println("Throw a Bomb , and wait 1 sec exposion");
					
					bomb.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("boom");
				bomb.exposion(currentRow,currentCol);
			}
		}
		else{
			return;
		}

		repaint();
		updateView();       
	}

	
	//check next step is valid or no 
	private boolean isValidMove(int r, int c){
		Collision_check cc=new Collision_check( mazeData,  r,  c , currentRow,currentCol);



		return cc.Collision_checks();


		/*if (r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c] == 0 || mazeData[r][c]==999){
			mazeData[currentRow][currentCol] = 999;
			mazeData[r][c] = 4;
			flag=true;
			return flag;
		}else{
			flag=false;
			return flag; //Can't move
		}*/
	}


	public static int [][] mmm;
	/*@Override
	public void paint(Graphics g) {
		paintComponents(g);
		g.drawString("Blood :" + hero.getBlood(), 10, 60);
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[i].length; j++) {
				switch (mazeData[i][j]) {
				case 0:
					g.setColor(Color.white);
					break;
				case 1:
					g.setColor(Color.BLACK);
					break;
				default:
					break;
				}

				g.fillRect(40 + j * 15, 40 + i
						* 15, 20, 20);
				g.setColor(Color.black);
				g.drawRect(40 + j * 15, 40 + i
						* 15, 20, 20);
			}
		}

		// mmm=mg.getChecks();
		if ( mmm!= null)
			for (int i = 0; i < mmm.length; i++) {
				for (int j = 0; j < mmm[i].length; j++) {
					if (mmm[i][j] == 1) {
						System.out.println(mmm[i][j]+"-"+i+"-"+j);
						

						g.setColor(Color.red);
						g.fillArc(40 + j * 15,40 + i * 15,20, 20, 0, 360);
					}
				}
			}

	}*/



	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public characters[][] getCharacter() {
		return character;
	}

	public void setCharacter(characters[][] character) {
		this.character = character;
	}



}