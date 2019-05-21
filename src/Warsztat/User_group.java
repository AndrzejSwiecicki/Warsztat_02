package Warsztat;

public class User_group {
    private int id;
    private String name;

    public User_group(){
    }

    public User_group(String name){
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User_group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


