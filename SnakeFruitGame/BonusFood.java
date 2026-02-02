package SnakeFruitGame;

public class BonusFood extends FoodItem {
    public BonusFood(int row, int column) {
        super(row, column);
        this.points = 5;
    }
    
}
