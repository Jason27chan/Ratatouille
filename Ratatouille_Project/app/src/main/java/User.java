/**
 * Created by Catherine on 10/2/2017.
 */

public class User {

    private String username;
    private String password;

    /**
     * Constructor for the user
     *
     * @param username the username of the user
     * @param password the password for the user
     */
    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public User() {
        this("", "123456");
    }
}
