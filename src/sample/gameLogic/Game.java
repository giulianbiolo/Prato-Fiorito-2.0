package sample.gameLogic;

import static sample.gameLogic.Constants.N_COLS;
import static sample.gameLogic.Constants.N_ROWS;

// [x] creazione board
// [x] creo prima le bombe
// [x] calcolo numeri
// [x] gestione flag
// [ni] ccaso numero zero
// [ni] GAME OVER

public class Game {
    private Cell[][] cells;


    public Game() {
        this.cells = new Cell[N_ROWS][N_COLS];
    }


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
            } while (this.cells[row][col].isBomb());
            this.cells[row][col].setValue(9);
            placedBombs++;
        }
    }


    private void populateNumberedCells() {
        for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                if (!cells[row][col].isBomb()) {
                    int value = 0;
                    if ((row > 0 && col > 0) && cells[row - 1][col - 1].isBomb()) value++;
                    if ((row > 0) && cells[row - 1][col].isBomb()) value++;
                    if ((row > 0 && col < N_COLS-1 ) && cells[row - 1][col + 1].isBomb()) value++;
                    if ((col > 0) && cells[row][col - 1].isBomb()) value++;
                    if ((col < N_COLS-1 ) && cells[row][col + 1].isBomb()) value++;
                    if ((row < N_ROWS-1  && col > 0) && cells[row + 1][col - 1].isBomb()) value++;
                    if ((row < N_ROWS-1 ) && cells[row + 1][col].isBomb()) value++;
                    if ((row < N_ROWS-1  && col < N_COLS-1 ) && cells[row + 1][col + 1].isBomb()) value++;
                    cells[row][col].setValue(value);
                }
            }
        }
    }

    /**
     *
     * @param row
     * @param col
     */
    public void checkZeros(int row, int col) {
        // Controllo i vicini
        // Per ogni vicino, cerco checkZeros()

        if (cells[row][col].getValue().equals("0")) {
            uncoverNeighbours(row, col);
            cells[row][col].buttonPressed();
            Cell cell;

            if (row > 0 && col > 0) {
                cell = cells[row - 1][col - 1];
                if ((cell.getValue().equals("0") && cell.isHidden())) {
                    checkZeros(cell.getRow(), cell.getCol());
                }
            }

            if (row > 0 ) {
                cell = cells[row - 1][col];
                if ((cell.getValue().equals("0") && cell.isHidden())) {
                    checkZeros(cell.getRow(), cell.getCol());
                }
            }

            if (row > 0 && col < N_COLS ) {
                cell = cells[row - 1][col + 1];
                if ((cell.getValue().equals("0") && cell.isHidden())) {
                    checkZeros(cell.getRow(), cell.getCol());
                }
            }

            if (col > 0) {
                cell = cells[row][col - 1];
                if ((cell.getValue().equals("0") && cell.isHidden())) {
                    checkZeros(cell.getRow(), cell.getCol());
                }
            }

            if (col < N_COLS ) {
                cell = cells[row][col + 1];
                if ((cell.getValue().equals("0") && cell.isHidden())) {
                    checkZeros(cell.getRow(), cell.getCol());
                }
            }

            if (row < N_ROWS  && col > 0) {
                cell = cells[row + 1][col - 1];
                if ((cell.getValue().equals("0") && cell.isHidden())) {
                    checkZeros(cell.getRow(), cell.getCol());
                }
            }

            if(row < N_ROWS  ) {
                cell = cells[row + 1][col];
                if ((cell.getValue().equals("0") && cell.isHidden())) {
                    checkZeros(cell.getRow(), cell.getCol());
                }
            }

            if(row < N_ROWS  && col < N_COLS ) {
                cell = cells[row + 1][col + 1];
                if ((cell.getValue().equals("0") && cell.isHidden())) {
                    checkZeros(cell.getRow(), cell.getCol());
                }
            }
        }
    }

    private void InsertFlag(int row, int col){
        cells[row][col].setFlagged(!cells[row][col].isFlagged());

    }
    /**
     *
     * @param r
     * @param c
     */
    private void uncoverNeighbours1(int r, int c) {
        for (int i = 0; i <= 2; i++)
            for (int j = 0; j <= 2; j++)
                if ((r + i - 1 > 0 && r - i + 1 < N_COLS) && (c + j - 1 > 0 && c + j - 1 < N_ROWS)) {
                    if (cells[r + i - 1][c + j - 1].getValue() != "0") cells[r + i - 1][c + j - 1].buttonPressed();
                }
    }
    private void uncoverNeighbours(int r, int c) {
        int row = r;
        int col = c;

        if (row > 0 && col > 0) {
            if (cells[row - 1][col - 1].getValue() != "0" && !(cells[row-1][col-1].isFlagged())) cells[row-1][col-1].buttonPressed();
        }

        if (row > 0 ) {
            if (cells[row - 1][col].getValue() != "0" && !(cells[row-1][col-1].isFlagged())) cells[row-1][col].buttonPressed();
        }

        if (row > 0 && col < N_COLS ) {
            if (cells[row - 1][col + 1].getValue() != "0" && !(cells[row-1][col-1].isFlagged())) cells[row-1][col+1].buttonPressed();
        }

        if (col > 0) {
            if (cells[row][col - 1].getValue() != "0" && !(cells[row-1][col-1].isFlagged())) cells[row][col-1].buttonPressed();
        }

        if (col < N_COLS ) {
            if (cells[row][col + 1].getValue() != "0" && !(cells[row-1][col-1].isFlagged())) cells[row][col+1].buttonPressed();
        }

        if (row < N_ROWS  && col > 0) {
            if (cells[row + 1][col - 1].getValue() != "0" && !(cells[row-1][col-1].isFlagged())) cells[row+1][col-1].buttonPressed();
        }

        if(row < N_ROWS  ) {
            if (cells[row + 1][col].getValue() != "0" && !(cells[row-1][col-1].isFlagged())) cells[row+1][col].buttonPressed();
        }

        if(row < N_ROWS  && col < N_COLS ) {
            if (cells[row + 1][col + 1].getValue() != "0" && !(cells[row-1][col-1].isFlagged())) cells[row+1][col+1].buttonPressed();
        }
    }



    /**
     *
     * @return
     */
    public Cell[][] getBoard() {
        return this.cells;
    }

    /**
     *
     * @return
     */
    public Cell[][] generateBoard() {
        initCells();
        placeBombs();
        populateNumberedCells();
        return getBoard();
    }
}
