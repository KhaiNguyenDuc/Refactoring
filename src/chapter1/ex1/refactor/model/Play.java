package chapter1.ex1.refactor.model;

public abstract class Play {

    String name;
    String type;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

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

    public abstract double calcAmount(int audience);
    public abstract int calcCredit(int audience);
}
