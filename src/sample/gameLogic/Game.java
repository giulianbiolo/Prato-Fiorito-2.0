package sample.gameLogic;

import static sample.gameLogic.Constants.N_ROWS;
import static sample.gameLogic.Constants.N_COLS;

// [x] creazione board
// [x] creo prima le bombe
// [x] calcolo numeri
// [ ] gestione flag
// [ ] ccaso numero zero

public class Game {
    private Cell[][] cells;

    public Game() { this.cells = new Cell[N_ROWS][N_COLS]; }

    private void initCells() {
        for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                this.cells[row][col] = new Cell(0, row, col);
            }
        }
    }

    private void placeBombs() {
        int placedBombs = 0;
        int row, col;
        while (placedBombs <= 99) {
            do {
                col = (int) (Math.random() * (N_COLS - 1));
                row = (int) (Math.random() * (N_ROWS - 1));
            } while(this.cells[row][col].isBomb());
            this.cells[row][col].setValue(9);
            placedBombs++;
        }
    }

    private void populateNumberedCells() {
        for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                if (!this.cells[row][col].isBomb()) {
                    int value = 0;
                    if((row > 0 && col > 0) && this.cells[row-1][col-1].isBomb()){ value++; };
                    if((row > 0) && this.cells[row-1][col].isBomb()){ value++; };
                    if((row > 0 && col < N_COLS - 1) && this.cells[row-1][col+1].isBomb()){ value++; };
                    if((col > 0) && this.cells[row][col-1].isBomb()){ value++; };
                    // if(this.cells[row][col].isBomb()){ value++; };
                    if((col < N_COLS - 1) && this.cells[row][col+1].isBomb()){ value++; };
                    if((row < N_ROWS - 1 && col > 0) && this.cells[row+1][col-1].isBomb()){ value++; };
                    if((row < N_ROWS - 1) && this.cells[row+1][col].isBomb()){ value++; };
                    if((row < N_ROWS - 1 && col < N_COLS - 1) && this.cells[row+1][col+1].isBomb()){ value++; };
                    this.cells[row][col].setValue(value);
                }
            }
        }
    }

    public Cell[][] getBoard() { return this.cells; }
    public Cell[][] generateBoard() {
        initCells();
        placeBombs();
        populateNumberedCells();
        return getBoard();
    }
}
