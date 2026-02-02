package SnakeFruitGame;

public class FoodFactory {
    public static FoodItem createFood(int []position, String type) {
        if (type.equalsIgnoreCase("NORMAL")) {
            return new NormalFood(position[0], position[1]);
        } else if (type.equalsIgnoreCase("BONUS")) {
            return new BonusFood(position[0], position[1]);
        }
        throw new IllegalArgumentException("Unknown food type: " + type);
    }
}
