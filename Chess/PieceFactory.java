package Chess;

public class PieceFactory {
    public static Piece createPiece(String pieceType, boolean isWhitePiece) {
        switch (pieceType.toLowerCase()) {
            case "pawn":
                return new Pawn(isWhitePiece);
            case "rook":
                return new Rook(isWhitePiece);
            case "knight":
                return new Knight(isWhitePiece);
            case "bishop":
                return new Bishop(isWhitePiece);
            case "queen":
                return new Queen(isWhitePiece);
            case "king":
                return new King(isWhitePiece);
            default:
                throw new IllegalArgumentException("Invalid piece type: " + pieceType);
        }
    }
}
