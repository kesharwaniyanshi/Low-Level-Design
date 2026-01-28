package LLD.TicTacToeGame;

public class GameContext {
    private GameState currenState;
    
    public GameContext() {
        this.currenState = new XTurnState();
    }

    public void setState(GameState state) {
        this.currenState = state;
    }

    public void next()
    {
        currenState.next(this);
    }

    public boolean isGameOver() {
        return currenState.isGameOver();
    }

    public GameState getCurrentState() {
        return currenState;
    }

}
