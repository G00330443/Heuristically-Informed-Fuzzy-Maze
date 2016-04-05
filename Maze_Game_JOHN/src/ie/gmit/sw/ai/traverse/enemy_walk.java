package ie.gmit.sw.ai.traverse;

import ie.gmit.sw.ai.Maze.*;
import ie.gmit.sw.ai.runner.*;

public class enemy_walk extends Thread{

	static Maze_Generate mg=new Maze_Generate();
	private static int row=mg.getROWMAZE();
	private static int col=mg.getROWMAZE();
	private static Dir dir;
	private static runner runner=new runner();
	private static GameView view=new GameView();
	
	public enemy_walk(){
		
	}
	
	public static int check(int i,int j){
		System.out.println(i+"???"+j);
		
		int l=0;
		if(valid(i,j,i+1,j)){
			System.out.println(view.maze[i+1][j]);
			l= 1;
		}else if(valid(i,j,i-1,j)){
			System.out.println(view.maze[i-1][j]);
			l= 2;
		}else if(valid(i,j,i,j+1)){
			System.out.println(view.maze[i][j+1]);
			l= 3;
		}else if(valid(i,j,i,j-1)){
			System.out.println(view.maze[i][j-1]);
			l= 4;
		}
		
		
		return l;
	}
	
	public static int[][] traverse(int i1, int j1, int i2, int j2) {
		int[][] c=new int[2][2];
		if(valid(i1,j1,i2,j2)){
			switch(dir){
			case Up:
				c[0][0]=i2-1;
				c[0][1]=j2;
				break;
			case Down:
				c[0][0]=i2+1;
				c[0][1]=j2;
				break;
			case Left:
				c[0][0]=i2;
				c[0][1]=j2-1;
				break;
			case Right:
				c[0][0]=i2;
				c[0][1]=j2+1;
				break;
			}
		}
		return c;
	}

	private static boolean valid(int i1, int j1, int i2, int j2) {
		boolean result = false;
		
		if (i2 >= 0 && i2 < 2 * row + 1 && j2 >= 0 && j2 < 2 * col + 1 ){
			if( view.maze[i2][j2] == 666 || view.maze[i2][j2] == 0|| view.maze[i2][j2] == 999){
				if (i1 == i2 + 1 ){// 从上往下
					dir=dir.Up;
					result = true;
				}
				else if (i1 == i2 - 1 ){// 从下往上
					dir=dir.Down;
					result = true;
				}
				else if (j1 == j2 + 1){// 从左往右
					dir=dir.Left;
					result = true;
				}
				else if (j1 == j2 - 1 ){// 从右往左
					dir=dir.Right;
					result = true;
				}
			}
		}
		return result;
	}
}

