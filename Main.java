/* CRITTERS Main.java
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

package assignment4; // cannot be in default package

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

	static Scanner kb; // scanner connected to keyboard input, or input file
	private static String inputFile; // input file, used instead of keyboard
										// input if specified
	static ByteArrayOutputStream testOutputString; // if test specified, holds
													// all console output
	private static String myPackage; // package of Critter file. Critter cannot
										// be in default pkg.
	private static boolean DEBUG = false; // Use it or not, as you wish!
	static PrintStream old = System.out; // if you want to restore output to
											// console

	// Gets the package name. The usage assumes that Critter and its subclasses
	// are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            args can be empty. If not empty, provide two parameters -- the
	 *            first is a file name, and the second is test (for test output,
	 *            where all output to be directed to a String), or nothing.
	 */
	public static void main(String[] args) {
		if (args.length != 0) {
			try {
				inputFile = args[0];
				kb = new Scanner(new File(inputFile));
			} catch (FileNotFoundException e) {
				System.out.println("USAGE: java Main OR java Main <input file> <test output>");
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
			}
			if (args.length >= 2) {
				if (args[1].equals("test")) { // if the word "test" is the
												// second argument to java
					// Create a stream to hold the output
					testOutputString = new ByteArrayOutputStream();
					PrintStream ps = new PrintStream(testOutputString);
					// Save the old System.out.
					old = System.out;
					// Tell Java to use the special stream; all console output
					// will be redirected here from now
					System.setOut(ps);
				}
			}
		} else { // if no arguments to main
			kb = new Scanner(System.in); // use keyboard and console
		}

		/* Do not alter the code above for your submission. */
		/* Write your code below. */
		
		boolean end = false; //the end flag for the program
		
		while (!end) {
			System.out.print("critters> ");
			String cmd = kb.nextLine();
			String[] parsed = (cmd.trim()).split("\\s+"); //trim the whitespace from the front and back of the user input, then split it into Strings separated by whitespace
			
			if(parsed.length == 0){ //if the user only entered a space, print an error
				System.out.println("invalid command: ");
			}
			
			else if(parsed[0].equals("quit")){ //if the user enters quit, then set the end flag
				if(parsed.length > 1){ // if the user entered extraneous text after the correct command, then print an error
					System.out.print("error processing:");
					int length = parsed.length; 
					for(int i = 0; i < length; i++)
						System.out.print(" " + parsed[i]);
					System.out.println();
				}
				else
					end = true;
			}
			
			else if(parsed[0].equals("show")){ //if the user enters show, then display the grid
				if(parsed.length > 1){ // if the user entered extraneous text after the correct command, then print an error
					System.out.print("error processing:");
					int length = parsed.length; 
					for(int i = 0; i < length; i++)
						System.out.print(" " + parsed[i]);
					System.out.println();
				}
				else
					Critter.displayWorld();				
			}
			
			else if(parsed[0].equals("step")){ //if the user enters step, then step once
				if(parsed.length == 1) //if the user enters only step, then step once
					Critter.worldTimeStep();
				else{
					try{
						int cnt = Integer.parseInt(parsed[1]); //Grab the integer from the second array index
						if(cnt < 1 || parsed.length > 2){ //if the user entered an invalid integer or extraneous text after the correct command, print an error
							System.out.print("error processing:");
							int length = parsed.length;
							for(int i = 0; i < length; i++)
								System.out.print(" " + parsed[i]);
							System.out.println();
						} //if the input is fine, then step the requested number of times
						else{
							for (int i = 0; i < cnt; i++)
								Critter.worldTimeStep();
						}
					}
					catch(NumberFormatException n){ //if the user entered an invalid step count, print an error
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int i = 0; i < length; i++)
							System.out.print(" " + parsed[i]);
						System.out.println();
					}
				}
			}
			
			else if(parsed[0].equals("seed")){ //if the user enters seed, seed the random number generator in Critter
				if(parsed.length != 2){ // if the user did not enter a seed number or entered extraneous text after the number, print an error
					System.out.print("error processing:");
					int length = parsed.length; 
					for(int i = 0; i < length; i++)
						System.out.print(" " + parsed[i]);
					System.out.println();
				}
				else{
					try{
						long seed = Long.parseLong(parsed[1]); //Grab the long from the second array index
						Critter.setSeed(seed);
					}
					catch (NumberFormatException n){ //if the user entered an invalid seed, print an error
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int i = 0; i < length; i++)
							System.out.print(" " + parsed[i]);
						System.out.println();
					}
				}
			}
			
			else if(parsed[0].equals("make")){ //if the user enters make, then make the given number of critters, or if no number is given, make one critter
				if(parsed.length != 2 && parsed.length != 3){ // if the user did not enter a critter type or a critter type followed by a number, print error
					System.out.print("error processing:");
					int length = parsed.length; 
					for(int i = 0; i < length; i++)
						System.out.print(" " + parsed[i]);
					System.out.println();
				}
				else{ //if the user entered 2 or 3 inputs, then check to see if they include a valid critter name and number (if 3 inputs)
					try{
						if(parsed.length == 2){ //if the user entered 2 inputs, the second must be a valid critter name
							Critter.makeCritter(parsed[1]);
						}
						else{
							int count = Integer.parseInt(parsed[2]); //grab the count of critters to make from the third input
							if (count < 1){ //if the number of critters to make is not at least 1, print an error
								System.out.print("error processing:");
								int length = parsed.length; 
								for(int i = 0; i < length; i++)
									System.out.print(" " + parsed[i]);
								System.out.println();
							}
							else{ //if everything is OK, make count critters
								String critter = parsed[1]; //the critter type to make
								for (int i = 0; i < count; i++)
									Critter.makeCritter(critter);
							}
						}
					}
					catch(InvalidCritterException e){ //if the user entered an invalid critter type, print an error
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int j = 0; j < length; j++)
							System.out.print(" " + parsed[j]);
						System.out.println();
					}
					catch(NumberFormatException n){ //if the user entered an invalid count, print an error
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int i = 0; i < length; i++)
							System.out.print(" " + parsed[i]);
						System.out.println();
					}
				}
			}
			
			else if(parsed[0].equals("stats")){ //return the statistics for the specified critter type on the board
				if(parsed.length != 2){ // if the user did not enter a critter type after the command, print an error
					System.out.print("error processing:");
					int length = parsed.length; 
					for(int i = 0; i < length; i++)
						System.out.print(" " + parsed[i]);
					System.out.println();
				}
				else{ //see if the second input is a valid critter and run its statistics
					try{
						String critter = parsed[1]; //the user input critter name
						Class<?> cl = Class.forName(myPackage + "." + critter); //create a class object with value equal to the given critter class
						List<Critter> instances = Critter.getInstances(critter); //get all the instances of the user specified critter
						Class<?>[] types = {List.class}; //get the type of List to use as a parameter reference for getMethod
						Method runStats = cl.getMethod("runStats", types); //create a method object for the runStats of this particular critter type
						runStats.invoke(null, new Object[] {instances}); //call the runStats method for the specified critter type 
					}
					catch(InvalidCritterException e){ //if the user entered an invalid critter
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int j = 0; j < length; j++)
							System.out.print(" " + parsed[j]);
						System.out.println();
					}
					catch(ClassNotFoundException c){ //if the user entered an invalid critter
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int j = 0; j < length; j++)
							System.out.print(" " + parsed[j]);
						System.out.println();
					}
					catch(NoSuchMethodException n){
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int j = 0; j < length; j++)
							System.out.print(" " + parsed[j]);
						System.out.println();
					} 
					catch (IllegalAccessException e) {
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int j = 0; j < length; j++)
							System.out.print(" " + parsed[j]);
						System.out.println();
					} 
					catch (IllegalArgumentException e) {
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int j = 0; j < length; j++)
							System.out.print(" " + parsed[j]);
						System.out.println();
					} 
					catch (InvocationTargetException e) {
						System.out.print("error processing:");
						int length = parsed.length; 
						for(int j = 0; j < length; j++)
							System.out.print(" " + parsed[j]);
						System.out.println();
					}
				}
			}
			
			else{ // if the user did not enter a valid command, print an error
				System.out.print("Invalid Command:");
				int length = parsed.length;
				for(int i = 0; i < length; i++)
					System.out.print(" " + parsed[i]);
				System.out.println();
			}
		}

		/* Write your code above */
		System.out.flush();

	}
}
