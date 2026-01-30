package TicTacToeGame;

public class Board {
    private final int rows;
    private final int cols;
    private Symbol[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Symbol[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public boolean isValidMove(Position position) {
        int row = position.row;
        int col = position.col;
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }
        return grid[row][col] == Symbol.EMPTY;
    }

    public void makeMove(Position position, Symbol symbol) {
        grid[position.row][position.col] = symbol;
    }

    public void checkGameState(GameContext context) {
        // Check rows for a win
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] != Symbol.EMPTY && isWinningLine(grid[i])) {
                if (grid[i][0] == Symbol.X) {
                    context.setState(new XWonState());
                } else {
                    context.setState(new OWonState());
                }
                return;
            }
        }
        // Check columns for a win
        for (int i = 0; i < cols; i++) {
            Symbol[] column = new Symbol[rows];
            for (int j = 0; j < rows; j++) {
                column[j] = grid[j][i];
            }
            if (column[0] != Symbol.EMPTY && isWinningLine(column)) {
                if (column[0] == Symbol.X) {
                    context.setState(new XWonState());
                } else {
                    context.setState(new OWonState());
                }
                return;
            }
        }
        // Check diagonals for a win
        Symbol[] diagonal1 = new Symbol[Math.min(rows, cols)];
        Symbol[] diagonal2 = new Symbol[Math.min(rows, cols)];
        for (int i = 0; i < Math.min(rows, cols); i++) {
            diagonal1[i] = grid[i][i];
            diagonal2[i] = grid[i][cols - 1 - i];
        }
        if (diagonal1[0] != Symbol.EMPTY && isWinningLine(diagonal1)) {
            if (diagonal1[0] == Symbol.X) {
                context.setState(new XWonState());
            } else {
                context.setState(new OWonState());
            }
            return;
        }
        if (diagonal2[0] != Symbol.EMPTY && isWinningLine(diagonal2)) {
            if (diagonal2[0] == Symbol.X) {
                context.setState(new XWonState());
            } else {
                context.setState(new OWonState());
            }
            return;
        }
        // Check for a draw (all cells are filled)
        if (isBoardFull()) {
            context.setState(new DrawState());
        }
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinningLine(Symbol[] line) {
        Symbol first = line[0];
        for (Symbol s : line) {
            if (s != first) {
                return false;
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Symbol symbol = grid[i][j];
                switch (symbol) {
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                    default:
                        System.out.print(" . ");
                }
                if (j < cols - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < rows - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }
}
