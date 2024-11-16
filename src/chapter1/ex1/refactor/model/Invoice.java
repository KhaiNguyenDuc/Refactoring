package chapter1.ex1.refactor.model;

import java.util.List;

public class Invoice {

    private String customer;
    private List<Performance> performances;

    public String getCustomer() {
        return customer;
    }
    public List<Performance> getPerformances() {
        return performances;
    }

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }
}
