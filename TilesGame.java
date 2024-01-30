/**
 *Pallav Regmi
 * Project1 - TileGame
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TilesGame extends Application {

    private final GameBoard GAME_Game_BOARD = new GameBoard();
    private StackPane firstClick;
    private StackPane secondClick;
    private int currCombo = 0;
    private int longestCombo = 0;

    /**
     * This function handles both input/output and the game logic also resides here.
     * The event handler holds much of that logic.
     *
     * @param primaryStage This is the stage where out game will be displayed or played.
     */
    @Override
    public void start(Stage primaryStage) {

        GridPane gameBoard = GAME_Game_BOARD.addTiles();
        primaryStage.setTitle("The Tiles");
        primaryStage.setScene(new Scene(gameBoard, Color.BLACK));

        Text currentValue = new Text(Integer.toString(currCombo));
        gameBoard.add(currentValue, GAME_Game_BOARD.getNUM_COLS() + 1, 0);
        displayCurrentCombo(gameBoard, currentValue, 0);

        Text longestValue = new Text(Integer.toString(longestCombo));
        gameBoard.add(longestValue, GAME_Game_BOARD.getNUM_COLS() + 1, 1);
        displayLongestCombo(gameBoard, longestValue, 0);

        Text statusMessage = new Text();
        statusMessage.setFill(Color.CYAN);
        statusMessage.setFont(Font.font("sans-serif", FontWeight.BOLD, 20));
        gameBoard.add(statusMessage, GAME_Game_BOARD.getNUM_COLS(), 2);

        EventHandler<MouseEvent> mouseEventHandler = event -> {
            statusMessage.setText("");
            Node clickedNode = event.getPickResult().getIntersectedNode();
            if (clickedNode != gameBoard) {
                Node parent = clickedNode.getParent();
                while (parent != gameBoard) {
                    clickedNode = parent;
                    parent = clickedNode.getParent();
                }
            }
            assert clickedNode instanceof StackPane;
            StackPane modStack = (StackPane) clickedNode;

            if (modStack.getChildren().size() < 4 && !completeTransparent(modStack)) {
                if (firstClick == null) {
                    firstClick = modStack;
                    modStack.getChildren().add(1, GAME_Game_BOARD.genBorder());
                } else {
                    secondClick = modStack;
                    firstClick.getChildren().remove(1);
                    if (!compare()) {
                        statusMessage.setText("No Match Found!!");
                        statusMessage.setFill(Color.CRIMSON.brighter());
                        // This resets current combo with negative increment value.
                        displayCurrentCombo(gameBoard, currentValue, -currCombo);
                        firstClick = null;
                        secondClick = null;
                    } else {
                        // This increments current combo.
                        displayCurrentCombo(gameBoard, currentValue, 1);
                        if (currCombo > longestCombo) {
                            displayLongestCombo(gameBoard, longestValue, 1);
                        }
                        if (completeTransparent(secondClick)) {
                            statusMessage.setText("Go Anywhere!");
                            statusMessage.setFill(Color.CYAN.brighter());
                            firstClick = null;
                            secondClick.getChildren().add(1,
                                    GAME_Game_BOARD.genBorder());
                            secondClick = null;
                            modStack.getChildren().remove(1);
                        } else {
                            secondClick.getChildren().add(1,
                                    GAME_Game_BOARD.genBorder());
                            firstClick = secondClick;
                        }
                        if (gameEnd(gameBoard)) {
                            statusMessage.setText("Game Over");
                            statusMessage.setFill(Color.CYAN.brighter());
                        }
                    }
                }
            }
        };
        gameBoard.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventHandler);
        primaryStage.show();
    }


    private void displayCurrentCombo(GridPane gameBoard, Text displayText, int increasedBy) {
        // It creates a text box once.
        if (increasedBy == 0) {
            Text currentTextCombo = new Text("Current Combo:");
            currentTextCombo.setFill(Color.WHITE);
            currentTextCombo.setFont(Font.font("sans-serif",
                    FontWeight.BOLD, 20));
            gameBoard.add(currentTextCombo, GAME_Game_BOARD.getNUM_COLS(), 0);
        }

        displayText.setText(Integer.toString(currCombo + increasedBy));
        displayText.setFill(Color.WHITE);
        displayText.setFont(Font.font("sans-serif", FontWeight.BOLD, 20));
        currCombo += increasedBy;
    }


    private void displayLongestCombo(GridPane gameBoard, Text displayText, int increasedBy) {
        if (increasedBy == 0) {
            Text longestTextCombo = new Text("Longest Combo:");
            longestTextCombo.setFill(Color.WHITE);
            longestTextCombo.setFont(Font.font("sans-serif",
                    FontWeight.BOLD, 20));
            gameBoard.add(longestTextCombo, GAME_Game_BOARD.getNUM_COLS(), 1);
        }
        displayText.setText(Integer.toString(longestCombo + increasedBy));
        displayText.setFill(Color.WHITE);
        displayText.setFont(Font.font("sans-serif", FontWeight.BOLD, 20));
        longestCombo += increasedBy;
    }

    private Boolean gameEnd(GridPane gameBoard) {
        for (Node node : gameBoard.getChildren()) {
            if ((GridPane.getRowIndex(node) < GAME_Game_BOARD.getNUM_ROWS() &&
                    GridPane.getColumnIndex(node) < GAME_Game_BOARD.getNUM_COLS())) {
                StackPane stackPane = (StackPane) node;
                if (!completeTransparent(stackPane)) {
                    return false;
                }
            }
        }
        return true;
    }


    private Boolean completeTransparent(StackPane modStack) {
        int transparentCount = 0;

        for (int i = 0; i < 3; i++) {
            Circle circle = (Circle) modStack.getChildren().get(i);
            if (circle.getFill().equals(Color.TRANSPARENT)) {
                transparentCount++;
            }
        }
        return (transparentCount == 3);
    }

    private Boolean compare() {
        boolean equal = false;
        for (int i = 0; i < 3; i++) {
            Circle circle1 = (Circle) firstClick.getChildren().get(i);
            Circle circle2 = (Circle) secondClick.getChildren().get(i);
            if (circle1.getFill().equals(circle2.getFill()) &&
                    !circle1.getFill().equals(Color.TRANSPARENT)) {
                circle1.setFill(Color.TRANSPARENT);
                circle1.setStroke(Color.TRANSPARENT);
                circle2.setFill(Color.TRANSPARENT);
                circle2.setStroke(Color.TRANSPARENT);
                equal = true;
            }
        }
        return equal;
    }

    /**
     * This functions starts the javaFx application.
     *
     * @param args It is a command line argument which is none in this case.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
