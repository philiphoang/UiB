import java.util.Scanner;

public class Exchange {

	public static void main(String[] args) {
		double USD = 0.128322;
		double EUR = 0.107566485;
		double GBP = 0.0990163174;
		double amount = 0;
		String type = "";
		Scanner t = new Scanner(System.in);

		do {
			while (true) {
				System.out.println("Choose currency type \nType: USD / EUR / GBP");
				type = t.nextLine().toUpperCase();
				if (!(type.equals("USD") || type.equals("EUR") || type.equals("GBP"))) {
					System.out.println("Not a valid option, choose again");
					continue;
				} else {
					System.out.println("Write an amount");
					try {
						amount = Double.parseDouble(t.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.out.println("Fail input with execption: " + e + "\nTry again");
					}
				}
			}

			if (type.equals("USD"))
				System.out.printf("%.2f NOK equals %.2f " + type, amount, amount * USD);
			else if (type.equals("GBP"))
				System.out.printf("%.2f NOK equals %.2f " + type, amount, amount * GBP);
			else if (type.equals("EUR"))
				System.out.printf("%.2f NOK equals %.2f " + type, amount, amount * EUR);

			System.out.println("\nWrite \"yes\" if you want exit, else press any button to continue");
			if (t.nextLine().equalsIgnoreCase("yes"))
				break;
			else
				continue;

		} while (true);
	}

}