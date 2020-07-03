package main.models;

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
        String minutes = String.valueOf(this.timeLeft / 60);
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        String seconds = String.valueOf(this.timeLeft % 60);
        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        return String.format("%s:%s", minutes, seconds);
    }

}
