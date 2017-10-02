/**
 * Jack the pirate is marooned on the tropical island of Mojang 
 * surrounded by sharks. He is also drunk, and his steps are unpredictable.
 * Pirate Survival Simulator is a program that simulates Jacks steps
 * before he will fall into the shark-infested water. 
 * 
 * @author Philip Hoang
 *
 */
public class Mojang {
	public static void main(String[] args) {
		final int ITERATIONS = 100000;
		int sum = 0;
		double average = 0;
		
		/*
		 * Simulate and sum the amount of steps in one 
		 * run up the numbers of ITERATION
		 */
		for (int i = 0; i < ITERATIONS; i++) {
			sum += simulate(5, 5);
		}
		average = (double) sum / ITERATIONS;
		System.out.printf("Jack was eaten by shark after %.3f "
				+ "(averaged over 100000)", average);
	}

	/*
	 * The method simulate() simulates Jacks process
	 * Will return the amount of steps when Jack dies
	 */
	public static int simulate(int x, int y) {
		int direction = 0;
		int steps = 0;

		// Grid (10,10)
		while (x <= 10 && x > 0 && y <= 10 && y > 0) {
			direction = (int) (Math.random() * 4); //Random numbers [0,3]
			if (direction == 0) //West
				x -= 1;
			else if (direction == 1) //East
				x += 1;
			else if (direction == 2) //North
				y += 1;
			else //South
				y -= 1;
			steps++;
		}
		return steps;
	}
}