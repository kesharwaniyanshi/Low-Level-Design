package Chess;

public class Queen extends Piece {
    private MovementStrategy movementStrategy;
    public Queen(boolean isWhitePiece) {
        super(isWhitePiece, new QueenMovementStrategy());
    }
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
