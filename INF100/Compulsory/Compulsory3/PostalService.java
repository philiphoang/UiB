import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

/**
* PostalService is a program that lets the user register parcels,
* including sender and reciever with address to each of them.
* This class utilize multiple classes to create a parcel with persons
* and addresses.
*
* @author Philip Hoang
*/
public class PostalService {
  /**
  * The interface the user interact with.
  * The user can do following:
  * - Register parcels, including sender and recipient name and address
  * - Print the registered parcels to the display
  * - Write the registered parcels to a text file
  * - Clear the registered parcels
  */
  public static void main(String[] args) {
    ArrayList<Parcel> registeredParcels = new ArrayList<Parcel>();
    Scanner sc = new Scanner(System.in);
    boolean done = false;

    while (!done) {
      int size = registeredParcels.size();

      System.out.printf("Enter command (%d parcel(s) registered)"
      + "%nr: register parcel"
      + "%np: print parcels to display"
      + "%nw: write parcels to file"
      + "%nc: clear parcel queue"
      + "%nq: quit%n> ", size);
      String command = sc.next();
      if (command.equals("r")) {
        registeredParcels.add(registerParcel());

      } else if (command.equals("p")) {
        parcelsToTerminal(registeredParcels);

      } else if (command.equals("w")) {
        sc.nextLine();
        System.out.println("Enter filename: (.txt not necessary)");
        String filename = sc.nextLine();
        parcelsToFile(registeredParcels, filename);

      } else if (command.equals("c")) {
        sc.nextLine();
        System.out.println("Are you sure want to delete all: " + size + " parcels(s)?");
        System.out.println("Write \"YES\" if you want to delete");
        command = sc.nextLine();
        if (command.equalsIgnoreCase("yes")) {
          registeredParcels.clear();
          System.out.println("[Cleared registered parcels]");
        }

      } else if (command.equals("q")) {
        System.out.println("[Quitting]");
        done = true;

      } else {
        System.out.println("[Unknown command]");
      }
    }
  }

  /**
  * Method that register a parcel between two persons,
  * a register and a recipient. Also calls on another method that
  * registers the persons.
  *
  * @return a parcel that gets stored in a list
  */
  public static Parcel registerParcel() {
    System.out.println("Register sender\n--------------------");
    Person sender = registerPerson();

    System.out.println("Recipient sender\n--------------------");
    Person recipient = registerPerson();

    Parcel parcel = new Parcel(sender, recipient);
    System.out.print("[Parcel registered]\n");

    return parcel;
  }

  /**
  * Creates a new person and a new address.
  *
  * @return a person with registered address
  */
  public static Person registerPerson() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter person name: (Only letters) ");
    String name = stringInput();

    System.out.println("Enter street name: (Only letters)");
    String street = stringInput();

    System.out.println("Enter street number: (Only numbers)");
    int streetNumber = intInput();

    System.out.println("Enter postal code: (Only numbers)");
    int postalCode = intInput();

    System.out.println("Enter town: (Only letters)");
    String town = stringInput();

    System.out.println("Enter country: (Only letters)");
    String country = stringInput();

    Address address = new Address(street, streetNumber, postalCode, town, country);
    Person person = new Person(name, address);

    return person;
  }

  /**
  * Check if the input contains only integers
  *
  * @return [description]
  */
  public static int intInput() {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    while(!s.matches("[0-9]+")) {
      System.out.println("Invalid number");
      s = sc.nextLine();
    }
    return Integer.parseInt(s);
  }

  /**
  * Check if the input contains only letters
  *
  * @return a string
  */
  public static String stringInput() {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    while (!s.matches("[a-zA-Z .]+")) {
      System.out.println("Only letters are allowed");
      s = sc.nextLine();
    }
    return s;
  }

  /**
  * Prints out the registered parcel(s) in the stored arrayList
  *
  * @param ArrayList<Parcel> registeredParcels a list that contains the parcels
  */
  public static void parcelsToTerminal(ArrayList<Parcel> registeredParcels) {
    for (Parcel parcel : registeredParcels)
    System.out.println(parcel + "\n");
  }

  /**
  * Takes a list of registered parcels and writes it to a textfile
  * @param ArrayList<String> assignment an ArrayList
  * @param String            filename   name of the textfile
  */
  public static void parcelsToFile(ArrayList<Parcel> regParcels, String filename) {
    try {
      FileWriter writer = new FileWriter(filename + ".txt");

      for(Parcel p : regParcels) {
        writer.write(p + "\n");
      }
      writer.close();
    } catch(IOException e) {
      System.out.println("Something went wrong with filewriting");
    }
  }

}
