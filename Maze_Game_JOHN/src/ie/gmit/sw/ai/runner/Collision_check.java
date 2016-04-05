package ie.gmit.sw.ai.runner;

import java.util.Random;

import ie.gmit.sw.ai.Maze.Maze_Generate;
import ie.gmit.sw.ai.characters.Boom;
import ie.gmit.sw.ai.characters.Hero;
import ie.gmit.sw.ai.fuzzy.fuzzyLogic;

//This class is used to check player next step state 



public class Collision_check {
	private int r;
	private int c;
	private int currentRow;
	private int currentCol;
	private int[][] mazeData;

	fuzzyLogic fuzzylogic=new fuzzyLogic();
	GameView view=new GameView();
	Random random=new Random();
	int ccc=0;

	runner runner=new runner();
	boolean flag;

	public Collision_check(int[][] mazeData, int r, int c, int currentRow, int currentCol) {

		this.r=r;
		this.c=c;
		this.currentCol=currentCol;
		this.currentRow=currentRow;
		this.mazeData=mazeData;
	}
	public Collision_check() {
		// TODO Auto-generated constructor stub
	}

	public boolean Collision_checks() {

		if(mazeData[r][c]==0){
			flag=	Roadcheck();
		}else if(mazeData[r][c]==3){
			flag=	bombcheck();
		}else if (mazeData[r][c]==999){
			flag= Roadcheck();
		}else if (mazeData[r][c]==2){
			flag= weponcheck();
		}else if (mazeData[r][c]==11){
			flag= doorcheck();
		}else if (mazeData[r][c]==1){
			flag= wallcheck();
		}else if (mazeData[r][c]==4){
			flag= enemycheck();
			//		/	runner.ccc=flase; 
		}else if (mazeData[r][c]==19){
			flag= healthcheck();
			//		/	runner.ccc=flase; 
		} 
		
		
		if((c==40)&&(r==39)){
			flag=victorycheck();
			view.timer.stop();
		}

		//	System.out.println(flag+"_+_+_+");
		return flag;

	}

	public boolean wallcheck(){
		//	int ccc=runner.hero.getBlood()-1;
		if(r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c] == 1&& runner.wallbreak.getNumber()>=1){
			runner.wallbreak.setNumber(runner.wallbreak.getNumber()-1);
			//	System.out.println(hero.getBlood());
			mazeData[currentRow][currentCol] = 999;
			mazeData[r][c] = 666;
			new Maze_Generate().node_set[currentRow][currentCol]=1;
			return true;
		}else{
			return false;
		}

	}

	public boolean Roadcheck(){
		if (r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && (mazeData[r][c] == 0)){
			mazeData[currentRow][currentCol] = 999;
			mazeData[r][c] = 666;

			return true;
		}else if(r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c]==999){
			mazeData[currentRow][currentCol] = 999;
			mazeData[r][c] = 666;

			return true;
		}/*else if(r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c]==998){
			mazeData[currentRow][currentCol] = 999;
			mazeData[r][c] = 666;

			return true;
		}*/else{
			return false;
		}
	}

	public boolean bombcheck(){
		//	int ccc=runner.hero.getBlood()-1;
		if(r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c] == 3){
			//	runner.hero.blood--;
			//	System.out.println(hero.getBlood());
			new Boom().number++;
			mazeData[r][c] = 1;
			return false;
		}else{
			return false;
		}

	}
	
	public boolean healthcheck(){
	
		if(r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c] == 19){
			mazeData[currentRow][currentCol] = 999;
			mazeData[r][c] = 666;
			Hero.blood++;
			return true;
		}else{
			return false;
		}

	}
	
	public boolean weponcheck(){
		//	int ccc=runner.hero.getBlood()-1;
		if(r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c] == 2){
			runner.wepon.getWepon();
			System.out.println(runner.wepon.usetimes);
			mazeData[r][c] = 1;
			return false;
		}else{
			return false;
		}

	}

	public boolean doorcheck(){
		//	int ccc=runner.hero.getBlood()-1;
		if(r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c] == 11){
			//	System.out.println(hero.getBlood());
			boolean xxx=true;
			int rr=0;
			int cc=0;
			while(xxx){
				rr=random.nextInt(15)+3;
				cc=random.nextInt(15)+2;

				if(mazeData[rr][cc]==0){
					xxx=false;
				}

			}
			//runner.placePlayer(1, 0);
			mazeData[currentRow][currentCol] = 999;
			return true;
		}else{
			return false;
		}

	}


	public boolean enemycheck(){
		//	int ccc=runner.hero.getBlood()-1;
		if(r <= mazeData.length - 1 && c <= mazeData[r].length - 1 && mazeData[r][c] == 4&& runner.wepon.usetimes>=1){
			runner.wepon.usetimes=runner.wepon.usetimes-1;

			//	System.out.println(hero.getBlood());
			mazeData[currentRow][currentCol] = 999;
			mazeData[r][c] = 12;
			runner.ccc=true;
			System.out.println(runner.ccc);
//			fuzzylogic.fight();
			return true;
		}else{
			Hero.blood=Hero.blood-1;

			//	System.out.println(hero.getBlood());
			mazeData[currentRow][currentCol] = 999;
			mazeData[r][c] = 12;
			runner.ccc=true;
			System.out.println(runner.ccc);
	//		fuzzylogic.fight();
			return true;
		}

	}
	
	public boolean victorycheck(){
		
			fuzzylogic.fight();
			System.out.println(r+"rrr"+c+"ccc");
			return false;
		
		
	}

}
