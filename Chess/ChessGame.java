package Chess;

import java.util.ArrayList;
import java.util.Scanner;


public class ChessGame {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    boolean isWhiteTurn;
    private ArrayList<Move> gameLog;
    private Status status;

    ChessGame(Player whitePlayer2, Player blackPlayer2) {
        this.whitePlayer = whitePlayer2;
        this.blackPlayer = blackPlayer2;
        this.board = Board.getInstance(8); // Initialize board with 8 rows
        this.isWhiteTurn = true;
        this.status = Status.ACTIVE;
        this.gameLog = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== CHESS GAME =====");
        System.out.println("White: " + whitePlayer.getName());
        System.out.println("Black: " + blackPlayer.getName());
        System.out.println("Enter moves as: startRow startCol endRow endCol (e.g., 1 0 3 0)");
        System.out.println("Enter 'quit' to exit");
        System.out.println("======================\n");
        
        // Continue the game till the status is active
        while (this.status == Status.ACTIVE) {
            board.displayBoard();
            
            Player currentPlayer = isWhiteTurn ? whitePlayer : blackPlayer;
            System.out.println("Turn: " + (isWhiteTurn ? "White" : "Black") + " (" + currentPlayer.getName() + ")");
            System.out.print("Enter move: ");
            
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Game ended by player.");
                status = Status.SAVED;
                break;
            }
            
            try {
                String[] parts = input.split("\\s+");
                if (parts.length != 4) {
                    System.out.println("Invalid input! Use format: startRow startCol endRow endCol");
                    continue;
                }
                
                int startRow = Integer.parseInt(parts[0]);
                int startCol = Integer.parseInt(parts[1]);
                int endRow = Integer.parseInt(parts[2]);
                int endCol = Integer.parseInt(parts[3]);
                
                Cell startCell = board.getCell(startRow, startCol);
                Cell endCell = board.getCell(endRow, endCol);
                
                if (startCell == null || endCell == null) {
                    System.out.println("Invalid coordinates! Use values 0-7.");
                    continue;
                }
                
                Move move = new Move(startCell, endCell);
                makeMove(move, currentPlayer);
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numbers.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
        displayGameResult();
    }
    
    private void displayGameResult() {
        board.displayBoard();
        System.out.println("\n===== GAME OVER =====");
        if (status == Status.WHITE_WIN) {
            System.out.println(whitePlayer.getName() + " (White) wins!");
        } else if (status == Status.BLACK_WIN) {
            System.out.println(blackPlayer.getName() + " (Black) wins!");
        } else if (status == Status.STALEMATE) {
            System.out.println("Game ended in stalemate.");
        } else if (status == Status.SAVED) {
            System.out.println("Game saved.");
        }
        System.out.println("Total moves: " + gameLog.size());
    }
    public void makeMove(Move move, Player player) {
        // Check if it's the correct player's turn
        if ((isWhiteTurn && !player.isWhiteSide()) || (!isWhiteTurn && player.isWhiteSide())) {
            System.out.println("Not your turn!");
            return;
        }
        
        // Check if the start cell has a piece
        Piece sourcePiece = move.getStartCell().getPiece();
        if (sourcePiece == null) {
            System.out.println("No piece at the start position!");
            return;
        }
        
        // Check if the player is moving their own piece
        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            System.out.println("You can only move your own pieces!");
            return;
        }
        
        // Initial check for a valid move
        if (!move.isValid()) {
            System.out.println("Invalid move! Cannot capture your own piece.");
            return;
        }
        
        // Check if the source piece can be moved according to its rules
        if (!sourcePiece.canMove(this.board, move.getStartCell(), move.getEndCell())) {
            System.out.println("Invalid move for this piece!");
            return;
        }
        
        Piece destinationPiece = move.getEndCell().getPiece();
        
        // Check if the destination cell contains opponent's King
        if (destinationPiece != null) {
            if (destinationPiece instanceof King) {
                if (isWhiteTurn) {
                    this.status = Status.WHITE_WIN;
                } else {
                    this.status = Status.BLACK_WIN;
                }
                System.out.println("\\nKing captured! Game Over!");
                return;
            }
            // Set the destination piece as killed
            destinationPiece.setKilled(true);
            System.out.println("Captured " + (destinationPiece.isWhite() ? "White" : "Black") + " piece!");
        }
        
        // Adding the valid move to the game logs
        gameLog.add(move);
        
        // Moving the source piece to the destination cell
        move.getEndCell().setPiece(sourcePiece);
        
        // Setting the source cell to null (means it doesn't have any piece)
        move.getStartCell().setPiece(null);
        
        // Toggling the turn
        isWhiteTurn = !isWhiteTurn;
        
        System.out.println("Move successful!\\n");
    }
}
