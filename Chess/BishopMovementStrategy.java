package Chess;

public class BishopMovementStrategy implements MovementStrategy {

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int rowDiff = Math.abs(startCell.getRow() - endCell.getRow());
        int colDiff = Math.abs(startCell.getCol() - endCell.getCol());
        
        // Must move diagonally
        if (rowDiff != colDiff) {
            return false;
        }
        
        // Check if path is clear
        int rowDirection = (endCell.getRow() - startCell.getRow()) / rowDiff;
        int colDirection = (endCell.getCol() - startCell.getCol()) / colDiff;
        
        int currentRow = startCell.getRow() + rowDirection;
        int currentCol = startCell.getCol() + colDirection;
        
        while (currentRow != endCell.getRow() && currentCol != endCell.getCol()) {
            if (board.getCell(currentRow, currentCol).getPiece() != null) {
                return false; // Path is blocked
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }
        
        return true;
    }

}
