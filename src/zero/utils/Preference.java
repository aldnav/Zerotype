package zero.utils;

/**
 * Created by Bonz-pc on 2/24/14.
 */
public class Preference {

    public static int populationSize;
    public static double infectionRate;
    public static int initialInfected;
    public static int initialRecovered;
    public static int runDuration;
    public static int neighbors;
    public static boolean inYears;
    
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
