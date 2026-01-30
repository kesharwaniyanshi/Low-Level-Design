package Chess;

public class BishopMovementStrategy implements MovementStrategy {

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int rowDiff = Math.abs(startCell.row - endCell.row);
        int colDiff = Math.abs(startCell.col - endCell.col);
        return rowDiff == colDiff;
    }

}
