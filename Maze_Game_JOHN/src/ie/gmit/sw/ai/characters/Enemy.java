package ie.gmit.sw.ai.characters;

import java.awt.Graphics;

import javax.swing.JFrame;

import ie.gmit.sw.ai.runner.GameView;
import ie.gmit.sw.ai.runner.runner;
import ie.gmit.sw.ai.traverse.enemy_walk;


public class Enemy extends JFrame implements characters{

	private int index;
	private int i=0;
	private int j=0;
	private static runner runner=new runner();
	private static GameView view=new GameView();
	private static int blood=1;
	public Enemy(int index) {
		setIndex(index);
		// TODO Auto-generated constructor stub
	}

	public Enemy(int i, int j) {
		// TODO Auto-generated constructor stub
		this.i=i;
		this.j=j;
		generate(i,j);
	}
	public void generate(int i,int j){
		
		runner.mazeData[i][j]=4;
	}
	public Enemy(){
		
	}
	
	public void paint(Graphics g){
		paintComponents(g);
		System.out.println("paint a picture");
		g.drawImage(view.images[5], i*20, j*20, null);
	}
	
	
	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public void setIndex(int index) {
		this.index=index;
		
	}

	public int getBlood() {
		// TODO Auto-generated method stub
		
		return blood;
	}

	public static void setBlood(int blood) {
		Enemy.blood = blood;
	}

}
