package Chess;

public class Move {
    private Cell startCell;
    private Cell endCell;

    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }

    public boolean isValid(){
        // Check if start cell has a piece
        if (startCell.getPiece() == null) {
            return false;
        }
        
        // If end cell is empty, it's potentially valid (will be checked by movement rules)
        if (endCell.getPiece() == null) {
            return true;
        }
        
        // If end cell has a piece, ensure it's of opposite color
        return startCell.getPiece().isWhite() != endCell.getPiece().isWhite();
    }
    public Cell getStartCell() {
        return startCell;
    }
    public Cell getEndCell() {
        return endCell;
    }
}
