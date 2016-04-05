package ie.gmit.sw.ai.characters;

import ie.gmit.sw.ai.Maze.Dir;
import ie.gmit.sw.ai.Maze.Maze_Node;
import ie.gmit.sw.ai.Maze.Type;

public class Wepon implements characters{
	private int col;
	private int row;
	private Type type;
	private int index;
	public static int usetimes=1;
	
	public Wepon(int index){
		setIndex(index);
	}
	
	public void use_times(){
		
	}
	
	public void getWepon(){
		usetimes++;
		
	}
	
	public void attack(){
		usetimes--;
	}
	
	public Wepon() {
		// TODO Auto-generated constructor stub
	
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
