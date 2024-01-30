/**
 * Pallav Regmi
 * Project1 - TileGame
 */

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.List;

public class Tile extends StackPane {
    private final List<Shape> elements;
    private final List<Color> colors;

    /**
     * This the constructor for this class. Board.java will use this to create
     * "tiles" for the game board.
     *
     * @param colors   colors for elements
     * @param elements shapes in tile
     */
    public Tile(List<Color> colors, List<Shape> elements) {
        this.colors = colors;
        this.elements = elements;
    }

    /**
     * This function uses the colors and fills circle from above and
     * puts them all into the same StackPane to be returned.
     *
     * @return StackPane which represents one "tiles" in the game board.
     */
    public StackPane generateTile() {
        StackPane stack = new StackPane();
        stack.getChildren().addAll(elements);
        setAlignment(Pos.CENTER);
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).setFill(colors.get(i));
            elements.get(i).setStroke(Color.GREY);
        }
        return stack;
    }
}
