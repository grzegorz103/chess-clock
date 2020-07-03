package sample.models;

public class Player {

    private int timeLeft;

    public Player(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getFormattedTime() {
        return (this.timeLeft / 60) + ":" + (this.timeLeft % 60);
    }

}
