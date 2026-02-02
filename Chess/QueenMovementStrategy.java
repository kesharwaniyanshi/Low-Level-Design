package Chess;

public class QueenMovementStrategy implements MovementStrategy {

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int rowDiff = Math.abs(startCell.getRow() - endCell.getRow());
        int colDiff = Math.abs(startCell.getCol() - endCell.getCol());

        // Check for same row or same column movement (Rook-like)
        if (rowDiff == 0 || colDiff == 0) {
            return checkStraightPath(board, startCell, endCell);
        }

        // Check for diagonal movement (Bishop-like)
        if (rowDiff == colDiff) {
            return checkDiagonalPath(board, startCell, endCell);
        }

        return false;
    }
    
    private boolean checkStraightPath(Board board, Cell startCell, Cell endCell) {
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
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }
        return true;
    }
    
    private boolean checkDiagonalPath(Board board, Cell startCell, Cell endCell) {
        int rowDiff = Math.abs(startCell.getRow() - endCell.getRow());
        int rowDirection = (endCell.getRow() - startCell.getRow()) / rowDiff;
        int colDirection = (endCell.getCol() - startCell.getCol()) / rowDiff;
        
        int currentRow = startCell.getRow() + rowDirection;
        int currentCol = startCell.getCol() + colDirection;
        
        while (currentRow != endCell.getRow() && currentCol != endCell.getCol()) {
            if (board.getCell(currentRow, currentCol).getPiece() != null) {
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }
        return true;
    }

}
