package LLD.TicTacToeGame;

public class Main {
    public static void main(String[] args) {
        PlayerStrategy xStrategy = new HumanPlayerStrategy("Player X");
        PlayerStrategy oStrategy = new HumanPlayerStrategy("Player O");
        TicTacToeGame game = new TicTacToeGame(xStrategy, oStrategy, 3, 3);
        game.play();
    }
}