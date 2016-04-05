package ie.gmit.sw.ai.Maze;

public class Maze_Node {
	private int col;
	private int row;
	private Type node_type;
	private Dir node_dir;
	
	public Maze_Node(){
		
	}
	public Maze_Node(int row,int col){
		this.col=col;
		this.row=row;
	}
	
//	public boolean checkRouter(int )
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	
	public Type getNode_type() {
		System.out.println(node_type+"()())");
		return node_type;
	}
	public void setNode_type(Type node_type) {
		this.node_type = node_type;
	}
	
	
	public Dir getNode_dir() {
		return node_dir;
	}
	public void setNode_dir(Dir node_dir) {
		this.node_dir = node_dir;
	}
	
}
