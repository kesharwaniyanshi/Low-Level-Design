package Chess;

public class King extends Piece {
    private MovementStrategy movementStrategy;
    public King(boolean isWhitePiece) {
        super(isWhitePiece, new KingMovementStrategy());
    }

    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
