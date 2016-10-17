/* CRITTERS Critter.java
 * EE422C Project 4 submission by 
 * Devisriram Akhil Mujje
 * 16470
 * Sriram Ravula
 * sr39533
 * 16475
 * Slip days used: 0
 * Fall 2016
 */

package assignment4;

import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */

public abstract class Critter {
	private static String myPackage;
	private static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// added fields
	private static Critter[][] position = new Critter[Params.world_height][Params.world_width];
	private boolean hasMoved = false;
	private boolean isFighting = false;
	// Gets the package name. This assumes that Critter and its subclasses are
	// all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	private static java.util.Random rand = new java.util.Random();

	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}

	/*
	 * a one-character long string that visually depicts your critter in the
	 * ASCII interface
	 */
	public String toString() {
		return "";
	}

	private int energy = 0;

	protected int getEnergy() {
		return energy;
	}

	private int x_coord;
	private int y_coord;

	protected final void walk(int direction) {
		if(!hasMoved){ //only move the critter if it hasn't moved before
			int next_x = x_coord; //variables to temporarily hold the coordinates you wish to move to 
			int next_y = y_coord;
			
			switch (direction) {
	
				case 0: {
					next_x += 1;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
		
					break;
				}
				case 1: {
					next_x += 1;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					next_y -= 1;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
				case 2: {
					next_y -= 1;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
				case 3: {
					next_x -= 1;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					next_y -= 1;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
				case 4: {
					next_x -= 1;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					break;
				}
				case 5: {
					next_x -= 1;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					next_y += 1;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
				case 6: {
					next_y += 1;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
				case 7: {
					next_x += 1;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					next_y += 1;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
			}
			
			hasMoved = true;
			
			if(isFighting){ //if the critter is in a fight, then only move if the new location is unoccupied
				if(canMove(next_x, next_y)){
					x_coord = next_x;
					y_coord = next_y;
				}
			}
			else{ //if the critter is not in a fight, then it can move freely into occupied locations
				x_coord = next_x;
				y_coord = next_y;
			}
			
		}
		this.energy -= Params.walk_energy_cost;
	}

	protected final void run(int direction) {
		if(!hasMoved){ //only move the critter if it hasn't moved before
			int next_x = x_coord; //variables to temporarily hold the coordinates you wish to move to 
			int next_y = y_coord;
			
			switch (direction) {
	
				case 0: {
					next_x += 2;
		
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
		
					break;
				}
				case 1: {
					next_x += 2;
		
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					next_y -= 2;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
				case 2: {
					next_y -= 2;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;			
					
					break;
				}
				case 3: {
					next_x -= 2;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					next_y -= 2;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					
					break;
				}
				case 4: {
					next_x -= 2;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					break;
				}
				case 5: {
					next_x -= 2;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					next_y += 2;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
				case 6: {
					next_y += 2;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
				case 7: {
					next_x += 2;
					
					if(next_x < 0)
						next_x += Params.world_width;
					else
						next_x = next_x % Params.world_width;
					
					next_y += 2;
					
					if(next_y < 0)
						next_y += Params.world_height;
					else
						next_y = next_y % Params.world_height;
					
					break;
				}
			}
			hasMoved = true;
			
			if(isFighting){ //if the critter is in a fight, then only move if the new location is unoccupied
				if(canMove(next_x, next_y)){
					x_coord = next_x;
					y_coord = next_y;
				}
			}
			else{ //if the critter is not in a fight, then it can move freely into occupied locations
				x_coord = next_x;
				y_coord = next_y;
			}
		}
		this.energy -= Params.run_energy_cost;
	}

	protected final void reproduce(Critter offspring, int direction) {
	    if (energy < Params.min_reproduce_energy) // parent requires min reproduce energy to reproduce
			return;
		offspring.energy = energy / 2; // child receives 1/2 of parent's energy (round down)
		energy = ((int) (0.5 * energy + 0.5)); // reduce parent energy by 1/2 (round up)

		// place child based on provided direction
		switch (direction) {
		case 0: {

			int child_loc_x = this.x_coord + 1;

			child_loc_x %= Params.world_width;

			offspring.x_coord = child_loc_x;
			offspring.y_coord = this.y_coord;

			break;

		}
		case 1: {

			int child_loc_x = this.x_coord + 1;
			int child_loc_y = this.y_coord - 1;

			child_loc_x %= Params.world_width;

			if (child_loc_y < 0)
				child_loc_y += Params.world_height;

			offspring.x_coord = child_loc_x;
			offspring.y_coord = child_loc_y;

			break;

		}
		case 2: {
			int child_loc_x = this.x_coord;
			int child_loc_y = this.y_coord - 1;

			if (child_loc_y < 0)
				child_loc_y += Params.world_height;

			offspring.x_coord = child_loc_x;
			offspring.y_coord = child_loc_y;

			break;

		}
		case 3: {
			int child_loc_x = this.x_coord - 1;
			int child_loc_y = this.y_coord - 1;

			if (child_loc_x < 0)
				child_loc_x += Params.world_width;

			if (child_loc_y < 0)
				child_loc_y += Params.world_height;

			offspring.x_coord = child_loc_x;
			offspring.y_coord = child_loc_y;

			break;

		}
		case 4: {
			int child_loc_x = this.x_coord - 1;

			if (child_loc_x < 0)
				child_loc_x += Params.world_width;

			offspring.x_coord = child_loc_x;
			offspring.y_coord = this.y_coord;

			break;

		}
		case 5: {

			int child_loc_x = this.x_coord - 1;
			int child_loc_y = this.y_coord + 1;

			if (child_loc_x < 0)
				child_loc_x += Params.world_width;

			child_loc_y %= Params.world_height;

			offspring.x_coord = child_loc_x;
			offspring.y_coord = child_loc_y;

			break;

		}
		case 6: {

			int child_loc_x = this.x_coord;
			int child_loc_y = this.y_coord + 1;

			child_loc_y %= Params.world_height;

			offspring.x_coord = child_loc_x;
			offspring.y_coord = child_loc_y;

			break;

		}
		case 7: {

			int child_loc_x = this.x_coord + 1;
			int child_loc_y = this.y_coord + 1;

			child_loc_x %= Params.world_width;
			child_loc_y %= Params.world_height;

			offspring.x_coord = child_loc_x;
			offspring.y_coord = child_loc_y;

			break;
		}

		}

	}

	public abstract void doTimeStep();

	public abstract boolean fight(String oponent);

	/**
	 * create and initialize a Critter subclass. critter_class_name must be the
	 * unqualified name of a concrete subclass of Critter, if not, an
	 * InvalidCritterException must be thrown. (Java weirdness: Exception
	 * throwing does not work properly if the parameter has lower-case instead
	 * of upper. For example, if craig is supplied instead of Craig, an error is
	 * thrown instead of an Exception.)
	 * 
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Class<?> cl = Class.forName(myPackage + "." + critter_class_name); //create a class object with value equal to the given critter class

			Critter c = (Critter) cl.newInstance();
			c.energy = Params.start_energy; 
			c.x_coord = getRandomInt(Params.world_width); //set the x and y coordinates to be random
			c.y_coord = getRandomInt(Params.world_height);
			population.add(c);
		} 
		
		catch (ClassNotFoundException c) { //catch any exceptions that may be thrown and propagate them as InvalidCritterException
			throw new InvalidCritterException(critter_class_name);
		} 
		catch (InstantiationException i) {
			throw new InvalidCritterException(critter_class_name);
		} 
		catch (IllegalAccessException il) {
			throw new InvalidCritterException(critter_class_name);
		}

	}

	/**
	 * Gets a list of critters of a specific type.
	 * 
	 * @param critter_class_name
	 *            What kind of Critter is to be listed. Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		
		try{
			Class<?> critter = Class.forName(myPackage + "." + critter_class_name); //create class object with given critter class
			
			int size = population.size();
			for(int i = 0; i < size; i++){
				if(critter.isInstance(population.get(i))) //check if the current critter is an instance of the specified critter 
						result.add(population.get(i));
			}
		}
		catch (ClassNotFoundException c) { //if the string input is not a valid critter type, then throw an exception
			throw new InvalidCritterException(critter_class_name);
		}

		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * 
	 * @param critters
	 *            List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string, 1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}

	/*
	 * the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this
	 * class and then use the setter functions contained here.
	 * 
	 * NOTE: you must make sure that the setter functions work with your
	 * implementation of Critter. That means, if you're recording the positions
	 * of your critters using some sort of external grid or some other data
	 * structure in addition to the x_coord and y_coord functions, then you MUST
	 * update these setter functions so that they correctly update your
	 * grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}

		/*
		 * This method getPopulation has to be modified by you if you are not
		 * using the population ArrayList that has been provided in the starter
		 * code. In any case, it has to be implemented for grading tests to
		 * work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using
		 * the babies ArrayList that has been provided in the starter code. In
		 * any case, it has to be implemented for grading tests to work. Babies
		 * should be added to the general population at either the beginning OR
		 * the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	private static void removeDead(){
		int size = population.size();
		for (int i = 0; i < size; i++){ //iterate through the Critter collection and remove all the dead critters
			if(population.get(i).energy < 1)
				population.remove(i);
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
	}
	
	private static void doEncounters(){
		// traverse through population, checking if two critters are at the same position		
		
		for (int i = 0; i < population.size() - 1; i++) { 
			if (population.get(i).energy <= 0)
				continue; //no need for dead critters
			for (int j = i + 1; j < population.size(); j++) {				
				if (population.get(j).energy <= 0)// no need for dead critters
					continue;
				
				Critter a = population.get(i);
				Critter b = population.get(j);

				if (a.x_coord == b.x_coord && a.y_coord == b.y_coord) { // critters have encountered	
					
					a.isFighting = true;
					b.isFighting = true;
					
					boolean a_fight = a.fight(b.toString());	//check if critter wants to fight				
					boolean b_fight = b.fight(a.toString());repr
					
					a.isFighting = false;
					b.isFighting = false;
					
					
					int a_power = 0;
					int b_power = 0;
					
					if ((a.x_coord == b.x_coord && a.y_coord == b.y_coord) && (a.energy > 0 && b.energy > 0)) {
						// fight!
						if (a_fight)
							a_power = getRandomInt(a.energy + 1); //roll a number btw 0 and A.energy (all inclusive)
						if (b_fight)
							b_power = getRandomInt(b.energy + 1); //roll a number btw 0 and B.energy (all inclusive)
						if (a_power > b_power){
							a.energy += (b.energy / 2); //Critter a wins fight, so it receives half of the loser's energy
							b.energy = 0;
						}
						else{
							b.energy += (a.energy / 2); //Critter b wins fight, so it receives half of the loser's energy
							a.energy = 0; //note: if two critters roll same number, b wins by default (arbitrary decision)
						}
					}

				}
			}

		}

	}
	
	private static void updateRestEnergy(){
		int size = population.size();
		for (int i = 0; i < size; i++){ //iterate through the Critter collection and decrease all Critters' energy by rest energy
			population.get(i).energy -= Params.rest_energy_cost;
		}
	}
	
	private static void generateAlgae(){
		for (int i = 0; i < Params.refresh_algae_count; i++){
			Critter a = new Algae(); //create a new algae with start_energy and place it in a random location
			a.energy = Params.start_energy;
			a.x_coord = getRandomInt(Params.world_width); //set the x and y coordinates to be random
			a.y_coord = getRandomInt(Params.world_height);
			population.add(a);
		}
	}
	
	private static void addBabies(){
		population.addAll(babies); 
		babies.clear();
	}
	
	private static void doAllTimeSteps(){
		int size = population.size();
		for (int i = 0; i < size; i++){ //iterate through the Critter collection and call each critter's doTimeStep
			population.get(i).doTimeStep();
		}
	}
	
	private static void updatePosition(){
		int size = population.size();
		position = new Critter[Params.world_height][Params.world_width];
		for (int i = 0; i < size; i++){ //iterate through the Critter collection and place the critters in the correct position in the position matrix
			int x = population.get(i).x_coord;
			int y = population.get(i).y_coord;
			position[x][y] = population.get(i);
		}
	}
	
	private static void resetHasMoved(){
		int size = population.size();
		for (int i = 0; i < size; i++){ //iterate through the Critter collection and call each critter's doTimeStep
			population.get(i).hasMoved = false;
		}
	}

	public static void worldTimeStep() {
		
		doAllTimeSteps();
		
		doEncounters();
		
		updateRestEnergy();
		
		generateAlgae();
		
		removeDead();
		
		updatePosition();
		
		addBabies(); //put after updatePosition so babies don't show up on the grid yet
		
		resetHasMoved();
	}

	public static void displayWorld() {
		 
        // printing top border
        System.out.print("+");
        for (int i = 0; i < Params.world_width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
 
        // printing each row of world grid
        for (int i = 0; i < Params.world_height; i++) {
            System.out.print("|");
            for (int j = 0; j < Params.world_width; j++) {
                if (position[i][j] != null)
                    System.out.print(position[i][j]);
                else
                    System.out.print(" ");
            }
 
            System.out.println("|");
 
        }
        // printing bottom border
        System.out.print("+");
        for (int i = 0; i < Params.world_width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
 
    }
}
