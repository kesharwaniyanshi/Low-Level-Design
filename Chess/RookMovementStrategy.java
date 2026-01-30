package Chess;

public class RookMovementStrategy  implements MovementStrategy {

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        // Rook moves in straight lines either horizontally or vertically
        return startCell.row == endCell.row || startCell.col == endCell.col;
    }

}
