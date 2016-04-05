package ie.gmit.sw.ai.Maze;

import java.util.Random;

import javax.swing.CellEditor;

import ie.gmit.sw.ai.traverse.traverse_DFS;

public class Maze_Generate {
	private int col;

	private int row;
	private static int ROWMAZE=20;
	private Maze_Node node;
	private static Type type;
	public static int[][] node_set;

	private Type[][] wall_VER_set;
	private Type[][] wall_Hor_set;
	public static int [][] newMaze;
	private boolean [][] unVisited;
	private static Random character =new Random();

	public static void main(String[] args) {
		Maze_Generate c=new Maze_Generate();
		c.Maze(ROWMAZE, ROWMAZE);
		//	c.maze_check(node_set);
	}


	public int[][] Maze(int row,int col){
		this.row=row;
		this.col=col;

		this.node_set=new int[2*this.row+1][2*this.col+1];
		this.unVisited=new boolean[this.row][this.col];

		wall_Hor_set=new Type[row+1][col];
		wall_VER_set=new Type[row][col+1];


		reset();
		generate(0,0);
		setEntry_Exit();

		newMaze=new int[this.row*2+1][this.col*2+1];
		generateMaze();

		getShortPath();


		//	newMaze=maze_check(newMaze);
		//	System.out.println(newMaze[0][0]+"+++++++++=");
		return  newMaze;
	}


	//user DFS search to generate the short path
	public void getShortPath(){
		for (int i = 0; i < this.row; i++)
			for (int j = 0; j < this.col; j++)
				node_set[i][j] = 0;
		traverse_DFS.traverse(1, 0, 1,1);

		for (int i = 0; i < 2 * this.row + 1; i++)
			for (int j = 0; j < 2 * this.col + 1; j++)
				if (node_set[i][j] != 1)
					node_set[i][j] = -1;
	}

	//generater maze
	public void generateMaze(){
		for(int i=0; i<newMaze.length;i++){
			for(int j=0; j<newMaze[i].length;j++){
				if((i & 1) == 1 && (j & 1) == 1){
					newMaze[i][j]=0;
				}else{

					newMaze[i][j]=1;
				}

				if((i & 1) == 0 && (j & 1) == 1){
					if(wall_Hor_set[i/2][(j-1)/2]==type.Empty){
						newMaze[i][j]=0;
					}
				}

				if((i & 1) == 1 && (j & 1) == 0){
					if(wall_VER_set[(i-1)/2][j/2]==Type.Empty){
						newMaze[i][j]=0;
					}
				}

			}
		}
	}


	//set entry and exit
	public void setEntry_Exit(){
		//Set topLeft is entry so no wall exist in this left side
		wall_VER_set[0][0]=Type.Empty;

		System.out.println(wall_VER_set[0][0]+"------------");

		//Set BotRight is exit so no wall exist in this right side
		wall_VER_set[row-1][col]=Type.Empty;
		System.out.println(wall_VER_set[row-1][col]);

	}


