package ie.gmit.sw.ai.characters;

public class Road implements characters{
	private int index;
	
	public Road(int index) {
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
