package sample.gameLogic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static sample.gameLogic.Constants.BTN_HEIGTH;
import static sample.gameLogic.Constants.BTN_WIDTH;
import static sample.gameLogic.Constants.FONT_DIR;

public class Cell {
    private final int row, col;
    private int value;
    private boolean hidden;
    private boolean flagged;
    private Button button;

    /**
     * A cell in the game grid
     *
     * @param value Values from 0 to 8 indicate the number of mines whilst the number 9 indicates a mine
     * @param row   Position in y axis
     * @param col   Position in x axis
     */
    public Cell(int value, int row, int col) {
        this.value = value;
        this.button = new Button("");
        this.hidden = true;
        this.flagged = false;
        this.row = row;
        this.col = col;
    }

    public void styleButton() {
        this.button.setShape(new Rectangle(BTN_WIDTH, BTN_HEIGTH));
        this.button.setMinSize(BTN_WIDTH, BTN_HEIGTH);
        this.button.setMaxSize(BTN_WIDTH, BTN_HEIGTH);
        if ((this.row + this.col) % 2 == 0) {
            this.button.setStyle("-fx-background-color: #aad751;");
        } else {
            this.button.setStyle("-fx-background-color: #b9dd77;");
        }
    }

    void buttonPressed() {
        if(button.getStyle().contains("-fx-background-color: #E5c29F;")) { button.setStyle("-fx-background-color: #E5c29F;"); }
        if(button.getStyle().contains("-fx-background-color: #D7B899;")) { button.setStyle("-fx-background-color: #D7B899;"); }
        if(button.getStyle().contains("-fx-background-color: #aad751;")) { button.setStyle("-fx-background-color: #E5c29F;"); }
        else if(button.getStyle().contains("-fx-background-color: #b9dd77;")){ button.setStyle("-fx-background-color: #D7B899;"); }
        if (getValue() == "0") {
            button.setText("");
        } else {
            setTextColor();
            button.setText(getValue());
        }
        if (isBomb()) {
            button.setStyle("-fx-text-fill: red;");
        }  //TODO: usato il colore bianco solo per debug
        hidden = false;
    }

    public void actionManager(Game game) {
        button.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (!isFlagged() && getValue().equals("⬤") && !game.getGameOver() && !game.getVictory()) {
                    game.gameOver();
                }
                if(!game.getGameOver() && !game.getVictory()){
                    buttonPressed();
                    game.checkZeros(row, col);
                    game.Victory();
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if(!game.getGameOver() && !game.getVictory()) setFlagged(!(isFlagged()));
            }
        });
    }

    private void setTextColor() {
        String btnColor = "black";
        switch (this.value) {
            case 1: btnColor = "blue"; break;
            case 2: btnColor = "green"; break;
            case 3: btnColor = "red"; break;
            case 4: btnColor = "purple"; break;
        }
        this.button.setStyle(this.button.getStyle() + "-fx-text-fill: " + btnColor + ";");
    }

    public boolean isBomb() {
        return this.value == 9;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isFlagged() {
        return flagged;
    }

    // TODO: Sistemare possibilmente l'emoji del flag
    public void setFlagged(boolean flagged) {
        if(isHidden()) {
            this.flagged = flagged;
            if (flagged) {
                button.setText("🚩");
            } else {
                button.setText("");
            }
        }
    }

    public Button getButton() {
        return this.button;
    }

    public String getValue() {
        if (this.isBomb()) {
            return "⬤";
        } else if (this.isFlagged()) {
            return "F";
        } else if (value == 0) {
            return "0";
        } else {
            return String.valueOf(value);
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void hackCell() {
        if (getValue() == "0") {
            button.setText("");
        } else {
            setTextColor();
            button.setText(getValue());
        }
    }
}
