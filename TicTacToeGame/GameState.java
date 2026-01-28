package LLD.TicTacToeGame;

public interface GameState {
    void next(GameContext context);
    boolean isGameOver();
}



