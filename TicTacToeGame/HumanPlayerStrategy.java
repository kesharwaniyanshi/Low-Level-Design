package LLD.TicTacToeGame;

import java.util.Scanner;

public class HumanPlayerStrategy implements PlayerStrategy {
        private Scanner scanner;
        private String playerName;

        public HumanPlayerStrategy(String playerName) {
    this.playerName = playerName;
    this.scanner = new Scanner(System.in);
  }

        @Override
        public Position makeMove(Board board) {
            while(true)
            {
                try {
                    System.out.println(playerName + ", enter a row and column number (0, 1, or 2) to place your mark");
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();
                    Position move = new Position(row, col);
                    if(board.isValidMove(move))
                    {
                        return move;
                    }
                    else
                    {
                        System.out.println("This move is not valid");
                    }
                } catch (Exception e) {
                   System.out.println("Invalid input. Please enter numbers for row and column.");
                   scanner.nextLine(); // Clear the invalid input
                }
            }
        }
}