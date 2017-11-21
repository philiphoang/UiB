import java.util.ArrayList;

public class User implements Comparable<User> {
  private String name;
  private ArrayList<User> friends; //friends of this user
  private static ArrayList<User> users = new ArrayList<>();

  public User(String name) {
    this.name = name;
    this.friends = new ArrayList<User>();
    users.add(this);
  }

  public String getName() {
    return name;
  }

  // public ArrayList<User> getFriends() {
  //   return friends;
  // }

  public String toString() {
    String s = name + ": {";

    for (User u : friends)
      s += u.getName() + ", ";
    s += "}";
    return s;
  }

  public void addFriend(User friend) {
    friends.add(friend);

  }

  public static void connect(User user1, User user2) {
      user1.addFriend(user2);
      user2.addFriend(user1);
  }

  public static ArrayList<User> getUsers() {
    return users;
  }

  public int getSize() {
    return friends.size();
  }

  public int compareTo(User otherUser) {
    if (this.getSize() < otherUser.getSize()) {
      return 1;
    }
    else if (this.getSize() == otherUser.getSize())
      return 0;
    else
      return -1;
  }
}
