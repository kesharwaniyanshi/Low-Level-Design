package TicTacToeGame;

public class TicTacToeGame implements BoardGames {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameContext gameContext;

    public TicTacToeGame(PlayerStrategy xStrategy, PlayerStrategy oStrategy,
      int rows, int columns) {
    board = new Board(rows, columns);
    playerX = new Player(Symbol.X, xStrategy);
    playerO = new Player(Symbol.O, oStrategy);
    currentPlayer = playerX;
    gameContext = new GameContext();
  }

  
  @Override
  public void play() {
    do {
      // print the current state of the game
      board.printBoard();
      // current player makes the move
      Position move = currentPlayer.getPlayerStrategy().makeMove(board);
      board.makeMove(move, currentPlayer.getSymbol());
      // checks game state for win/draw
      board.checkGameState(gameContext);
      switchPlayer();
    } while (!gameContext.isGameOver());
    announceResult();
  }


  private void switchPlayer() {
    if (currentPlayer == playerX) {
      currentPlayer = playerO;
    } else {
      currentPlayer = playerX;
    }
  }
  // Displays the outcome of the game based on the final game state.
  private void announceResult() {
    GameState state = gameContext.getCurrentState();
    if (state instanceof XWonState) {
      System.out.println("Player X wins!");
    } else if (state instanceof OWonState) {
      System.out.println("Player O wins!");
    } else {
      System.out.println("It's a draw!");
    }
  }
}
