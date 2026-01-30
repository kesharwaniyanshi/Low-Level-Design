package Chess;

public class Pawn extends Piece {
    private MovementStrategy movementStrategy;
    public Pawn(boolean isWhitePiece) {
        super(isWhitePiece, new PawnMovementStrategy());
    }
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
