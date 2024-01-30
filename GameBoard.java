/**
 *Pallav Regmi
 * Project1 - TileGame
 */

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.*;

public class GameBoard {
    private final int NUM_COLS = 4;
    private final int NUM_ROWS = 5;
    private final int NUM_ELEMENTS = 3;

    /**
     * This function randomly grabs colors from a bank and fills different size shapes defined by NUM_ELEMENTS.
     * Then, those shapes are all put into a StackPane.
     * Finally, those StackPanes get inserted into a GridPane by indexing its
     * rows and columns.
     *
     * @return GridPane This GridPane is the entire game board.
     */
    public GridPane addTiles() {
        List<List<Color>> colors = new ArrayList<>();
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            List<Color> elementColor = popRandClr();
            Collections.shuffle(elementColor);
            colors.add(elementColor);
        }
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        int colorCounter = 0;
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                List<Shape> elements = new ArrayList<>();
                List<Color> elementColors = new ArrayList<>();
                for (int k = 0; k < NUM_ELEMENTS; k++) {
                    elementColors.add(colors.get(k).get(colorCounter));
                    elements.add(new Circle(25 + 25 * k));
                }
                Collections.reverse(elements);

                Tile tile = new Tile(elementColors, elements);
                colorCounter++;
                gridPane.add(tile.generateTile(), i, j);
            }
        }
        return gridPane;
    }


    private List<Color> popRandClr() {
        Random rand = new Random();
        List<String> colorHex = new ArrayList<>();
        Collections.addAll(colorHex, "FE5D26", "F2C078", "FAEDCA",
                "C1DBB3", "7EBC89", "61A0AF", "F06C9B", "F9B9B7", "36494E",
                "597081", "F4998D", "F40000", "917C78", "B79492", "508CA4",
                "004F2D", "C2E812", "FF934F", "918EF4", "141B41");

        List<Color> colors = new ArrayList<>();
        for (int i = 1; i <= (NUM_COLS * NUM_ROWS) / 2; i++) {
            int randomNum = rand.nextInt(20);
            Color color = Color.valueOf(colorHex.get(randomNum));
            colors.add(color);
            colors.add(color);
        }
        return colors;
    }


    /**
     * This function generates a border when called upon. The border is nothing
     * more than a WHITE Stroke with a transparent Fill.
     *
     * @return Circle that represents a white border.
     */
    public Circle genBorder() {
        Circle border = new Circle(25 + (25 * (NUM_ELEMENTS - 1)));
        border.setStrokeWidth(5);
        border.setStroke(Color.WHITE);
        border.setFill(Color.TRANSPARENT);
        border.setStrokeType(StrokeType.INSIDE);
        return border;
    }


    /**
     * When this function is called it will return NUM_COLS. Which is the
     * number of columns in the game board specified at the top of this class.
     *
     * @return int Number of Columns in the game board.
     */
    public int getNUM_COLS() {
        return NUM_COLS;
    }


    /**
     * When this function is called it will return NUM_ROWS. Which is the
     * number of rows in the game board specified at the top of this class.
     *
     * @return in Number of Rows in the game board.
     */
    public int getNUM_ROWS() {
        return NUM_ROWS;
    }
}
