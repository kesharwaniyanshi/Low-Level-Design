package Chess;

public class KingMovementStrategy implements MovementStrategy {
    
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int rowDiff = Math.abs(startCell.getRow() - endCell.getRow());
        int colDiff = Math.abs(startCell.getCol() - endCell.getCol());
        return (rowDiff <= 1 && colDiff <= 1);
    }

}
