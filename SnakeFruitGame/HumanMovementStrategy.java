package SnakeFruitGame;

public class HumanMovementStrategy implements MovementStrategy {
    @Override
    public Pair getNextHeadPosition(Pair currentHead, String direction) {
        int row = currentHead.getRow();
        int col = currentHead.getCol();
        
        switch (direction) {
            case "U":
                return new Pair(row - 1, col);
            case "D":
                return new Pair(row + 1, col);
            case "L":
                return new Pair(row, col - 1);
            case "R":
                return new Pair(row, col + 1);
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }
}