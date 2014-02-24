package zero.utils;

/**
 * Created by Bonz-pc on 2/24/14.
 */
public class Preference {

    static int populationSize;
    static double infectionRate;
    static int initialInfected;
    static int initialRecovered;
    static int runDuration;
    static boolean inYears;
    
    public String toString() {
    	String s = "Preference\n";
    	      s += "\tPop size: " + populationSize + "\t\tInfection Rate: " + infectionRate + "\n";
    	      s += "\tInitial infected: " + initialInfected + "\tInitial recovered: " + initialRecovered + "\n";
    	      if (inYears)
    	    	  s += "\tRun (years): " + runDuration;
    	      else
    	    	  s += "\tRun (days): " + runDuration;
    	      
    	return s;
    }
    
}
