package chapter1.ex1.refactor;

import chapter1.ex1.refactor.model.*;

import java.text.NumberFormat;
import java.util.*;

public class StatementRefactor {

    private static String statement(Invoice invoice, Map<String, Play> plays) {
        return "Statement for " +
                invoice.getCustomer() +
                "\n" +
                getInvoiceAmount(invoice, plays) +
                "Amount owed is " +
                usd(getTotalAmount(invoice, plays)) +
                "\n" +
                "You earned " +
                getVolumeCredits(invoice, plays) +
                " credits\n";
    }

    private static double getTotalAmount(Invoice invoice, Map<String, Play> plays) {
        double totalAmount = 0;
        for(Performance perf : invoice.getPerformances()){
            Play play = plays.get(perf.getPlayID());
            totalAmount += amountFor(perf, play);

        }
        return totalAmount;
    }

    private static int getVolumeCredits(Invoice invoice, Map<String, Play> plays) {
        int volumeCredits = 0;
        for(Performance perf : invoice.getPerformances()){
            Play play = plays.get(perf.getPlayID());
            volumeCredits += play.calcCredit(perf.getAudience());
        }
        return volumeCredits;
    }

    public static double amountFor(Performance performance, Play play) {
        return play.calcAmount(performance.getAudience());
    }

    private static String getInvoiceAmount(Invoice invoice, Map<String, Play> plays) {
        StringBuilder result = new StringBuilder();
        for(Performance perf : invoice.getPerformances()){
            Play play = plays.get(perf.getPlayID());
            result.append("  ")
                    .append(play.getName())
                    .append(": ")
                    .append(usd(amountFor(perf, play)))
                    .append(" (")
                    .append(perf.getAudience())
                    .append(" seats)\n");
        }
        return result.toString();
    }

    public static String usd(double currency){
        return NumberFormat.getCurrencyInstance(Locale.US).format(currency  / 100) ;
    }


    public static void run(){
        Map<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Tragedy("Hamlet", "tragedy"));
        plays.put("as-like", new Comedy("As You Like It", "comedy"));
        plays.put("othello", new Tragedy("Othello", "tragedy"));

        // Create invoice list from JSON
        List<Invoice> invoices = new ArrayList<>();
        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 55));
        performances.add(new Performance("as-like", 35));
        performances.add(new Performance("othello", 40));

        invoices.add(new Invoice("BigCo", performances));

        for(Invoice invoice : invoices) {
            System.out.println(StatementRefactor.statement(invoice, plays));
        }
    }
}
