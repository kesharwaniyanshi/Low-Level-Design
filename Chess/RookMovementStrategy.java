package Chess;

public class RookMovementStrategy  implements MovementStrategy {

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        // Rook moves in straight lines either horizontally or vertically
        if (startCell.getRow() != endCell.getRow() && startCell.getCol() != endCell.getCol()) {
            return false;
        }
        
        // Check if path is clear
        int rowDirection = 0;
        int colDirection = 0;
        
        if (startCell.getRow() != endCell.getRow()) {
            rowDirection = (endCell.getRow() - startCell.getRow()) > 0 ? 1 : -1;
        }
        if (startCell.getCol() != endCell.getCol()) {
            colDirection = (endCell.getCol() - startCell.getCol()) > 0 ? 1 : -1;
        }
        
        int currentRow = startCell.getRow() + rowDirection;
        int currentCol = startCell.getCol() + colDirection;
        
        while (currentRow != endCell.getRow() || currentCol != endCell.getCol()) {
            if (board.getCell(currentRow, currentCol).getPiece() != null) {
                return false; // Path is blocked
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }
        
        return true;
    }

}
