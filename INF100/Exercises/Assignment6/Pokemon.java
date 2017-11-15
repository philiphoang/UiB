import java.util.Random;
import java.text.DecimalFormat;

/**
* Class for creating a Pokemon, including methods for attacking and check if
* the Pokemon is conscious
*/
public class Pokemon {
  private String name;
  private int healthPoints;
  private int maxHealthPoints;
  private int strength;
  private double criticalChance;
  private Random random;

  /** Constructor for creating a new pokemon with a name from the user */
  public Pokemon(String name) {
    this.random = new Random();
    this.name = name;
    this.healthPoints = (int) Math.abs(Math.round(100 + 10 * random.nextGaussian()));
    this.maxHealthPoints = this.healthPoints;
    this.strength = (int) Math.abs(Math.round(20 + 10 * random.nextGaussian()));
    this.criticalChance = Math.abs(0.1 * random.nextGaussian());
  }

  /**
  * Print out the name and stats of the Pokemon
  * @return string containing name and stats
  */
  public String toString() {
    DecimalFormat df = new DecimalFormat("#");

    String s = name + " ";
    s += String.format("HP: (%d/%d)", healthPoints, maxHealthPoints);
    s += " STR: " + strength;
    s += " CHC: " + df.format(criticalChance*100) + "%";
    return s;
  }

  /**
  * Return the name of the Pokemon
  */
  public String getName() {
    return name;
  }

  /**
  * Check if the Pokemon's HP and see if it is under 0
  * @return a boolean if the Pokemon is conscious or not
  */
  public boolean isConscious() {
    if (healthPoints > 0)
      return true;
    return false;
  }

  /**
  * Redusing the Pokemon's HP. If damage is larger than the HP, set it to 0
  * @param int damageTaken    reduce the HP with amount of damage taken
  */
  public void damage(int damageTaken) {
    if (damageTaken > healthPoints)
      healthPoints = 0;
    else
      healthPoints = healthPoints - damageTaken;

    System.out.printf("%s takes %d damage and is left with %d/%d HP\n", name, damageTaken, healthPoints, maxHealthPoints);
  }

  /**
  * Method for attacking a Pokemon
  * The Pokemon also have a critical chance for damaging twice
  * It can also do 0 damage if damageInflicted is under 0
  * @param Pokemon target    the Pokemon it is going to attack
  */
  public void attack(Pokemon target) {
    double rand = Math.random() * 1;
    int damageInflicted = (int) (this.strength + this.strength / 2 * random.nextGaussian());

    if (damageInflicted < 0)
      damageInflicted = 0;

    System.out.printf("\n%s attacks %s.%n", this.getName(), target.getName());
    target.damage(damageInflicted);

    if (rand < this.criticalChance && target.isConscious()) {
      System.out.println("Critical hit!");
      target.damage(damageInflicted);
    }

    if (!target.isConscious())
      System.out.println(target.getName() + " is defeated by " + this.getName());
  }
}
