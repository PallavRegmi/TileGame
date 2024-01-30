TilesGame.java:
1. Class Overview:
   - The main class for the JavaFX game.
   - Implements a memory-matching tile game.
2. Attributes:
   - GAME_Game_BOARD: An instance of the GameBoard class representing the game board.
   - firstClick and secondClick: StackPanes representing the first and second clicked tiles.
   - currCombo: Current combo count.
   - longestCombo: Longest combo count.
3. Methods:
   - start(Stage primaryStage): Initializes and displays the game board, handles user clicks, and updates the game state.
   - displayCurrentCombo(): Displays and updates the current combo count on the game board.
   - displayLongestCombo(): Displays and updates the longest combo count on the game board.
   - gameEnd(): Checks if the game is over by examining transparency of tiles.
   - completeTransparent(): Checks if all circles in a tile are transparent.
   - compare(): Compares colors of circles in two clicked tiles.

GameBoard.java:
1. Class Overview:
   - Represents the game board.
2. Attributes:
   - NUM_COLS, NUM_ROWS, NUM_ELEMENTS: Constants defining the dimensions of the game board.
3. Methods:
   - addTiles(): Generates a random game board with colored circles arranged in tiles.
   - popRandClr(): Populates a list of random colors for the tiles.
   - genBorder(): Generates a white border (circle) with transparent fill.
   - getNUM_COLS(), getNUM_ROWS(): Getter methods for the number of columns and rows.

Tile.java:
1. Class Overview:
   - Represents an individual tile on the game board.
2. Attributes:
   - elements: List of shapes representing circles in the tile.
   - colors: List of colors for the circles in the tile.
3. Methods:
   - generateTile(): Generates a StackPane representing a tile with colored circles.
General Flow:
1. The game board is initialized and displayed.
2. User clicks on two tiles, and the game logic determines if they match or not.
3. Combo counts are updated based on the game state.
4. The game continues until all tiles are matched, and a "Game Over" message is displayed.
The game involves matching colored circles on tiles and keeping track of the current and longest combo counts.
