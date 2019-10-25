package main;
/**
 * Description: Resistor object converts color code entered
 * in main class to numeric output through a series of calculations
 *
 * @author: Sam Hilliard
 * @version: 1.0
 */
public class Resistor {
	private String[] code;
	private int digits;
	private String prefix;
	private double multiplier;
	private String tolerance;
	private double value;
	private boolean error;
	public Resistor(String[] code) {
		this.code = code;
		digits = 0;
		multiplier = 0;
		tolerance = "";
		value = 0;
		prefix = "";
		error = false;
	}
	/**
	 * finds digit value associated with color bands
	 */
	public void calcDigits() {
		String color;
		String strDigits = "";	//stores digits in string for ease of concatenation
		int limit;
		//determines digit indexes based on number of resistor bands
		if (code.length == 4)
			limit = 2;
		else
			limit = 3;
		//loops through first two/three band colors
		for (int i = 0; i < limit; i++) {
			color = code[i];
			switch(color) {
				case "black":
					strDigits += "0";
					break;
				case "brown":
					strDigits += "1";
					break;
				case "red":
					strDigits += "2";
					break;
				case "orange":
					strDigits += "3";
					break;
				case "yellow":
					strDigits += "4";
					break;
				case "green":
					strDigits += "5";
					break;
				case "blue":
					strDigits += "6";
					break;
				case "purple":
				case "violet":
					strDigits += "7";
					break;
				case "grey":
					strDigits += "8";
					break;
				case "white":
					strDigits += "9";
					break;
				default:
					error = true;
			}
		}
		//prevents null pointer exception error
		if (!error)
			digits = Integer.valueOf(strDigits);
	}
	/**
	 * finds the multiplier associated with color
	 */
	public void calcMultiplier() {
		String color;
		//determines whether multiplier coded on third or fourth band
		if (code.length == 4)
			color = code[2];
		else
			color = code[3];
		//matches band to corresponding multiplier
		switch(color) {
			case "black":
				multiplier = 1;
				break;
			case "brown":
				 multiplier = 10;
				 break;
			case "red":
				multiplier = 100;
				break;
			case "orange":
				multiplier = 1;
				prefix = "K";
				break;
			case "yellow":
				multiplier = 10;
				prefix = "K";
				break;
			case "green":
				multiplier = 100;
				prefix = "K";
				break;
			case "blue":
				multiplier = 1;
				prefix = "M";
				break;
			case "purple":
			case "violet":
				multiplier = 10;
				prefix = "M";
				break;
			case "grey":
				multiplier = 100;
				prefix = "M";
				break;
			case "white":
				multiplier = 1;
				prefix = "G";
				break;
			case "gold":
				multiplier = 0.1;
				break;
			case "silver":
				multiplier = 0.01;
				break;
			default:
				error = true;
		}
		prefix += '\u03A9';
	}

	/**
	 * finds tolerance depending on associated color
	 */
	public void calcTolerance() {
		String color;
		tolerance = "\u00B1";

		//determines whether tolerance color band resides on fourth or fifth
		if (code.length == 4)
			color = code[3];
		else
			color = code[4];

		//matches color in colors to corresponding tolerance value
		switch (color) {
			case "brown":
				tolerance += "1%";
				break;
			case "red":
				tolerance += "2%";
				break;
			case "green":
				tolerance += "0.5%";
				break;
			case "blue":
				tolerance += "0.25%";
				break;
			case "violet":
			case "purple":
				tolerance += "0.10%";
				break;
			case "grey":
				tolerance += "0.05%";
				break;
			case "gold":
				tolerance += "5%";
				break;
			case "silver":
				tolerance += "10%";
				break;
			default:
				error = true;
		}
	}
	/**
	 * sets the actual value of the resistor after
	 * digits and multiplier are found
	 */
	public void calcVal() {
		value = multiplier * digits;
		value = Math.round(value * 100) / 100;
	}
	/**
	 * calculates final resistor value using all of the
	 * calc methods
	 */
	public void calcAll() {
		calcDigits();
		calcMultiplier();
		calcTolerance();
		calcVal();
	}
	/**
	 * returns the value of the resistor in a readable format
	 *
	 * @return String  resistor value
	 */
	@Override
	public String toString() {
		String output;
		//if color is invalid, error message and user able to reenter resistor
		if (error)
			output = "A color entered does not match the standard resistor code."
					+ "\nUnable to calculate resistance.";
		else
			output = value + prefix + " " + tolerance;
		return output;
	}
}
