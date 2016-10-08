/* CRITTERS <MyClass.java>
 * EE422C Project 4 submission by October 18, 2016
 * <Devisriram Akhil Mujje>
 * <16470>
 * <Sriram Ravula>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

package assignment4;

public class InvalidCritterException extends Exception {
	String offending_class;
	
	public InvalidCritterException(String critter_class_name) {
		offending_class = critter_class_name;
	}
	
	public String toString() {
		return "Invalid Critter Class: " + offending_class;
	}

}
