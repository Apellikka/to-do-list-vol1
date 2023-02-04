import java.util.Scanner;

public class Login {

    private User user;
    private final Storage storage;
    private Scanner reader;
    private String username;

    public Login(Storage s) {
        this.storage = s;
        this.reader = new Scanner(System.in);
        this.user = storage.returnUser();
    }

    public void logIn() {
        while (true) {
            System.out.println("Username:");
            this.username = reader.nextLine();
            System.out.println("Password:");
            String password = reader.nextLine();
            if (validateUser(this.username, password)) {
                System.out.println("Welcome " + user.getFirstName() + "!");
                break;
            }
        }
    }

    public User getUser() {
        return this.user;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean validateUser(String username, String password) {
        if (storage.validateUsername(username, password)) {
            this.user = storage.returnUser();
            return true;
        }
        return false;
    }
}
