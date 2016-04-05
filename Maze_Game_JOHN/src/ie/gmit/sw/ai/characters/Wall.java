package ie.gmit.sw.ai.characters;

public class Wall implements characters{
	private int index;
	
	public Wall(int index) {
		setIndex(index);
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

}
