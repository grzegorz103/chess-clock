package sample;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {

    private final String[] arrayData = {"1 min", "2 min", "3 min", "5 min", "10 min", "20 min"};
    private String selected;
    private int minutes1, minutes2;
    private int seconds1, seconds2;
    private AtomicBoolean currentPlayer = new AtomicBoolean(false);
    private int timeLeft;

    @FXML
    Label time1;

    @FXML
    Label time2;

    public Controller() {
        List<String> dialogData = Arrays.asList(arrayData);
        ChoiceDialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
        dialog.setTitle("Enter time of gameplay");
        dialog.setHeaderText("Select your choice");
        Optional<String> result = dialog.showAndWait();
        selected = "cancelled.";
        result.ifPresent(s -> selected = s);
    }

    public void initialize() {

        String[] splitSelected = selected.split(" ");

        this.minutes1 = (Integer.valueOf(splitSelected[0]) * 60) / 60;
        this.seconds1 = (Integer.valueOf(splitSelected[0]) * 60) % 60;
        this.minutes2 = (Integer.valueOf(splitSelected[0]) * 60) / 60;
        this.seconds2 = (Integer.valueOf(splitSelected[0]) * 60) % 60;
        this.time1.setText(String.valueOf(this.minutes1) + ":" + String.valueOf(this.seconds1));
        this.time2.setText(String.valueOf(this.minutes2) + ":" + String.valueOf(this.seconds2));
        time1.setFocusTraversable(true);
        time1.requestFocus();
        time1.setOnKeyPressed(event -> this.currentPlayer.set(!currentPlayer.get()));
        startGame();
    }

    private void startGame() {

        Thread th = new Thread(() -> {
            boolean stop = false;
            while (!stop) {

                if (!currentPlayer.get()) {
                    timeLeft = (minutes1 * 60) + seconds1;
                    timeLeft--;
                    this.minutes1 = timeLeft / 60;
                    this.seconds1 = timeLeft % 60;
                    Platform.runLater(() -> this.time1.setText(String.valueOf(this.minutes1) + ":" + String.valueOf(this.seconds1)));

                } else {
                    timeLeft = (minutes2 * 60) + seconds2;
                    timeLeft--;
                    this.minutes2 = timeLeft / 60;
                    this.seconds2 = timeLeft % 60;
                    Platform.runLater(() -> this.time2.setText(String.valueOf(this.minutes2) + ":" + String.valueOf(this.seconds2)));
                }

                if ((minutes1 == 0 && seconds1 == 0) || (minutes2 == 0 && seconds2 == 0))
                    stop = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        th.start();
    }
}
