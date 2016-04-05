package ie.gmit.sw.maze;

import ie.gmit.sw.ai.Maze.Maze_Generate;
import ie.gmit.sw.ai.runner.runner;

public class Maze {
	 static int i=0;
	 static runner rr=new runner(); 
	public static void main(String[] args) {
		add();
		add();
		System.out.println(i);
		
		int[][] mmm=new Maze_Generate().Getset();
		
	/*	for(int row = 0; row <=mmm.length; row++) {
			for (int col = 0; col <= mmm[row].length; col++){ 
				if(mmm[row][col]==0){
					System.out.println("1111111");
				}
				
			}
			}*/
		
		System.out.println(mmm.length);
	}
	
	public static void add(){
		i++;
	}
}
