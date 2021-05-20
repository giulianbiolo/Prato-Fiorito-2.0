package sample.gameLogic;

import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;

import static sample.gameLogic.Constants.SCREEN_WIDTH;
import static sample.gameLogic.Constants.SCREEN_HEIGTH;
import static sample.gameLogic.Constants.N_ROWS;
import static sample.gameLogic.Constants.N_COLS;

public class Board {
    private Game game;
    private GridPane grid;

    public Board() {
        game = new Game();
        this.grid = styleCells(game.generateBoard());
        this.grid.setAlignment(Pos.CENTER);
        this.grid.setBorder(Border.EMPTY);
        this.grid.setMinSize(SCREEN_WIDTH, SCREEN_HEIGTH);
        this.grid.setMaxSize(SCREEN_WIDTH, SCREEN_HEIGTH);
    }

    public GridPane styleCells(Cell[][] c) {
        GridPane g = new GridPane();
        for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                // Stilizzo cella
                c[row][col].styleButton();
                // Gestisco la pressione ed aggiungo alla griglia
                c[row][col].actionManager(game);
                g.add(c[row][col].getButton(), col, row);
            }
        }
        return g;
    }
    public GridPane getGrid() { return this.grid; }
}