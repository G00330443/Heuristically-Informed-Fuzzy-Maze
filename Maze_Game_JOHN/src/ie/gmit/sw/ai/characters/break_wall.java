package ie.gmit.sw.ai.characters;

public class break_wall implements characters{
	private int index;
	private static int number=1; 
	public break_wall(int index) {
		setIndex(index);
		// TODO Auto-generated constructor stub
	}

	public break_wall() {
		// TODO Auto-generated constructor stub
	}
	
	public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		break_wall.number = number;
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
}
