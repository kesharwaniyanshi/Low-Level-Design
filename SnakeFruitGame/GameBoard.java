package SnakeFruitGame;

public class GameBoard {
    private static GameBoard instance; // Singleton instance
    private int width;
    private int height;
    
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

     // Method to get the singleton instance of the game board
    public static GameBoard getInstance(int width, int height) {
        if (instance == null) {
            instance = new GameBoard(width, height); // Create instance if not already created
        }
        return instance; // Return the existing instance
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
