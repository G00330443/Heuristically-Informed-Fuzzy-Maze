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
	
	public static int[][] traverse(int row1, int col1, int nextrow, int nextcol) {
		int[][] c=new int[2][2];
		if(valid(row1,col1,nextrow,nextcol)){
			switch(dir){
			case Up:
				c[0][0]=nextrow-1;
				c[0][1]=nextcol;
				break;
			case Down:
				c[0][0]=nextrow+1;
				c[0][1]=nextcol;
				break;
			case Left:
				c[0][0]=nextrow;
				c[0][1]=nextcol-1;
				break;
			case Right:
				c[0][0]=nextrow;
				c[0][1]=nextcol+1;
				break;
			}
		}
		return c;
	}

	private static boolean valid(int row1, int col1, int nextrow, int nextcol) {
		boolean result = false;
		
		if (nextrow >= 0 && nextrow < 2 * row + 1 && nextcol >= 0 && nextcol < 2 * col + 1 ){
			if( view.maze[nextrow][nextcol] == 666 || view.maze[nextrow][nextcol] == 0|| view.maze[nextrow][nextcol] == 999){
				if (row1 == nextrow + 1 ){// 从上往下
					dir=dir.Up;
					result = true;
				}
				else if (row1 == nextrow - 1 ){// 从下往上
					dir=dir.Down;
					result = true;
				}
				else if (col1 == nextcol + 1){// 从左往右
					dir=dir.Left;
					result = true;
				}
				else if (col1 == nextcol - 1 ){// 从右往左
					dir=dir.Right;
					result = true;
				}
			}
		}
		return result;
	}
}

