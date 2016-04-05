package ie.gmit.sw.ai.runner;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;

import javax.imageio.*;
import javax.swing.*;

import ie.gmit.sw.ai.Maze.Maze_Generate;
import ie.gmit.sw.ai.characters.Boom;
import ie.gmit.sw.ai.characters.Enemy;
import ie.gmit.sw.ai.characters.Hero;
import ie.gmit.sw.ai.characters.characters;
import ie.gmit.sw.ai.traverse.enemy_walk;
import ie.gmit.sw.ai.traverse.traverse_DFS;


public class GameView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;	
	private static final int IMAGE_COUNT = 30;
	private int cellspan = 5;	
	private int cellpadding = 2;
	public  static int[][] maze;
	public BufferedImage[] images;
	private Maze_Generate mg=new Maze_Generate();
	
	//set action state
	private int enemy_state = 5;
	private int hero_state = 7;
	private int hero_attack = 12;

	public Timer timer;
	
	private int currentRow;
	private int currentCol;
	
	// Boolean zoomOut
	private boolean zoomOut = false;
	private int imageIndex = -1;
	private characters[][] character;

	private boolean shortpath=false;
	private static int nu=1;
	public GameView(){
	}

	public GameView(int[][] mazeData) throws Exception{
		init();
		this.maze = mazeData;
		character=new runner().getCharacter();
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);
		timer = new Timer(300, this);
		timer.start();
		/*GameView view=new GameView();
		enemy_walk thread1= view.new enemy_walk();*/
	}

	public void setCurrentRow(int row) {
		System.out.println(row);
		if (row < cellpadding){
			currentRow = cellpadding;
		}else if (row > (maze.length - 1) - cellpadding){
			currentRow = (maze.length - 1) - cellpadding;
		}else{
			currentRow = row;
		}
	}

	public void setCurrentCol(int col) {
		if (col < cellpadding){
			currentCol = cellpadding;
		}else if (col > (maze[currentRow].length - 1) - cellpadding){
			currentCol = (maze[currentRow].length - 1) - cellpadding;
		}else{
			currentCol = col;
		}
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public int getCurrentCol() {
		return currentCol;
	}
	
	//Setting enemy_walk walk Direction
	
	static int u=	0;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		cellspan = zoomOut ? maze.length : 5;         
		final int size = DEFAULT_VIEW_SIZE/cellspan;
		g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
		g2.drawImage(images[enemy_state], currentRow*size, currentCol*size, null);

		//show hero state
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman",1,30));
		g2.drawString("Blood :" + runner.hero.getBlood(), 30, DEFAULT_VIEW_SIZE+30);
		g2.drawString("Wepon :" + runner.wepon.usetimes, 230, DEFAULT_VIEW_SIZE+30);
		g2.drawString("Wall break tool :" + runner.wallbreak.getNumber(), 430, DEFAULT_VIEW_SIZE+30);
		g2.drawString("Bomb :" + new Boom().getNumber(), 30, DEFAULT_VIEW_SIZE+80);
		int[][] mmm =runner.mmm;

		
		//Paint Cell of Game
		for(int row = 0; row < cellspan; row++) {

			for (int col = 0; col < cellspan; col++){  
				int x1 = col * size;
				int y1 = row * size;

				int index = 0;

				if (zoomOut){
					mg.getShortPath();
					mmm=mg.node_set;
					index = maze[row][col];

					if(shortpath){
						for(int i=0;i<mmm.length;i++){

							for(int j=0;j<mmm[i].length;j++){
								int x2 = j * size;
								int y2 = i * size;
								if(mmm[i][j]==1){

									g2.setColor(Color.red);

									g2.setFont(new Font("TimesRoman",1,15));
									g2.drawString(">",x2+15, y2+15);
									
								}
								
								if(maze[i][j]==4){
									g2.setColor(Color.yellow);
									g2.fillRect(x2, y2,15, 15);
								}
							}
						}
					}

					if (row == currentRow && col == currentCol){
						g2.setColor(Color.BLACK);
						g2.fillRect(x1, y1, size, size);
						continue;
					}



				}else{

					index = maze[currentRow - cellpadding + row][currentCol - cellpadding + col];
					//	System.out.println(ch);
				}

				if (index == 0){        			
					imageIndex = -1;;
				}else if (index==999){// visited road
					imageIndex=999;;
				}/*else if (index==998){// break wall 
					imageIndex=998;;
				}*/else if (index == 1){ // wall
					imageIndex = 0;

				}else if (index ==666){ // paint hero 
					imageIndex = hero_state;;
				}else if (index == 3){   //bomb
					imageIndex = 3;;
				}else if (index == 2){
					imageIndex = 1;;
				}else if (index == 4){
					//new Enemy(row,col).paint(g2);;  
					imageIndex=enemy_state;;
				}else if (index == 10){
					imageIndex = 10;;       			
				}else if (index == 11){
					imageIndex = 11;;       			
				}else if (index == 12){
					imageIndex = hero_attack;;       			
				}else if (index == 19){
					imageIndex = 19;;       			
				}else{
					imageIndex = -1;
				}

				
				g2.setColor(Color.LIGHT_GRAY);
				g2.fillRect(x1, y1, size, size);
				
				if (imageIndex >= 0&& imageIndex!=999 && imageIndex!=998&& imageIndex!=5){
					g2.drawImage(images[imageIndex], x1, y1, null);


				}else if(imageIndex==999){
					g2.setColor(Color.white);
					g2.fillRect(x1, y1, size, size);
					
				}/*else if(imageIndex==998){
					g2.setColor(Color.white);
					g2.fillRect(x1, y1, size, size);
					mg.node_set[currentRow - cellpadding + row][currentCol - cellpadding + col]=1;
				}*/else if(imageIndex==5){
					new enemy_walk().start();
					if(nu%2!=0){
						g2.drawImage(images[imageIndex], x1, y1, null);	
						
						nu++;
						
					}else{

						// Create a new enemy_walk();
						// check enemy next step is valid or not   is valid go next step 
						// throught action preformed to sleep 200ms

						new enemy_walk();
						
							u=	enemy_walk.check(currentRow - cellpadding + row,currentCol - cellpadding + col);
						
					System.out.println(u+"***");
						if(u==4){
							g2.drawImage(images[imageIndex], x1+size, y1, null);
							enemy_collision((currentRow - cellpadding + row),(currentCol - cellpadding + col));
				//			System.out.println("now location==="+maze[(currentRow - cellpadding + row)][(currentCol - cellpadding + col+1)]);
							
						}else if(u==3){
							g2.drawImage(images[imageIndex], x1-size, y1, null);
							enemy_collision((currentRow - cellpadding + row),(currentCol - cellpadding + col));
							System.out.println("now location==="+maze[(currentRow - cellpadding + row)][(currentCol - cellpadding + col-1)]);

						}else if(u==2){
							g2.drawImage(images[imageIndex], x1, y1+size, null);
							enemy_collision((currentRow - cellpadding + row),(currentCol - cellpadding + col));
							System.out.println("now location==="+maze[(currentRow - cellpadding + row+1)][(currentCol - cellpadding + col)]);

							
						}else if(u==1){
							g2.drawImage(images[imageIndex], x1, y1-size, null);
							enemy_collision((currentRow - cellpadding + row),(currentCol - cellpadding + col));
							System.out.println("now location==="+maze[(currentRow - cellpadding + row-1)][(currentCol - cellpadding + col)]);

						}
						nu++;
					
						
						//		g2.drawImage(images[imageIndex], x1-size, y1, null);

						
					}
				}else{

					


				}      		
			}
		}
		
		if(Hero.blood<=0){
			paintDead(g2);
		}
		
	}
	
	
	public void enemy_collision(int x1,int y1){
		if(maze[x1][y1]==666){
			maze[x1][y1]=7;
		//	System.out.println("x1"+x1+"x2"+y1);
			try {
				runner.ccc=true;
				System.out.println("666");
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void toggleZoom(){

		zoomOut = !zoomOut;		
		System.out.println(zoomOut);
	}
	int times=0;

	public void resetAttack(){
		if(times==2){
			times=0;
			runner.ccc=false;
		}
	}

	public void actionPerformed(ActionEvent e) {	
		resetAttack();


		if (enemy_state < 0 || enemy_state == 5){
			enemy_state = 6;
			try {
				Thread.currentThread();
				Thread.sleep(200);

			} catch (InterruptedException c) {
				// TODO Auto-generated catch block
				c.printStackTrace();
			}
		}else{
			enemy_state = 5;
		}
		try {
			Thread.currentThread();
			Thread.sleep(200);

		} catch (InterruptedException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}


		if (hero_state < 0 || hero_state == 7){
			hero_state = 8;
		}else if(hero_state < 0 || hero_state == 8){
			hero_state = 9;
		}else{
			hero_state = 7;
		}

		//	System.out.println(ccc+"``````");
		if(runner.ccc){
			
			System.out.println(runner.ccc);
			if( hero_attack==12){

				hero_attack = 13;
			}else if(hero_attack == 13){

				hero_attack = 14;
			}else if(hero_attack == 14){
				times++;
				System.out.println(times);
				hero_attack = 15;
			}else if(hero_attack == 15){
				hero_attack = 16;
			}else if(hero_attack == 16){
				hero_attack = 17;
			}else if( hero_attack == 17){
				hero_attack = 18;
			}else{

				hero_attack = 12;

			}

			if(times>=2){
				hero_attack=7;
				
			}


		}
		//	/	times=0;
		//	runner.ccc=false;
		this.repaint();
	}

	/*public void he(){
		int iii=0;

		if( hero_attack==12){
			iii++;
			hero_attack = 13;
		}else if(hero_attack == 13){
			times++;
			System.out.println(times+"`````"+iii);
			hero_attack = 14;
		}else if(hero_attack == 14){
		//	times++;
			hero_attack = 15;
		}else if(hero_attack == 15){
			hero_attack = 16;
		}else if(hero_attack == 16){
			hero_attack = 17;
		}else if( hero_attack == 17){
			hero_attack = 18;
		}else{

			hero_attack = 12;

		}
	}*/

	private void init() throws Exception{
		images = new BufferedImage[IMAGE_COUNT];
		images[0] = ImageIO.read(new java.io.File("resources/mon.png"));
		images[1] = ImageIO.read(new java.io.File("resources/sword.png"));		
		images[2] = ImageIO.read(new java.io.File("resources/help.png"));
		images[3] = ImageIO.read(new java.io.File("resources/bomb.png"));
		images[4] = ImageIO.read(new java.io.File("resources/h_bomb.png"));
		images[5] = ImageIO.read(new java.io.File("resources/spider_down.png"));
		images[6] = ImageIO.read(new java.io.File("resources/spider_up.png"));
		images[7] = ImageIO.read(new java.io.File("resources/hero1.gif"));
		images[8] = ImageIO.read(new java.io.File("resources/hero2.gif"));
		images[9] = ImageIO.read(new java.io.File("resources/hero3.gif"));
		images[10] = ImageIO.read(new java.io.File("resources/trees.png"));
		images[11] = ImageIO.read(new java.io.File("resources/door.png"));
		images[12] = ImageIO.read(new java.io.File("resources/attack1.jpg"));
		images[13] = ImageIO.read(new java.io.File("resources/attack2.jpg"));
		images[14] = ImageIO.read(new java.io.File("resources/attack3.jpg"));
		images[15] = ImageIO.read(new java.io.File("resources/attack4.jpg"));
		images[16] = ImageIO.read(new java.io.File("resources/attack5.jpg"));
		images[17] = ImageIO.read(new java.io.File("resources/attack6.jpg"));
		images[18] = ImageIO.read(new java.io.File("resources/attack7.jpg"));
		images[19] = ImageIO.read(new java.io.File("resources/health.png"));

	}

	public boolean isShortpath() {
		return shortpath;
	}

	public void setShortpath() {
		shortpath = !shortpath;		

	}

	public void paintDead(Graphics g) {
		g.setColor(Color.red);
		g.drawString("You are dead. GAME OVER", 200, 400);
		timer.stop();
	}

}