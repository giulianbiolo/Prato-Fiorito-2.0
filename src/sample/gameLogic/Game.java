package sample.gameLogic;

import static sample.gameLogic.Constants.N_COLS;
import static sample.gameLogic.Constants.N_ROWS;

public class Game {
    private Cell[][] cells;
    private boolean gameover = false;
    private boolean victory = false;
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
                    for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++)
                            if (row + i >= 0 && row + i < N_COLS && col + j >= 0 && col + j < N_ROWS && cells[row + i][col + j].isBomb())
                                value++;
                    cells[row][col].setValue(value);
                }
            }
        }
    }

    /**
     * Se la cella è uno zero allora ne scopro i vicini e per tutti i vicini controllo se ci sono zeri nascosti e richiamo checkZeros recursivamente su di essi
     *
     * @param row
     * @param col
     */
    public void checkZeros(int row, int col) {
        if (cells[row][col].getValue().equals("0")) {
            uncoverNeighbours(row, col);
            cells[row][col].buttonPressed();
            for (int i = -1; i <= 1; i++)
                for (int j = -1; j <= 1; j++)
                    if (row + i >= 0 && row + i < N_COLS && col + j >= 0 && col + j < N_ROWS && cells[row + i][col + j].getValue().equals("0") && cells[row + i][col + j].isHidden())
                        checkZeros(row + i, col + j);
        }
    }

    /**
     * Ciclo per tutti i vicini controllando di non uscire dai bordi [row+i>0 && row+i<N_COLS && col+j>0 && col+j<N_ROWS]
     * E controllando che il vicino non sia uno zero e che non sia flaggato, in tal caso richiamo buttonPressed() su quella cella
     *
     * @param row
     * @param col
     */
    private void uncoverNeighbours(int row, int col) {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (row + i >= 0 && row + i < N_COLS && col + j >= 0 && col + j < N_ROWS && cells[row + i][col + j].getValue() != "0" && !cells[row + i][col + j].isFlagged())
                    cells[row + i][col + j].buttonPressed();
    }

    public void gameOver() {
        System.out.println("GAME OVER");
        for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                if (cells[row][col].getValue().equals("⬤")) cells[row][col].buttonPressed();

            }
        }
        gameover = true;
    }

    public boolean getGameOver(){
        return gameover;
    }

    public void Victory() {
        boolean hidden = true;
        for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                if (!cells[row][col].getValue().equals("⬤") && cells[row][col].isHidden()) hidden = false;
            }
        }
        if (hidden){
            System.out.println("WINNER");
            victory = true;
        }
    }

    public boolean getVictory(){
        return victory;
    }

    public void Hack(){
        for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                cells[row][col].hackCell();
            }
        }
    }
    public Cell[][] getBoard() { return this.cells; }
    public Cell[][] generateBoard() {
        initCells();
        placeBombs();
        populateNumberedCells();
        //Hack(); TODO: trucco magico magico per vincere in modo easy
        return getBoard();
    }
}
