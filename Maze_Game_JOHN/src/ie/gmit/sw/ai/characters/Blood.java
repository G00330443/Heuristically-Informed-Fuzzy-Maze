package ie.gmit.sw.ai.characters;

public class Blood implements characters{
	private int index;
	
	public Blood(int index) {
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
