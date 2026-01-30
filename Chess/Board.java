package Chess;

public class Board {
    private static Board instance;
    private Cell[][] board;

    public Board(int rows) {
        initializeBoard(rows);
    }

    public static Board getInstance(int rows) {
        if (instance == null) {
            instance = new Board(rows);
        }
        return instance;
    }

    private void initializeBoard(int rows) {
        board = new Cell[rows][rows];

        // Setting white pieces
        setPieceRow(0, true);
        setPawnRow(1, rows, true);

        // Setting black pieces
        setPieceRow(rows - 1, false);
        setPawnRow(rows - 2, rows, false);

        // Setting empty cells
        for (int i = 2; i < rows - 2; i++) {
            {
                for (int j = 0; j < rows; j++) {
                    board[i][j] = new Cell(i, j, null);
                }
            }
        }
    }

    private void setPieceRow(int row, boolean isWhite) {
        board[row][0] = new Cell(row, 0, PieceFactory.createPiece("rook", isWhite));
        board[row][1] = new Cell(row, 1, PieceFactory.createPiece("knight", isWhite));
        board[row][2] = new Cell(row, 2, PieceFactory.createPiece("bishop", isWhite));
        board[row][3] = new Cell(row, 3, PieceFactory.createPiece("queen", isWhite));
        board[row][4] = new Cell(row, 4, PieceFactory.createPiece("king", isWhite));
        board[row][5] = new Cell(row, 5, PieceFactory.createPiece("bishop", isWhite));
        board[row][6] = new Cell(row, 6, PieceFactory.createPiece("knight", isWhite));
        board[row][7] = new Cell(row, 7, PieceFactory.createPiece("rook", isWhite));
    }

    private void setPawnRow(int row, int cols, boolean isWhite) {
        for (int j = 0; j < cols; j++) {
            board[row][j] = new Cell(row, j, PieceFactory.createPiece("pawn", isWhite));
        }
    }
}
