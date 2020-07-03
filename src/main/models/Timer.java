package main.models;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.Main;

public class Timer implements Runnable {

    private Player leftPlayer;

    private Player rightPlayer;

    @FXML
    Label time1;

    @FXML
    Label time2;

    public Timer(Player leftPlayer, Player rightPlayer, Label time1, Label time2) {
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
        this.time1 = time1;
        this.time2 = time2;
    }

    public Player getLeftPlayer() {
        return leftPlayer;
    }

    public void setLeftPlayer(Player leftPlayer) {
        this.leftPlayer = leftPlayer;
    }

    public Player getRightPlayer() {
        return rightPlayer;
    }

    public void setRightPlayer(Player rightPlayer) {
        this.rightPlayer = rightPlayer;
    }

    @Override
    public void run() {
        boolean stop = false;
        while (!stop) {

            synchronized (this) {
                if (this.leftPlayer.isCurrentPlayer()) {
                    this.leftPlayer.setTimeLeft(this.leftPlayer.getTimeLeft() - 1);
                } else {
                    this.rightPlayer.setTimeLeft(this.rightPlayer.getTimeLeft() - 1);
                }

                Platform.runLater(() -> {
                    time1.setText(leftPlayer.getFormattedTime());
                    time2.setText(rightPlayer.getFormattedTime());
                });
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.leftPlayer.getTimeLeft() == 0 | this.rightPlayer.getTimeLeft() == 0) {
                stop = true;
            }
        }

        Platform.runLater(Main::reset);
    }

    public void switchPlayers() {
        if (leftPlayer.isCurrentPlayer()) {
            leftPlayer.setCurrentPlayer(false);
            rightPlayer.setCurrentPlayer(true);
        } else {
            rightPlayer.setCurrentPlayer(false);
            leftPlayer.setCurrentPlayer(true);
        }
    }

    public void switchSides() {
        Player temp = leftPlayer;
        leftPlayer = rightPlayer;
        rightPlayer = temp;
    }
}
