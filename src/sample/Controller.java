package sample;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import sample.models.Player;
import sample.models.Timer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {

    private final String[] arrayData = {"1 min", "2 min", "3 min", "5 min", "10 min", "20 min"};
    private String selected;
    private Timer timer;

    @FXML
    private Label time1;

    @FXML
    private Label time2;

    public Controller() {
        openDialog();
    }

    private void setTimer() {
        int timeChoosen = Integer.parseInt(selected.split(" ")[0]) * 60;
        this.timer = new Timer(new Player(timeChoosen), new Player(timeChoosen), time1, time2);
    }

    private void openDialog() {
        List<String> dialogData = Arrays.asList(arrayData);
        ChoiceDialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
        dialog.setTitle("Enter time of gameplay");
        dialog.setHeaderText("Select time for each player");
        Optional<String> result = dialog.showAndWait();
        selected = "cancelled.";
        result.ifPresent(s -> selected = s);
    }

    public void initialize() {
        setTimer();
        time1.setText(timer.getWhitePlayer().getFormattedTime());
        time2.setText(timer.getBlackPlayer().getFormattedTime());
        time1.setFocusTraversable(true);
        time1.requestFocus();
        time1.setOnKeyPressed(event -> this.timer.switchPlayers());
        startTimer();
    }

    private void startTimer() {
        Thread th = new Thread(this.timer);
        th.start();
    }
}
