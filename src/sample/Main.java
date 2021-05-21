package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.gameLogic.Board;

import static sample.gameLogic.Constants.SCREEN_WIDTH;
import static sample.gameLogic.Constants.SCREEN_HEIGTH;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Board board = new Board();
        primaryStage.setTitle("PRATO FIORITO \uD83D\uDE0E");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(board.getGrid(), SCREEN_WIDTH, SCREEN_HEIGTH));
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
