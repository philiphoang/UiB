/**
* This class creates an address that is tied to a person.
*
* @author Philip Hoang
*/

public class Address {
  private String street;
  private int streetNumber;
  private int postalCode;
  private String town;
  private String country;

  /**
  * Constructor for Address
  * @param  String s             a street
  * @param  int    sn            a streetnumber
  * @param  int    pc            a postal code
  * @param  String t             a town
  * @param  String c             a country
  * @return a new address
  */
  public Address(String s, int sn, int pc, String t, String c) {
    this.street = s;
    this.streetNumber = sn;
    this.postalCode = pc;
    this.town = t;
    this.country = c;
  }

  /**
  * Prints out the registered address
  * @return the string that contains an address
  */
  public String toString() {
    return street + ", " + Integer.toString(streetNumber) + "\n"
        + Integer.toString(postalCode) + ", " + town + "\n"
        + country;
}
