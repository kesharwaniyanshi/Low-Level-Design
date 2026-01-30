package Chess;

public class KingMovementStrategy implements MovementStrategy {
    
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int rowDiff = Math.abs(startCell.row - endCell.row);
        int colDiff = Math.abs(startCell.col - endCell.col);
        return (rowDiff <= 1 && colDiff <= 1);
    }

}
