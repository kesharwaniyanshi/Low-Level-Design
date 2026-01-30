package Chess;

public abstract class Piece {
    private boolean isWhitePiece;
    private boolean killed = false;
    private MovementStrategy movementStrategy;

    public Piece(boolean isWhitePiece, MovementStrategy movementStrategy) {
        this.isWhitePiece = isWhitePiece;
        this.movementStrategy = movementStrategy;
    }

    public boolean isWhite() {
        return isWhitePiece;
    }

    public boolean isKilled() {
        return killed;
    }
    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
