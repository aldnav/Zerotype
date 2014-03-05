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
    
    private void initPopulation() {
    	population = new ArrayList<Agent>();
    	for (int i = 0; i < pref.populationSize; i++) {
    		population.add(new Agent());
    	}
    	susceptible = pref.populationSize;
    }
    
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
    
    private void initResistant() {

    }

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
    
    public void simulate() {
    	int run = pref.runDuration;
    	if (pref.inYears) {
    		run *= 365;
    	}
    	System.out.println("Simulation started.");
    	
    	// Go for every time step from day 1    	
    	for (int i = 0; i < run; i++) {
    		// Evaluate each person in population for possiblity of getting infected
    		for (Agent human : population) {
    			if (human.infectWith(0)) {
    				infected++;
    				susceptible--;
    			}
    		}
    		
    		// Evaluate each person's latency rate
    		for (Agent human: population) {
    			if (human.isInfectedWith(0))
    				human.updateLatency(0);
    		}
    		
    		System.out.print((i+1) + " : ");
    		displayStatus();
    	}
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
