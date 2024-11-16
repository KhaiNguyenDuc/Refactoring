package chapter1.ex1.refactor.model;

public class Comedy extends Play{

    public Comedy(String name, String type){
        super(name, type);
    }

    @Override
    public double calcAmount(int audiences) {
        double amount = 30000;
        if (audiences > 20) {
            amount += 10000 + 500 * (audiences - 20);
        }
        return amount + 300 * audiences;
    }

    @Override
    public int calcCredit(int audience) {
        return (int) (Math.max(audience - 30, 0) + Math.floor(audience / 5.0));
    }
}
