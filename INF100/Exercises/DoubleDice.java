import java.util.Scanner;

/*
 * DoubleDice is a program that ask for the users input as a target as well
 * a number of matches.
 * The program will run and output different numbers until the sum of the dice is 
 * equal users target and also equal the matches the user gave. 
 *
 */
public class DoubleDice {
	private static int target;
	private static int amount;

	/*
	 * Metho for askiing the user to write in a number between 2-12 and an 
	 * amount which can not be lower than 0. 
	 * Any other input will give the user a message to try again.
	 */
	public static void userInput() {
		Scanner t = new Scanner(System.in);
		while (true) {
			try {
				target = Integer.parseInt(t.nextLine());
				if (target >= 2 && target <= 12) {
					break;
				} else 
					System.out.println("Not between 2-12");
			} catch (NumberFormatException e) {
				System.out.println("Input not a number, try again");
			}
		}
		
		while (true) {
			try {
				System.out.println("How many times");
				amount = Integer.parseInt(t.nextLine());
				if (amount < 0)
					System.out.println("Can not be lower than 1, so try again");
				else 
					break;
			} catch (NumberFormatException e) {
				System.out.println("Fail input, try again");
			}
		}
		System.out.println("Target: " + target);
		System.out.println("Amount: " + amount);
		rollingDice(target, amount);

	}

	/*
	 * Method for rolling the dice. Will loop until the method's variable match
	 * is equal the user's amount. 
	 * The program will also count how many times it rolled the dice.
	 */
	public static void rollingDice(int input, int amount) {
		int die1;
		int die2;
		int sum;
		int match = 0;
		int count = 0;
		
		while (match != amount) {	
			die1 = (int) (1 + 6 * Math.random());
			die2 = (int) (1 + 6 * Math.random());	
			sum = die1 + die2;	
			
			System.out.println("rolled: (" + die1 + "," + die2 +") " + "SUM=" + sum );
			if (input == sum) {
				match++;
				if (match == amount) {
					System.out.println("Got the target outcome " + match + 
							" with target " + input + " times in " + count + " rolls");
					break;
				}
				continue;
			} else {					
				count++;				
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Write a number between 2 and 12");
		userInput();
	}
}
