package main;

/**
 * Description: This program reads a list of colors
 * from the bands of either a 4 band or 5 band resistor and
 * converts them to the numerical value in Ohms.
 *
 * @author Sam Hilliard
 * @version 1.0
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input;
		String[] colors = {};
		Resistor resistor;
		String answer = "";
		final String VALID_COLORS = "black, brown, red, orange, yellow, green, blue, purple, violet, white,grey, gold, silver";
		while (true) {
			//ensures start has space between title and output
			for (int i = 0; i < 50; i++) {
				System.out.println();
			}

			//program info/title
			System.out.println("Resistor Color Code Converter");
			System.out.print("\n\nEnter the colors of the resistor bands "
					+ "\nfrom left to right, seperated by a single space: ");

			//input checking
			while (true) {
				try {
					//converts input to array
					input = scan.nextLine();
					colors = input.split(" ");

					//converts all colors to lower case for consistency
					for (int i = 0; i < colors.length; i++) {
						colors[i] = colors[i].toLowerCase();
					}

					//checking for valid color numbers
					if (colors.length < 4 || colors.length > 5) {
						throw new ColorNumberException("More/Less color input than expected.");
					}
					//checking validity of colors entered
					for (int i = 0; i < colors.length; i++) {
						if (!VALID_COLORS.contains(colors[i])) {
							throw new InvalidColorsException("An element of colors is not a valid color.");
						}
					}

					break;
				}
				catch(InputMismatchException e) {
						System.out.println("Invalid input.");
						System.out.println("Please enter the names of the colors on the resistor");
						System.out.print("from left to right: ");
				}
				catch(InvalidColorsException e) {
					System.out.println("Unrecognized color(s) inputted.");
					System.out.print("Please reenter correct color names on the resistor: ");
				}
				catch (ColorNumberException e) {
					System.out.println("Number of colors entered invalid.");
					System.out.print("Please enter the 4 or 5 colored bands on the resistor: ");
				}
			}

			resistor = new Resistor(colors);
			System.out.println("Calculating Resistance...");
			resistor.calcAll();
			System.out.println(resistor.toString());

			//prompts user to continue or not
			System.out.println("Would you like to find the resistance of another resistor?");
			System.out.print("Enter 'y' for yes or 'n' for no: ");
			while (true) {
				try {
					answer = scan.next();
					//ensures input is 'y' or 'n'
					if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
						throw new Exception("Input not 'y' or 'n.'");
					}
					break;
				}
				catch(Exception e) {
					System.out.print("Please enter 'y'/'n': ");
				}
			}
			//ends game if user responds with 'n'
			if (answer.equalsIgnoreCase("n"))
				break;
		}
		scan.close();
	}

}

