package sample.models;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Timer implements Runnable {

    private Player whitePlayer;

    private Player blackPlayer;

    private Player currentPlayer;

    @FXML
    Label time1;

    @FXML
    Label time2;

    public Timer(Player whitePlayer, Player blackPlayer, Label time1, Label time2) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer;
        this.time1 = time1;
        this.time2 = time2;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    @Override
    public void run() {
        boolean stop = false;
        while (!stop) {
            currentPlayer.setTimeLeft(currentPlayer.getTimeLeft() - 1);

            Platform.runLater(() -> {
                time1.setText(whitePlayer.getFormattedTime());
                time2.setText(blackPlayer.getFormattedTime());
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void switchPlayers() {
        currentPlayer = currentPlayer == this.whitePlayer
                ? this.blackPlayer
                : this.whitePlayer;
    }

}
