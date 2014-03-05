package zero.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	protected int latency1 = Environment.pref.latency1;
	protected int latency2 = Environment.pref.latency2;

	/**
	 * Each agent is assumed to have neighbors. Space ignored since
	 * its any agent could be anywhere at any time. Population seems
	 * to be considered heavier value. 
	 * */
	private List<Agent> neighbors;

	public Agent() {
		neighbors = new ArrayList<Agent>();
	}

	protected boolean isSusceptibleWith(int n) {
		return this.status[n][0] == 0 ? true : false;
	}

	protected boolean isInfectedWith(int n) {
		return this.status[n][1] == 1 ? true : false;
	}

	protected boolean isResistantWith(int n) {
		return this.status[n][2] == 1 ? true : false;
	}

	protected void setNeighbors(List<Agent> neighbors) {
		this.neighbors = neighbors;
	}
	
	protected boolean infectWith(int n) {
		if (this.isInfectedWith(n)) {
			return false;
		}
		
		boolean hasInfectiousNeighbor = false;
		for (Agent agent : this.neighbors) {
			if (agent.isInfectedWith(n)) {
				hasInfectiousNeighbor = true;
			}
			if (hasInfectiousNeighbor)
				break;
		}
		
		if (hasInfectiousNeighbor && (new Random(System.nanoTime()).nextDouble() < Environment.pref.infectionRate) ||
			this.latency1 <= 0) {
			this.status[n][0] = 1;
			this.status[n][1] = 1;
			return true;
		}
		return false;
	}
	
	protected void updateLatency(int n) {
		if (n == 0) {
			if (latency1 > 0)
				this.latency1--;
		} else if (n == 1) {
			this.latency2--;
		}
	}

}
