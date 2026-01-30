package Chess;

public class QueenMovementStrategy implements MovementStrategy {

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int rowDiff = Math.abs(startCell.row - endCell.row);
        int colDiff = Math.abs(startCell.col - endCell.col);

        // Check for same row or same column movement
        if (rowDiff == 0 || colDiff == 0) {
            return true;
        }

        // Check for diagonal movement
        if (rowDiff == colDiff) {
            return true;
        }

        return false;
    }

}
