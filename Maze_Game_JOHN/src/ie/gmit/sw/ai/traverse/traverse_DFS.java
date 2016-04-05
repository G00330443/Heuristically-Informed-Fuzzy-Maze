package ie.gmit.sw.ai.traverse;

import java.io.IOException;
import java.util.Scanner;

import ie.gmit.sw.ai.Maze.Maze_Generate;

public class traverse_DFS {

	static Maze_Generate mg=new Maze_Generate();
	private static int row=mg.getROWMAZE();
	private static int col=mg.getROWMAZE();


	public static  boolean traverse(int row1, int col1, int nextrow, int nextcol) {
		boolean flag = false;

		if (row1 == 1 && col1 == 0)// set the start point 
			mg.node_set[row1][col1]=1;

		if (valid(row1, col1, nextrow, nextcol)) {
			mg.node_set[nextrow][nextcol]=-1;// reset all cell in the path equal -1

			if (nextrow == 2 * row - 1 && nextcol == 2 * col){
				flag = true; 
			}
			else {
				
				// check top bot left and right is or not  in the short path
				
				flag = traverse(nextrow, nextcol, nextrow + 1, nextcol); 
				if (!flag)
					flag = traverse(nextrow, nextcol, nextrow, nextcol + 1); 
				if (!flag)
					flag = traverse(nextrow, nextcol, nextrow - 1, nextcol); 
				if (!flag)
					flag = traverse(nextrow, nextcol, nextrow, nextcol - 1); 
			}

			if (flag) // if flag==true  meaning this cell in short path
				mg.node_set[nextrow][nextcol]=1;
		} else {

		}

		return flag;
	}


	// below function is used to check next step is type.road or other characters 
	
	private static boolean valid(int row1, int col1, int nextrow, int nextcol) {
		boolean flag = false;

		if (nextrow >= 0 && nextrow < 2 * row + 1 && nextcol >= 0
				&& nextcol < 2 * col + 1)
			if (Maze_Generate.node_set[nextrow][nextcol] == 0) {
				if (row1 == nextrow - 1 && mg.newMaze[nextrow][nextcol] == 0){// top
					flag = true;
				}
				else if (row1 == nextrow + 1 && mg.newMaze[nextrow][nextcol] == 0){// bot
					flag = true;
				}
				else if (col1 == nextcol - 1 &&mg.newMaze[nextrow][nextcol] == 0){// left
					flag = true;
				}
				else if (col1 == nextcol + 1 && mg.newMaze[nextrow][nextcol] == 0){// right
					flag = true;
				}
			}
		return flag;
	}
}