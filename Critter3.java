/* CRITTERS Critter3.java
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
 * Each time step, this Critter runs in a set direction. During an 
 * encounter, this Critter will not want to fight and will reproduce, but 
 * will not flee from the encounter.
 */
public class Critter3 extends Critter {

	int direction;
	
	public Critter3() {
		direction = Critter.getRandomInt(8);
	}

	/** 
	 * During each time step, runs in a set direction.
	 */
	@Override
	public void doTimeStep() {
		run(direction);

	}

	/** 
	 * During an encounter, will not want to fight.
	 * This creature reproduces and will not flee from the encounter.
	 * @return false
	 */
	@Override
	public boolean fight(String oponent) {
		Critter3 c = new Critter3();
		reproduce(c, Critter.getRandomInt(8));
		return false;
	}

	/** 
	 * Returns the String representation of Critter3
	 * @return 3
	 */
	@Override
	public String toString(){
		return "3";
	}
}