package Chess;

public class Knight extends Piece {
    private MovementStrategy movementStrategy;
    public Knight(boolean isWhitePiece) {
        super(isWhitePiece, new KnightMovementStrategy());
    }

    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
