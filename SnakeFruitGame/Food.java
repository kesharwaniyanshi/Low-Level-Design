package SnakeFruitGame;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private List<FoodItem> foodItems;
    private int currentFoodIndex;
    
    // Constructor with positions only (defaults to NORMAL food)
    public Food(int[][] foodPositions) {
        this.foodItems = new ArrayList<>();
        for (int[] position : foodPositions) {
            this.foodItems.add(FoodFactory.createFood(position, "NORMAL"));
        }
        this.currentFoodIndex = 0;
    }
    
    // Constructor with positions and types
    public Food(int[][] foodPositions, String[] foodTypes) {
        this.foodItems = new ArrayList<>();
        for (int i = 0; i < foodPositions.length; i++) {
            String type = i < foodTypes.length ? foodTypes[i] : "NORMAL";
            this.foodItems.add(FoodFactory.createFood(foodPositions[i], type));
        }
        this.currentFoodIndex = 0;
    }
    
    // Returns the FoodItem if eaten, null otherwise
    public FoodItem ateFood(int row, int col) {
        if (currentFoodIndex < foodItems.size()) {
            FoodItem currentFood = foodItems.get(currentFoodIndex);
            if (currentFood.getRow() == row && currentFood.getColumn() == col) {
                currentFoodIndex++;
                return currentFood;
            }
        }
        return null;
    }
    
    public FoodItem getCurrentFood() {
        if (currentFoodIndex < foodItems.size()) {
            return foodItems.get(currentFoodIndex);
        }
        return null;
    }
}
