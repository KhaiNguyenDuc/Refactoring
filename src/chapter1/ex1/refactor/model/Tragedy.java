package chapter1.ex1.refactor.model;

public class Tragedy extends Play{

    public Tragedy(String name, String type){
        super(name, type);
    }

    @Override
    public double calcAmount(int audiences) {
        double amount = 40000;
        if (audiences > 30) {
            amount += 1000 * (audiences - 30);
        }
        return amount;
    }

    @Override
    public int calcCredit(int audience) {
        return Math.max(audience - 30, 0);
    }
}
