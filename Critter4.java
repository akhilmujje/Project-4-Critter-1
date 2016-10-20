/* CRITTERS Critter4.java
 * EE422C Project 4 submission by 
 * Devisriram Akhil Mujje
 * 16470
 * Sriram Ravula
 * sr39533
 * 16475
 * Slip days used: 0
 * Git URL: https://github.com/akhilmujje/Project-4-Critter-1.git
 * Fall 2016
 */

package assignment4;

/**
 * Implements a new Critter that builds on the abstract Critter class. 
 * Each time step, this Critter runs in a random direction. During an 
 * encounter, this Critter will not want to fight and will attempt to run
 * away in a random direction.
 */
public class Critter4 extends Critter {
	
	int direction;

	public Critter4() {
		direction = Critter.getRandomInt(8);
	}

	/**
	 * During each time step, runs in a random direction.
	 */
	@Override
	public void doTimeStep() {
		direction = Critter.getRandomInt(8);
		run(direction);
	}

	/** 
	 * During an encounter, will not want to fight.
	 * Will attempt to run in a random direction.
	 * @return false
	 */
	@Override
	public boolean fight(String oponent) {
		direction = Critter.getRandomInt(8);
		run(direction);
		return false;
	}

	/** 
	 * Returns the String representation of Critter4
	 * @return 4
	 */
	@Override
	public String toString(){
		return "4";
	}
}