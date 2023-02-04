import java.io.Serializable;

public class ToDo implements Serializable {

    private String name;

    public ToDo(String name) {
        this.name = name;
    }

    public void setName(String setName) {
        this.name = setName;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
