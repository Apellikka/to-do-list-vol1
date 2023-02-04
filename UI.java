import java.util.Locale;
import java.util.Scanner;

public class UI {

    private final Scanner reader;
    private final Storage storage;
    private Login login;
    private User user;

    public UI(Storage s) {
        this.reader = new Scanner(System.in);
        this.storage = s;
        this.login = new Login(s);
    }

    public void start() {
        System.out.println("Register new username by writing register, or login by writing login.");
        String registerOrLogin = reader.nextLine().toLowerCase(Locale.ROOT);

        if (registerOrLogin.equals("register")) {
            System.out.println("Enter username:");
            String userName = reader.nextLine();
            System.out.println("Enter first name:");
            String firstName = reader.nextLine();
            System.out.println("Enter last name:");
            String lastName = reader.nextLine();
            System.out.println("Enter password:");
            String password = reader.nextLine();
            storage.createUser(userName, firstName, lastName, password);

            login.logIn();
            this.user = login.getUser();
            this.user.getUsersToDoList().printToDoList();
        }

        else if (registerOrLogin.equals("login")) {
            login.logIn();
            this.user = login.getUser();
            this.user.getUsersToDoList().printToDoList();
        }

        while (true) {
            System.out.println("");
            System.out.println("1: Add a to-do item.");
            System.out.println("2. Remove a to-do item.");
            System.out.println("3. Print a list of my to-do items.");
            System.out.println("4. Quit and save");
            System.out.print("Type the number of desired action: ");

            String input = reader.nextLine();

            if (input.equals("4")) {
                saveUsersList();
                System.out.println("Quitting!");
                break;
            }

            else if (input.equals("1")) {
                System.out.println("What would you like to add?");
                String add = reader.nextLine();
                ToDo item = new ToDo(add);
                this.user.getUsersToDoList().addToDo(item);
            }

            else if (input.equals("2")) {
                if (this.user.getUsersToDoList().getList().isEmpty()) {
                    System.out.println("List is empty.");
                    continue;
                }
                System.out.println("");
                this.user.getUsersToDoList().printToDoList();
                System.out.print("Type the index of the item you wish to remove: ");
                try {
                    int remove = Integer.parseInt(reader.nextLine());
                    this.user.getUsersToDoList().removeToDo(remove);
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a number.");
                }
            }

            else if (input.equals("3")) {
                System.out.println("");
                this.user.getUsersToDoList().printToDoList();
            }
        }
    }

    public void saveUsersList() {
        storage.getToDoLists().put(login.getUsername(), this.user.getUsersToDoList());
        storage.saveUsersToDoLists();
    }
}

