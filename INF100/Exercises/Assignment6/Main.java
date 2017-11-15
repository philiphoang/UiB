public class Main {
  public static void main(String[] args) {
    Pokemon pokemon1 = new Pokemon("Pikachu");
    Pokemon pokemon2 = new Pokemon("Charmander");

    System.out.println(pokemon1);
    System.out.println(pokemon2);

    while (pokemon1.isConscious() || pokemon2.isConscious() ) {
          pokemon1.attack(pokemon2);
          if (!pokemon2.isConscious())
            break;

          pokemon2.attack(pokemon1);
          if (!pokemon1.isConscious())
            break;
    }
  }
}
