package chapter1.ex1.origin.model;

public class Play {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
