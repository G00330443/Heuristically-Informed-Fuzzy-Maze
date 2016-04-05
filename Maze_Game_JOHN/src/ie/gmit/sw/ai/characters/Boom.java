package ie.gmit.sw.ai.characters;

public class Boom extends Thread implements characters {
	private int index;
	public  static int number=1;
	private int row=0;
	private int col=0;
	private static ie.gmit.sw.ai.runner.runner runner=new ie.gmit.sw.ai.runner.runner();
	
	public Boom(int index) {
		setIndex(index);
		// TODO Auto-generated constructor stub
	}

	public Boom() {
		// TODO Auto-generated constructor stub
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

	public static int getNumber() {
		System.out.println(number);
		return number;
	}

	public static void setNumber(int number) {
		Boom.number = number;
	}


	public void exposion(int row, int col) {
		runner.mazeData[row+1][col]=999;
		runner.mazeData[row-1][col]=999;
		runner.mazeData[row][col+1]=999;
		runner.mazeData[row][col-1]=999;
		runner.mazeData[row-1][col+1]=999;
		runner.mazeData[row+1][col-1]=999;
		runner.mazeData[row-1][col-1]=999;
		runner.mazeData[row+1][col+1]=999;
	}

}
