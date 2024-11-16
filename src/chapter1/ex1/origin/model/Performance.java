package chapter1.ex1.origin.model;

public class Performance {

    private String playID;
    private int audience;

    public String getPlayID() {
        return playID;
    }

    public void setPlayID(String playID) {
        this.playID = playID;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }
}