	// this method to generate Maze is get idea from a book named(JAVA2 Game Design   publised by Tsinghua University Press)
	//Removal of a number of horizontal and vertical wall wall
	public void generate(int row,int col ){
		unVisited[row][col]=false;

		Random dir=new Random();

		//check lefttop 
		if(row==0&&col==0){
			while(unVisited[row+1][col] ||unVisited[row][col+1]){
				if(dir.nextInt(2)==0){

					if(unVisited[row+1][col]){
						wall_Hor_set[row+1][col]=type.Empty;
						generate(row+1,col);
					}
				}else{
					if(unVisited[row][col+1]){
						wall_VER_set[row][col+1]=type.Empty;
						generate(row,col+1);
					}
				}
			}
		}

		//check  botLeft
		if(row==this.row-1&&col==0){
			while(unVisited[row-1][col] ||unVisited[row][col+1]){
				if(dir.nextInt(2)==0){

					if(unVisited[row-1][col]){
						wall_Hor_set[row][col]=type.Empty;
						generate(row-1,col);
					}
				}else{
					if(unVisited[row][col+1]){
						wall_VER_set[row][col+1]=type.Empty;
						generate(row,col+1);
					}
				}
			}
		}

		//check top right

		if(row==0&&col==this.col-1){
			while(unVisited[row+1][col] || unVisited[row][col-1]){
				if(dir.nextInt(2)==0){

					if(unVisited[row+1][col]){
						wall_Hor_set[row+1][col]=type.Empty;
						generate(row+1,col);
					}
				}else{
					if(unVisited[row][col-1]){
						wall_VER_set[row][col]=type.Empty;
						generate(row,col-1);
					}
				}
			}
		}

		//check bot right

		if(row==this.row-1&&col==this.col-1){
			while(unVisited[row-1][col] ||unVisited[row][col-1]){
				if(dir.nextInt(2)==0){

					if(unVisited[row-1][col]){
						wall_Hor_set[row][col]=type.Empty;
						generate(row-1,col);
					}
				}else{
					if(unVisited[row][col-1]){
						wall_VER_set[row][col]=type.Empty;
						generate(row,col-1);
					}
				}
			}
		}

		//check top

		if(row==0 &&col!=0 && col!=this.col-1){
			while(unVisited[row][col+1] ||unVisited[row][col-1] || unVisited[row+1][col]){
				if(dir.nextInt(3)==0){

					if(unVisited[row][col+1]){
						wall_VER_set[row][col+1]=type.Empty;
						generate(row,col+1);
					}
				}else if(dir.nextInt(3)==1){
					if(unVisited[row][col-1]){
						wall_VER_set[row][col]=type.Empty;
						generate(row,col-1);
					}
				}else{
					if(unVisited[row+1][col]){
						wall_Hor_set[row+1][col]=type.Empty;
						generate(row+1,col);
					}
				}
			}
		}

		//check bot

		if(row==this.row-1 &&col!=0 && col!=this.col-1){
			while(unVisited[row][col-1] || unVisited[row][col+1] || unVisited[row-1][col]){
				if(dir.nextInt(3)==0){

					if(unVisited[row][col-1]){
						wall_VER_set[row][col]=type.Empty;
						generate(row,col-1);
					}
				}else if(dir.nextInt(3)==1){
					if(unVisited[row][col+1]){
						wall_VER_set[row][col+1]=type.Empty;
						generate(row,col+1);
					}
				}else{
					if(unVisited[row-1][col]){
						wall_Hor_set[row][col]=type.Empty;
						generate(row-1,col);
					}
				}
			}
		}

		//check left
		if(col==0 &&row!=0 && row!=this.row-1){
			while(unVisited[row-1][col] || unVisited[row+1][col] || unVisited[row][col+1]){
				if(dir.nextInt(3)==0){

					if(unVisited[row-1][col]){
						wall_Hor_set[row][col]=type.Empty;
						generate(row-1,col);
					}
				}else if(dir.nextInt(3)==1){
					if(unVisited[row+1][col]){
						wall_Hor_set[row+1][col]=type.Empty;
						generate(row+1,col);
					}
				}else{
					if(unVisited[row][col+1]){
						wall_VER_set[row][col+1]=type.Empty;
						generate(row,col+1);
					}
				}
			}
		}

		//check right
		if(col==this.col-1 &&row!=0 && row!=this.row-1){
			while(unVisited[row-1][col] || unVisited[row+1][col] || unVisited[row][col-1]){
				if(dir.nextInt(3)==0){

					if(unVisited[row-1][col]){
						wall_Hor_set[row][col]=type.Empty;
						generate(row-1,col);
					}
				}else if(dir.nextInt(3)==1){
					if(unVisited[row+1][col]){
						wall_Hor_set[row+1][col]=type.Empty;
						generate(row+1,col);
					}
				}else{
					if(unVisited[row][col-1]){
						wall_VER_set[row][col]=type.Empty;
						generate(row,col-1);
					}
				}
			}
		}

		//center
		if(row>0 && row<this.row-1 &&col>0 && col<this.col-1){
			while(unVisited[row-1][col] || unVisited[row+1][col] || unVisited[row][col-1] || unVisited[row][col+1]){
				if(dir.nextInt(4)==0){

					if(unVisited[row-1][col]){
						wall_Hor_set[row][col]=type.Empty;
						generate(row-1,col);
					}
				}else if(dir.nextInt(4)==1){
					if(unVisited[row+1][col]){
						wall_Hor_set[row+1][col]=type.Empty;
						generate(row+1,col);
					}
				}else if(dir.nextInt(4)==2){
					if(unVisited[row][col-1]){
						wall_VER_set[row][col]=type.Empty;
						generate(row,col-1);
					}
				}else{
					if(unVisited[row][col+1]){
						wall_VER_set[row][col+1]=type.Empty;
						generate(row,col+1);
					}
				}
			}
		}
	} 

	
	//reset all data 
	public void reset(){
		for (int i = 0; i < this.row; i++)
			for (int j = 0; j < this.col; j++)
				node_set[i][j] = 0;

		// ȫ�������ʼ��Ϊ"δ���"
		for (int i = 0; i < this.row; i++)
			for (int j = 0; j < this.col; j++)
				unVisited[i][j] = true;

		// ȫ��ˮƽǽ״̬��ʼ��Ϊ"����"
		for (int i = 0; i < this.row + 1; i++)
			for (int j = 0; j < this.col; j++)
				wall_Hor_set[i][j] = type.Wall;

		// ȫ����ֱǽ״̬��ʼ��Ϊ"����"
		for (int i = 0; i < this.row; i++)
			for (int j = 0; j < this.col + 1; j++)
				wall_VER_set[i][j] = type.Wall;
	}

	
	// check next step type 
	public String type_check(Type c){
		if(c==type.Empty){
			return "Empty";
		}else if(c==type.Wepon){
			return "Wepon";
		}else if(c==type.Blood){
			return "Blood";
		}else if(c==type.Enemy){
			return "Enemy";
		}else if(c==type.Wall){
			return "Wall";
		}else {
			return "Door";
		}
	}


	public int[][] Getset() {

		return (node_set);
	}

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
	public static int[][] getNode_set() {
		return node_set;
	}


	public static void setNode_set(int[][] node_set) {
		Maze_Generate.node_set = node_set;
	}


	public int[][] getNewMaze() {
		return newMaze;
	}


	public void setNewMaze(int[][] newMaze) {
		this.newMaze = newMaze;
	}


	public static int getROWMAZE() {
		return ROWMAZE;
	}


	public static void setROWMAZE(int rOWMAZE) {
		ROWMAZE = rOWMAZE;
	}

}
