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
                col = (int) (Math.random() * N_COLS);
                row = (int) (Math.random() * N_ROWS);
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
                    for(int i = 0; i < 2; i++)
                        for(int j = 0; j < 2; j++)
                            if((row+i-1 > 0 && row-i+1 < N_COLS) && (col+j-1 > 0 && col+j-1 < N_ROWS) && this.cells[row+i-1][col+j-1].isBomb()) { value++; }
                    this.cells[row][col].setValue(value);
                }
            }
        }
    }

    public void checkZeros(int row, int col) {
        // Controllo i vicini
        // Per ogni vicino, cerco checkZeros()
        uncoverNeighbours(row, col);
        if((row > 0 && col > 0) && this.cells[row-1][col-1].getValue() == "0"){
            uncoverNeighbours(row-1,col-1);
            checkZeros(row-1, col-1);
        };
        if((row > 0) && this.cells[row-1][col].getValue() == "0"){
            uncoverNeighbours(row-1,col);
            checkZeros(row-1, col);
        };
        if((row > 0 && col < N_COLS - 1) && this.cells[row-1][col+1].getValue() == "0"){
            uncoverNeighbours(row-1,col+1);
            checkZeros(row-1,col+1);
        };
        if((col > 0) && this.cells[row][col-1].getValue() == "0"){
            uncoverNeighbours(row,col-1);
            checkZeros(row, col-1);
        };
        // if(this.cells[row][col].isBomb()){ value++; };
        if((col < N_COLS - 1) && this.cells[row][col+1].getValue() == "0"){
            uncoverNeighbours(row, col+1);
            checkZeros(row,col+1);
        };
        if((row < N_ROWS - 1 && col > 0) && this.cells[row+1][col-1].getValue() == "0"){
            uncoverNeighbours(row+1,col-1);
            checkZeros(row+1,col-1);
        };
        if((row < N_ROWS - 1) && this.cells[row+1][col].getValue() == "0"){
            uncoverNeighbours(row+1, col);
            checkZeros(row+1, col);
        };
        if((row < N_ROWS - 1 && col < N_COLS - 1) && this.cells[row+1][col+1].getValue() == "0"){
            uncoverNeighbours(row-1, col-1);
            checkZeros(row+1,col+1);
        };

    }

    private void uncoverNeighbours(int r, int c) {
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                if((r+i-1 > 0 && r-i+1 < N_COLS) && (c+j-1 > 0 && c+j-1 < N_ROWS)) { this.cells[r+i-1][c+j-1].getButton().fire(); }
    }

    public Cell[][] getBoard() { return this.cells; }
    public Cell[][] generateBoard() {
        initCells();
        placeBombs();
        populateNumberedCells();
        return getBoard();
    }
}
