package ie.gmit.sw.ai.fuzzy;

import ie.gmit.sw.ai.characters.Enemy;
import ie.gmit.sw.ai.characters.Hero;
import ie.gmit.sw.ai.characters.Wepon;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class fuzzyLogic {
	private static Wepon wepon =new Wepon();
	private static Enemy enemy =new Enemy();
	private static Hero hero=new Hero();
	public double fight() {
		FIS fis = FIS.load("fcl/ruler.fcl", true); //Load and parse the FCL
		 //Display the linguistic variables and terms
		 FunctionBlock functionBlock = fis.getFunctionBlock("fight");
	        
	        // Show 
	        JFuzzyChart.get().chart(functionBlock);
	        fis.setVariable("weapon", Integer.valueOf(wepon.usetimes));
			fis.setVariable("hero", Integer.valueOf(hero.getBlood()));
			fis.setVariable("enemy", Integer.valueOf(enemy.getBlood()));
			fis.evaluate();

			Double victory = fis.getVariable("victory").getValue();

			System.out.println(victory);
			return victory;
	}
}
