/**
* A class that creates a person with an address.
* This class only ask for a name to the person and gives the person
* an address for the Address class.
*
* @author Philip Hoang
*/

public class Person {
  private String name;
  private Address address;

  /**
  * Constructor for Person
  *
  * @param  String  name          name for the person
  * @param  Address address       an address to the person
  * @return a person object
  */
  public Person(String name, Address address) {
    this.name = name;
    this.address = address;
  }

  /**
  * Print out the persons name and its following address
  *
  * @return a string that contains a name and an address
  */
  public String toString() {
    return name + "\n" + address.toString();
  }
}
