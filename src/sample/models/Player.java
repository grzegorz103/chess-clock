package sample.models;

public class Player {

    private int timeLeft;
    private boolean currentPlayer;
    private String backgroundColor;

    public Player(int timeLeft, String backgroundColor, boolean currentPlayer) {
        this.timeLeft = timeLeft;
        this.backgroundColor = backgroundColor;
        this.currentPlayer = currentPlayer;
    }

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
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
