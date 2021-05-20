package sample.gameLogic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static sample.gameLogic.Constants.BTN_HEIGTH;
import static sample.gameLogic.Constants.BTN_WIDTH;

public class Cell {
    private final int row, col;
    // values from 0 to 8 indicates the number of mines
    // the number 9 indicate a mine
    private int value;
    private boolean hidden;
    private boolean flagged;
    private Button button;
    private InnerShadow pressed_shadow_effect;

    /**
     *
     * @param value
     * @param row
     * @param col
     */
    public Cell(int value, int row, int col) {
        this.value = value;
        this.button = new Button("");
        this.hidden = true;
        this.flagged = false;
        this.row = row;
        this.col = col;
        this.pressed_shadow_effect = new InnerShadow();
        this.pressed_shadow_effect.setOffsetX(4);
        this.pressed_shadow_effect.setOffsetY(4);
        this.pressed_shadow_effect.setColor(Color.web("0x3b596d"));
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
        button.setText(getValue());
        if (isBomb()) { button.setStyle("-fx-text-fill: red;"); }  //TODO: usato il colore bianco solo per debug
        button.setEffect(pressed_shadow_effect);
        hidden = false;
    }

    /**
     *
     * @param game
     */
    public void actionManager(Game game) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonPressed();
                game.checkZeros(row, col);
            }
        });
    }

    public InnerShadow getPressed_shadow_effect() {
        return pressed_shadow_effect;
    }

    /**
     *
     * @return
     */
    public boolean isBomb() {
        return this.value == 9;
    }

    /**
     *
     * @return
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     *
     * @return
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     *
     * @return
     */
    public Button getButton() {
        return this.button;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        if (this.isBomb()) {
            return "X";
        } else if (this.isFlagged()) {
            return "F";
        } else if (value == 0) {
            return "0";
        } else {
            return String.valueOf(value);
        }
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public int getRow() {
        return this.row;
    }

    /**
     *
     * @return
     */
    public int getCol() {
        return this.col;
    }
}