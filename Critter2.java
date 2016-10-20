/* CRITTERS Critter2.java
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
 * Each time step, this Critter stays completely still. During an 
 * encounter, this Critter will attempt to fight, but will try to run
 * away from the encounter in a set direction first. 
 */
public class Critter2 extends Critter {
	
	private int direction;

	public Critter2() {
		direction = Critter.getRandomInt(8);
	}

	/** 
	 * During each time step, stays still.
	 */
	@Override
	public void doTimeStep() {
		return;

	}

	/** 
	 * During an encounter, will want to fight, 
	 * but will first try to run away in a set direction.
	 * @return true
	 */
	@Override
	public boolean fight(String oponent) {
		run(direction);
		return true;
	}
	
	/** 
	 * Returns the String representation of Critter2
	 * @return 2
	 */
	@Override
	public String toString(){
		return "2";
	}

}