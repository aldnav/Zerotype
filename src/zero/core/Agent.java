package zero.core;

import java.util.ArrayList;
import java.util.List;

public class Agent {

	/**
	 * Table that keeps track of status with respect to the serotype.
	 * 
	 * @param Row indicates what serotype. 0 = serotype 1, 1 = serotype 2
	 * @param Column indicates what state, 0 = susceptible, 1 = infected, 2 = resistant
	 * */
	public int[][] status = new int[][]{
		{0, 0, 0},
		{0, 0, 0}
	};

	/**
	 * Each agent is assumed to have neighbors. Space ignored since
	 * its any agent could be anywhere at any time. Population seems
	 * to be considered heavier value. 
	 * */
	private List<Agent> neighbors;

	public Agent() {
		neighbors = new ArrayList<Agent>();
	}

	public boolean isSusceptibleWith(int n) {
		return this.status[n][0] == 0 ? true : false;
	}

	public boolean isInfectedWith(int n) {
		return this.status[n][1] == 1 ? true : false;
	}

	public boolean isResistantWith(int n) {
		return this.status[n][2] == 1 ? true : false;
	}

	public void setNeighbors(List<Agent> neighbors) {
		this.neighbors = neighbors;
	}
	
	public boolean infectWith(int n) {
		
		if (this.isResistantWith(n)) {
			return false;
		}
		
		boolean neighborIsInfectious = true;		
		for (Agent neighbor: neighbors) {			
			if (this.isSusceptibleWith(n) && neighbor.isInfectedWith(n) && ((double)(Math.random()) < Environment.pref.infectionRate)) {
				neighborIsInfectious = true;
				break;
			}
		}
		if (neighborIsInfectious) {
			this.status[n][0] = 1;
			this.status[n][1] = 1;
			return true;
		}
		return false;
	}

}
