package valueOrReference;

public class User {
    private String name,strength;

    public User(String name, String strength) {
        this.name = name;
        this.strength = strength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "name: "+name+" strength: "+strength;
    }
}