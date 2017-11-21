import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    User ken = new User("ken");
    User phil = new User("phil");
    User torg = new User("torg");
    User jules = new User("jules");

    User.connect(phil, ken);
    User.connect(phil, torg);
    User.connect(ken, torg);
    User.connect(jules, phil);

    Collections.sort(User.getUsers());

    System.out.println(User.getUsers());
  }
}
