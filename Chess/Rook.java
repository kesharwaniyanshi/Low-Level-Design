package Chess;

public class Rook extends Piece {
    private MovementStrategy movementStrategy;
    public Rook(boolean isWhitePiece) {
        super(isWhitePiece, new RookMovementStrategy());
    }

    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
