import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String password;
    private ToDoList toDoList;

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.toDoList = new ToDoList();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ToDoList getUsersToDoList() {
        return this.toDoList;
    }

    public void setList(ToDoList list) {
        this.toDoList = list;
    }

    public boolean passwordEquals(String password) {
        return this.password.equals(password);
    }
}
