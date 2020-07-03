package sample;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.models.Player;
import sample.models.Timer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller {

    private final String[] arrayData = {"1 min", "2 min", "3 min", "5 min", "10 min", "20 min"};
    private String selected;
    private Timer timer;

    @FXML
    private Label time1;

    @FXML
    private Label time2;

    @FXML
    private Pane leftPane;

    @FXML
    private Pane rightPane;

    public Controller() {
        openDialog();
    }

    private void setTimer() {
        int timeChoosen = Integer.parseInt(selected.split(" ")[0]) * 60;
        this.timer = new Timer(new Player(timeChoosen, "white", true), new Player(timeChoosen, "black", false), time1, time2);
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
        time1.setText(timer.getLeftPlayer().getFormattedTime());
        time2.setText(timer.getRightPlayer().getFormattedTime());

        updateStyles();

        time1.setFocusTraversable(true);
        time1.requestFocus();
        time1.setOnKeyPressed(event -> this.timer.switchPlayers());
        startTimer();
    }

    private void updateStyles() {
        leftPane.setStyle("-fx-background-color: " + timer.getLeftPlayer().getBackgroundColor());
        rightPane.setStyle("-fx-background-color: " + timer.getRightPlayer().getBackgroundColor());
        time1.setStyle("-fx-text-fill: " + (timer.getLeftPlayer().getBackgroundColor().equals("white") ? "black" : "white"));
        time2.setStyle("-fx-text-fill: " + (timer.getRightPlayer().getBackgroundColor().equals("white") ? "black" : "white"));
        time1.setStyle("-fx-font-size: 88");
        time2.setStyle("-fx-font-size: 88");
    }

    private void startTimer() {
        Thread th = new Thread(this.timer);
        th.start();
    }

    public void switchSides() {
        this.timer.switchSides();
        updateStyles();
    }

}
