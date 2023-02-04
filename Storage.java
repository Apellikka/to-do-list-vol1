import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Storage {

    private Map<String, ToDoList> toDoLists;
    private Map<String, User> allUsers;
    private final File usernamesAndPasswords;
    private final File usersToDoLists;
    private User user;

    Storage() {
        this.usernamesAndPasswords = new File("UsernamesAndPasswords.ser");
        this.usersToDoLists = new File("ToDoLists.ser");
        loadUserNamesAndPasswords(usernamesAndPasswords);
        loadUsersToDoLists(usersToDoLists);
    }

    public void saveUsersToDoLists() {
        try (
            FileOutputStream fos = new FileOutputStream(usersToDoLists);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(this.toDoLists);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Exception happened. saveUsersList");
        }
    }

    public void loadUsersToDoLists(File file) {
        if (file.length() == 0) {
            toDoLists = new HashMap<>();
            return;
        }
        try (
            FileInputStream fis = new FileInputStream(usersToDoLists);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            toDoLists = (HashMap<String, ToDoList>) ois.readObject();

        } catch (Exception e) {
            System.out.println("Exception happened. loadUsersList");
        }
    }

    public void saveUserNamesAndPasswords(Map<String, User> allUsers) {
        try (
            FileOutputStream fos = new FileOutputStream(usernamesAndPasswords);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(allUsers);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Exception happened. saveUsernames");
        }
    }

    public void loadUserNamesAndPasswords(File file) {
        //If the file is empty then this method creates a new empty hashmap and saves it
        //in the file
        if (file.length() == 0) {
            allUsers = new HashMap<>();
            return;
        }
        try (
            FileInputStream fis = new FileInputStream(usernamesAndPasswords);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            allUsers = (HashMap<String, User>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Exception happened. loadUserNames");
        }
    }

    public Map<String, ToDoList> getToDoLists() {
        return this.toDoLists;
    }

    public boolean validateUsername(String username, String password) {
        try {
            if (this.allUsers.get(username).passwordEquals(password)) {
                this.user = allUsers.get(username);
                this.user.setList(this.toDoLists.get(username));
                return true;
            }
        } catch (NullPointerException npe) {
            System.out.println("Incorrect username or password! Try again!");
        }
        return false;
    }

    public User returnUser() {
        return this.user;
    }

    public void createUser(String userName, String firstName, String lastName, String password) {
        this.user = new User(firstName, lastName, password);
        this.allUsers.putIfAbsent(userName, user);
        this.saveUserNamesAndPasswords(this.allUsers);
        this.toDoLists.putIfAbsent(userName, this.user.getUsersToDoList());
        this.saveUsersToDoLists();
    }
}
