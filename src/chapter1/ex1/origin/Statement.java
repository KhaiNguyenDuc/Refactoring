package chapter1.ex1.origin;

import chapter1.ex1.origin.model.Invoice;
import chapter1.ex1.origin.model.Performance;
import chapter1.ex1.origin.model.Play;

import java.text.NumberFormat;
import java.util.*;

public class Statement {

    public static String statement(Invoice invoice, Map<String, Play> plays) {
        double totalAmount = 0;
        int volumeCredits = 0;
        String result = "Statement for " + invoice.getCustomer() + "\n";

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance perf : invoice.getPerformances()) {
            Play play = plays.get(perf.getPlayID());
            double thisAmount = 0;

            // Determine amount for each play
            switch (play.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                default:
                    throw new IllegalArgumentException("unknown type: " + play.getType());
            }

            // Add volume credits
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(perf.getAudience() / 5.0);
            }

            // Print line for this order
            result += "  " + play.getName() + ": " + format.format(thisAmount / 100) + " (" + perf.getAudience() + " seats)\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is " + format.format(totalAmount / 100) + "\n";
        result += "You earned " + volumeCredits + " credits\n";
        return result;
    }

    public static void run(){
        Map<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Play("Hamlet", "tragedy"));
        plays.put("as-like", new Play("As You Like It", "comedy"));
        plays.put("othello", new Play("Othello", "tragedy"));

        // Create invoice list from JSON
        List<Invoice> invoices = new ArrayList<>();
        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 55));
        performances.add(new Performance("as-like", 35));
        performances.add(new Performance("othello", 40));

        invoices.add(new Invoice("BigCo", performances));

        for(Invoice invoice : invoices) {
            System.out.println(Statement.statement(invoice, plays));
        }
    }
}
