/* CRITTERS Critter1.java
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
 * Each time step, this Critter walks in a random direction. During an 
 * encounter, this Critter will not want to fight and will attempt to walk
 * away in a set direction. 
 */
public class Critter1 extends Critter {
	
	private int direction;

	public Critter1() {
		direction = Critter.getRandomInt(8);
	}

	/** 
	 * During each time step, walks in a random direction.
	 */
	@Override
	public void doTimeStep() {
		direction = Critter.getRandomInt(8);
		walk(direction);
	}

	/** 
	 * During an encounter, will not want to fight.
	 * It will attempt to walk away in a set direction.
	 * @return false
	 */
	@Override
	public boolean fight(String oponent) {
		walk(direction);
		return false;
	}
	
	/** 
	 * Returns the String representation of Critter1
	 * @return 1
	 */
	@Override
	public String toString(){
		return "1";
	}

}