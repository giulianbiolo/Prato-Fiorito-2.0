package sample.gameLogic;

import static sample.gameLogic.Constants.N_COLS;
import static sample.gameLogic.Constants.N_ROWS;

// [x] creazione board
// [x] creo prima le bombe
// [x] calcolo numeri
// [ ] gestione flag
// [ ] ccaso numero zero

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
                    if ((row > 0 && col < N_COLS - 1) && cells[row - 1][col + 1].isBomb()) value++;
                    if ((col > 0) && cells[row][col - 1].isBomb()) value++;
                    if ((col < N_COLS - 1) && cells[row][col + 1].isBomb()) value++;
                    if ((row < N_ROWS - 1 && col > 0) && cells[row + 1][col - 1].isBomb()) value++;
                    if ((row < N_ROWS - 1) && cells[row + 1][col].isBomb()) value++;
                    if ((row < N_ROWS - 1 && col < N_COLS - 1) && cells[row + 1][col + 1].isBomb()) value++;
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
        //uncoverNeighbours(row, col)
        if (cells[row][col].getValue().equals("0")) {
            cells[row][col].buttonPressed();
            Cell cell;
            //uncoverNeighbours(row, col);
            cell = cells[row - 1][col - 1];
            if ((row > 0 && col > 0) && !(cell.getValue().equals("0"))) {
                cells[row][col].buttonPressed();
            }else if (cell.isHidden()){
                //if (cell.isHidden()) uncoverNeighbours(cell.getRow(), cell.getCol());
                checkZeros(cell.getRow(), cell.getCol());
            }

            cell = cells[row - 1][col];
            if ((row > 0) && !(cell.getValue().equals("0") )) {
                cells[row][col].buttonPressed();
            }else if (cell.isHidden()){
                //if (cell.isHidden()) uncoverNeighbours(cell.getRow(), cell.getCol());
                checkZeros(cell.getRow(), cell.getCol());
            }

            cell = cells[row - 1][col + 1];
            if ((row > 0 && col < N_COLS - 1) && !(cell.getValue().equals("0"))) {
                cells[row][col].buttonPressed();
            }else if (cell.isHidden()){
                //if (cell.isHidden()) uncoverNeighbours(cell.getRow(), cell.getCol());
                checkZeros(cell.getRow(), cell.getCol());
            }

            cell = cells[row][col - 1];
            if ((col > 0) && !(cell.getValue().equals("0"))) {
                cells[row][col].buttonPressed();
            }else if (cell.isHidden()){
                //if (cell.isHidden()) uncoverNeighbours(cell.getRow(), cell.getCol());
                checkZeros(cell.getRow(), cell.getCol());
            }

            cell = cells[row][col + 1];
            if ((col < N_COLS - 1) && !(cell.getValue().equals("0"))) {
                cells[row][col].buttonPressed();
            }else if (cell.isHidden()){
                //if (cell.isHidden()) uncoverNeighbours(cell.getRow(), cell.getCol());
                checkZeros(cell.getRow(), cell.getCol());
            }

            cell = cells[row + 1][col - 1];
            if ((row < N_ROWS - 1 && col > 0) && !(cell.getValue().equals("0"))) {
                cells[row][col].buttonPressed();
            }else if (cell.isHidden()){
                //if (cell.isHidden()) uncoverNeighbours(cell.getRow(), cell.getCol());
                checkZeros(cell.getRow(), cell.getCol());
            }

            cell = cells[row + 1][col];
            if ((row < N_ROWS - 1) && !(cell.getValue().equals("0"))) {
                cells[row][col].buttonPressed();
            }else if (cell.isHidden()){
                //if (cell.isHidden()) uncoverNeighbours(cell.getRow(), cell.getCol());
                checkZeros(cell.getRow(), cell.getCol());
            }

            cell = cells[row + 1][col + 1];
            if ((row < N_ROWS - 1 && col < N_COLS - 1) && !(cell.getValue().equals("0"))) {
                cells[row][col].buttonPressed();
            }else if (cell.isHidden()){
                //if (cell.isHidden()) uncoverNeighbours(cell.getRow(), cell.getCol());
                checkZeros(cell.getRow(), cell.getCol());
            }
        }
    }

    /**
     *
     * @param r
     * @param c
     */
    private void uncoverNeighbours(int r, int c) {
        for (int i = 0; i <= 2; i++)
            for (int j = 0; j <= 2; j++)
                if ((r + i - 1 > 0 && r - i + 1 < N_COLS) && (c + j - 1 > 0 && c + j - 1 < N_ROWS)) {
                    cells[r + i - 1][c + j - 1].buttonPressed();
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
