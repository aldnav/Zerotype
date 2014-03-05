package zero.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import zero.utils.Preference;
import zero.utils.XMLParser;

/**
 * Created by Bonz-pc on 2/24/14.
 */

public class Environment {
    static Preference pref;
    Set<Agent> population;
    int infected1 = 0;

    public Environment() {
        pref = XMLParser.pull("src/zero/data/preferences.xml");
        initPopulation();
        displayInfectStatus();
        initInfected();
        displayInfectStatus();
        initResistant();
        initNeighbors();
        displayInfectStatus();
    }
    
    private void displayInfectStatus() {
    	int infected = 0;
    	for (Agent agent: population) {
    		if (agent.isInfectedWith(0)) {
    			infected++;
    		}
    	}
    	System.out.println("Infected: " + infected + " out of " + pref.populationSize);
    }

    private void initPopulation() {
    	population = new HashSet<Agent>();
    	for (int i = 0; i < pref.populationSize; i++) {
    		population.add(new Agent());
    	}
    }
    
    private void initInfected() {
    	for (Agent human: population) {
    		human.status[0][0] = 1;
			human.status[0][1] = 1;
    		infected1++;
    		if (infected1 == pref.initialInfected)
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
    	population = new HashSet<Agent>(poplist);
    	poplist = null;
    }
    
    private void destruct() {
    	pref = null;
    	population = null;
    }
    
    public void simulate() {
    	
    }

    public static void main(String[] args) {
        Environment env = new Environment();
        System.out.println(env.pref);
        env.simulate();
        System.out.println(env.infected1);
        env = null;
    }

}
