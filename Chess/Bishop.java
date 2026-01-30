package Chess;

public class Bishop extends Piece {
    private MovementStrategy movementStrategy;
    public Bishop(boolean isWhitePiece) {
        super(isWhitePiece, new BishopMovementStrategy());
    }   

    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
