import java.util.ArrayList;


/**
* Class for connecting a user to another user, like Facebook.
* Each user has a friendlist that contains other users.
* Also implements Comparable to compare friendlist and sort them after size.
*/
public class User implements Comparable<User> {
    private String name;
    private ArrayList<User> friends; //friends of this user
    private static ArrayList<User> users = new ArrayList<>();

    /**
     * Constructor for User
     * @param  String name          Name of the user
     * @return a new User
     */
    public User(String name) {
        this.name = name;
        this.friends = new ArrayList<User>();
        users.add(this);
    }

    /**
     * @return the name of the User
     */
    public String getName() {
        return name;
    }

    /**
     * Pretty printer for printing the users friendlist
     * @return a list of the user's name including its' friendlist
     */
    public String toString() {
        String s = name + ": {";

        for (User u : friends)
            s += u.getName() + ", ";

        s += "}";
        return s;
    }

    /**
     * Adding a friend to the users friendlist
     * @param User friend   another user
     */
    public void addFriend(User friend) {
        friends.add(friend);
    }

    /**
     * Connect two users together by calling method addFriend() for each of them
     * @param User user1    A user
     * @param User user2    Another user
     */
    public static void connect(User user1, User user2) {
        user1.addFriend(user2);
        user2.addFriend(user1);
    }

    /**
     * Return the list that contains all the users
     * @return a list of users
     */
    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * [getSize description]
     * @return [description]
     */
    public int getSize() {
        return friends.size();
    }

    /**
     * Compares a friendlist with another friendlist to be able to sort
     * @param  User otherUser     other users friendlist
     * @return      an integer based on the size of this users friendlist
     *              compared to the other users friendlist 
     */
    public int compareTo(User otherUser) {
        if (this.getSize() < otherUser.getSize())
            return -1;
        else if (this.getSize() == otherUser.getSize())
            return 0;
        else
            return 1;
    }
}
