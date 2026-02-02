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
    
    public Cell getCell(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board.length) {
            return board[row][col];
        }
        return null;
    }
    
    public Cell[][] getBoard() {
        return board;
    }
    
    public void displayBoard() {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(getPieceSymbol(piece) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private String getPieceSymbol(Piece piece) {
        String symbol = "";
        if (piece instanceof King) symbol = "K";
        else if (piece instanceof Queen) symbol = "Q";
        else if (piece instanceof Rook) symbol = "R";
        else if (piece instanceof Bishop) symbol = "B";
        else if (piece instanceof Knight) symbol = "N";
        else if (piece instanceof Pawn) symbol = "P";
        
        return piece.isWhite() ? symbol : symbol.toLowerCase();
    }
}
