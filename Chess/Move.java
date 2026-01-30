package Chess;

public class Move {
    private Cell startCell;
    private Cell endCell;

    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }

    public boolean isValid(){
        return !(startCell.getPiece().isWhite() == endCell.getPiece().isWhite());
    }
    public Cell getStartCell() {
        return startCell;
    }
    public Cell getEndCell() {
        return endCell;
    }
}
