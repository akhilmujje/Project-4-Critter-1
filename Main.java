/* CRITTERS Main.java
 * EE422C Project 4 submission by 
 * Devisriram Akhil Mujje
 * 16470
 * Sriram Ravula
 * sr39533
 * 16475
 * Slip days used: 0
 * Fall 2016
 */

package assignment4; // cannot be in default package

import java.util.Scanner;
import java.io.*;

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
		
		for(int i = 0; i < 100; i++){ //For stage 1, create 100 algae to start
			try{
				Critter.makeCritter("Algae");
			}
			catch (InvalidCritterException e){ //dummy catch
				System.out.println("Try Again");
			}
		}
		
		for(int i = 0; i < 25; i++){ //For stage 1, create 25 Craigs to start
			try{
				Critter.makeCritter("Craig");
			}
			catch (InvalidCritterException e){ //dummy catch
				System.out.println("Try Again");
			}
		}
		
		boolean end = false; //the end flag for the program
		
		while (!end) {
			System.out.println("critters> ");
			String cmd = kb.nextLine();
			String[] parsed = (cmd.trim()).split("\\s+"); //trim the whitespace from the front and back of the user input, then split it into Strings separated by whitespace
			
			if(parsed[0].equals("quit")){ //if the user enters quit, then set the end flag
				end = true;
			}
			
			else if(parsed[0].equals("show")){ //if the user enters show, then display the grid
				Critter.displayWorld();				
			}
			
			else if(parsed[0].equals("step")){ //if the user enters step, then step once
				int cnt = 1; //TODO take user input for stage 2
				for (int i = 0; i < cnt; i++)
					Critter.worldTimeStep();
			}		
		}

		/* Write your code above */
		System.out.flush();

	}
}
