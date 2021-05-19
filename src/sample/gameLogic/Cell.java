package sample.gameLogic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import static sample.gameLogic.Constants.BTN_HEIGTH;
import static sample.gameLogic.Constants.BTN_WIDTH;

public class Cell {
    // values from 0 to 8 indicates the number of mines
    // the number 9 indicate a mine
    private int value;
    private int row, col;
    private boolean hidden;
    private boolean flagged;
    private Button button;

    public Cell(int value, int row, int col) {
        this.value = value;
        this.button = new Button(String.valueOf(this.value));
        this.hidden = true;
        this.flagged = false;
        this.row = row;
        this.col = col;
    }

    public void styleButton() {
        this.button.setShape(new Rectangle(BTN_WIDTH, BTN_HEIGTH));
        this.button.setMinSize(BTN_WIDTH, BTN_HEIGTH);
        this.button.setMaxSize(BTN_WIDTH, BTN_HEIGTH);
        if((this.row + this.col) % 2 == 0) { this.button.setStyle("-fx-background-color: #aad751;"); }
        else { this.button.setStyle("-fx-background-color: #b9dd77;"); }
    }

    public void actionManager(Game game) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                button.setText(getValue());
                // game.btnPressed();
            }
        });
    }

    public boolean isBomb(){ return value == 9; }
    public boolean isHidden() { return hidden == true; }
    public boolean isFlagged() { return flagged == true; }
    public Button getButton() { return button; }
    public String getValue() {
        if (this.isBomb()) { return "X"; }
        else if (this.isFlagged()) { return "F"; }
        else { return String.valueOf(this.value); }
    }
    public void setValue(int value) { this.value = value; }
}