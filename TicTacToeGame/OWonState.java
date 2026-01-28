package LLD.TicTacToeGame;

public class OWonState implements GameState {
    @Override
    public void next(GameContext context) {
        // No next state since the game is over
    }

    @Override
    public boolean isGameOver() {
        return true;
    }
    
}
