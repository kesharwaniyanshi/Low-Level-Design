package SnakeFruitGame;

public class SnakeGame {
    private GameBoard board;
    protected Snake snake;
    private Food food;
    private int score;	   
    private MovementStrategy movementStrategy; 
    // Game initialization and movement logic
    public SnakeGame(int width, int height, int[][] foodPositions) {
        this.board = GameBoard.getInstance(width, height);
        this.snake = new Snake();
        this.food = new Food(foodPositions);
        this.score = 0;

        this.movementStrategy = new HumanMovementStrategy();
    }
    
    // Constructor with food types for different point values
    public SnakeGame(int width, int height, int[][] foodPositions, String[] foodTypes) {
        this.board = GameBoard.getInstance(width, height);
        this.snake = new Snake();
        this.food = new Food(foodPositions, foodTypes);
        this.score = 0;

        this.movementStrategy = new HumanMovementStrategy();
    }

     // Set the movement strategy (Human or AI)
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }
    
    public int getScore() {
        return this.score;
    }

    public int move(String direction) {
       
        Pair currenthead = snake.getHead();

        Pair newHead = movementStrategy.getNextHeadPosition(currenthead, direction);
        int newHeadRow = newHead.getRow();
        int newHeadColumn = newHead.getCol(); 

        boolean hitWall = newHeadRow < 0 || newHeadRow >= this.board.getHeight() ||
                          newHeadColumn < 0 || newHeadColumn >= this.board.getWidth();

        Pair currentTail = snake.getTail();

        boolean hitSelf = snake.positionMap.containsKey(newHead) && !(newHead.equals(currentTail));

        if (hitWall || hitSelf) {
            return -1; // Game over
        }

        FoodItem eatenFood = food.ateFood(newHeadRow, newHeadColumn);
        if(eatenFood != null) {
            // Add points based on food type (NormalFood = 1, BonusFood = 5)
            this.score += eatenFood.getPoints();
        } else {
            // Remove tail
            Pair tail = snake.body.pollLast();
            snake.positionMap.remove(tail);
        }
        // Add new head
        this.snake.body.addFirst(newHead);
        this.snake.positionMap.put(newHead, true);       
        // Return current score (not snake length)
        return this.score;

    }
}
