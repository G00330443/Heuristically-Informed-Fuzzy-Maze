package ie.gmit.sw.ai.characters;

import ie.gmit.sw.ai.runner.GameView;

public class Hero implements characters{
	private int index;
	private int row ;
	private int col;
	public static int blood;
	private Wepon wepon;
	
	public Hero(int blood) {
		setBlood(blood);
		// TODO Auto-generated constructor stub
	}

	public Hero() {
		
	}
	
	public void loose_blood(){
		this.blood--;
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
	
	public int getBlood() {
	//	System.out.println("Blood = "+blood);
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public Wepon getWepon() {
		return wepon;
	}

	public void setWepon(Wepon wepon) {
		this.wepon = wepon;
	}
	
	
	public int getRow() {
		return row;
	}

	public void setRow() {
		this.row = new GameView().getCurrentRow();
	}
	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	public void setCol() {
		this.col = new GameView().getCurrentRow();
	}

}
