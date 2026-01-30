package Chess;

public class Main {
    public static void main(String[] args) {
        // Create players
        Player whitePlayer = new Player("Player1", true); // White
        Player blackPlayer = new Player("Player2", false); // Black
        // Initialize game
        ChessGame chessGame = new ChessGame(whitePlayer, blackPlayer);
        // Start the game
        chessGame.start();
    }
}