import java.util.Arrays;
import java.util.Random;

/**
 * This program is about shuffling a deck of cards that utilize the Knuth Shuffle.
 * We are only using 13 cards for this case.
 * 
 * @author Philip Hoang
 * 
 */
public class KnuthShuffle {
	
	public static void main(String[] args) {
		String[] deck = sortedDeck();
		System.out.println("Before shuffling: \n" + Arrays.toString(deck) + "\n");

		deck = shuffleDeck();
		System.out.println("After shuffling: \n" + Arrays.toString(deck));
	}
	
	/**
	 * This method is creating an array(deck) with 13 elements 
	 * and returns the deck after initializing it.
	 */
	public static String[] sortedDeck() {
		String[] deck = new String[13];
		
		for(int i = 0; i < deck.length; i++) 
			deck[i] = "Hearts" + (i + 1);
		
		return deck;
	}
	
	
	/**
	 * Swap two elements in a list by giving two numbers.
	 * Must have a temporary variable temp to save the first element 
	 * before overwriting it.
	 * @param deck the list of type String
	 * @param i first index 
	 * @param j second index 
	 */
	public static void swap(String[] deck, int i, int j) {
		String temp = deck[j];
		deck[j] = deck[i];
		deck[i] = temp; 
	}
	
	/**
	 * Return a random value between 0 and a given integer by using Random.
	 * @param max a given integer
	 * @return a random integer from 0 to max
	 */
	public static int randInt(int max) {
		Random rand = new Random();
		int random = rand.nextInt(max);
		return random;
	}
	
	/**
	 * This method is for Knuth-shuffle.  
	 * Using method randInt() and swap() to shuffle. 
	 * @param deck a list of String 
	 */
	public static void shuffle(String[] deck) {
		for(int i = 0; i < deck.length - 1; i++) {
			int n = randInt(deck.length);
			swap(deck, i, n);
		}
	}
	
	/**
	 * Shuffle a deck by using shuffle (Knuth-shuffle) and 
	 * return the deck after it has been shuffled. 
	 * @return a deck where the elements have been randomly placed in the list
	 */
	public static String[] shuffleDeck() {
		String[] deck = sortedDeck();
		shuffle(deck);
		return deck; 
	}
}


