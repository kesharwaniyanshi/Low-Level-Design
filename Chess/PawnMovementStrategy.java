package Chess;

public class PawnMovementStrategy implements MovementStrategy {

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int direction = startCell.getPiece().isWhite() ? 1 : -1;
        int startRow = startCell.row;
        int startCol = startCell.col;
        int endRow = endCell.row;
        int endCol = endCell.col;

        // Standard one-square move
        if (endCol == startCol && endRow == startRow + direction && endCell.getPiece() == null) {
            return true;
        }

        // Initial two-square move
        if ((startRow == 1 && direction == 1) || (startRow == 6 && direction == -1)) {
            if (endCol == startCol && endRow == startRow + 2 * direction &&
                endCell.getPiece() == null &&
                board.getCell(startRow + direction, startCol).getPiece() == null) {
                return true;
            }
        }

        // Capturing move
        if (Math.abs(endCol - startCol) == 1 && endRow == startRow + direction) {
            Piece targetPiece = endCell.getPiece();
            if (targetPiece != null && targetPiece.isWhite() != startCell.getPiece().isWhite()) {
                return true;
            }
        }

        return false;
    }
}
