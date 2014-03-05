package zero.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import zero.utils.Preference;
import zero.utils.XMLParser;

/**
 * Created by Bonz-pc on 2/24/14.
 */

public class Environment {
    static Preference pref;
    List<Agent> population;
    int infected = 0;
    int susceptible = 0;
    
    public Environment() {
        pref = XMLParser.pull("src/zero/data/preferences.xml");
        System.out.println(pref);
        initPopulation();
        initNeighbors();
        initInfected();
    }
    
    /**
     * Initialize the population.
     * */
    private void initPopulation() {
    	population = new ArrayList<Agent>();
    	for (int i = 0; i < pref.populationSize; i++) {
    		population.add(new Agent());
    	}
    	susceptible = pref.populationSize;
    }
    
    /**
     * Initialize infected humans. 
     * */
    private void initInfected() {
    	for (Agent human: population) {
    		human.status[0][0] = 1;
			human.status[0][1] = 1;
			human.latency1 = (int)(Math.random() * ((human.latency1) + 1));
    		infected++;
    		susceptible--;
    		if (infected == pref.initialInfected)
    			break;
    	}
    }
    
    /**
     * Initialize resistant humans.
     * */
    private void initResistant() {

    }
    
    /**
     * Initialize each human's neighbors.
     * */
    private void initNeighbors() {
    	// the problem of getting itself as neighbor is not yet solved
    	List<Agent> poplist = new ArrayList<Agent>(population);
    	for (Agent human: population) {
    		Collections.shuffle(poplist);
    		human.setNeighbors(poplist.subList(0, pref.neighbors));
    	}
    	population = new ArrayList<Agent>(poplist);
    	poplist = null;
    }
    
    /**
     * Simulation pseudo-code
     * 1. Determine run duration in days.
     * 2. Do the following From day 1 to day d.
     *    1. For each human in population
     *       1. Check if human could be infected.
     *       2. If human could be infected, increase number of infected (increment),
     *          and decrease the number of susceptible.
     *       3. Check if human is infected.
     *       4. If human is infected, update human latency due to infection. 
     * 		 5. If human is infected and latency is up, decrease number of infected,
     * 			and increase the number of resistant.
     *		 6. Update neighbor. (Work-around for non-matrix)
     * */    
    public void simulate() {
    	int run = pref.runDuration;
    	if (pref.inYears) {
    		run *= 365;
    	}
    	System.out.println("Simulation started.");
    	
    	List<Agent> poplist = new ArrayList<Agent>(population);
    	// Go for every time step from day 1    	
    	for (int i = 0; i < run; i++) {
    		// Evaluate each person in population for possiblity of getting infected
    		for (Agent human : population) {
    			if (human.infectWith(0)) {
    				infected++;
    				susceptible--;
    			}
    			if (human.isInfectedWith(0))
    				human.updateLatency(0);
    		}
    		
    		
    		for (Agent human: population) {
        		human.setNeighbors(poplist.subList(0, pref.neighbors));
        	}
    		Collections.shuffle(poplist);
    		
    		System.out.print((i+1) + " : ");
    		displayStatus();
    	}
    	poplist = null;
    	System.out.println("Simulation ended.");
    }
    
    private void displayStatus() {
    	System.out.println("S: " + susceptible + " I: " + infected);
    }

    public static void main(String[] args) {
        Environment env = new Environment();
        env.simulate();
        
    }

}
