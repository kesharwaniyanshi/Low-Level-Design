package SnakeFruitGame;

public interface MovementStrategy {
    Pair getNextHeadPosition(Pair currentHead, String direction);
}
