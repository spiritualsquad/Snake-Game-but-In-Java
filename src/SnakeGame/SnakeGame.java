package SnakeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SnakeGame {
    public static void main(String[] args) {
        // Initialize variables
        List<Point> snake = new ArrayList<>();
        snake.add(new Point(0, 0)); // Snake starting position
        Random random = new Random();
        Point food = new Point(random.nextInt(5), random.nextInt(5)); // Initial food position
        int score = 0;

        // Main game loop
        while (score < 100) {
            // Display the game board
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Point currentPoint = new Point(i, j);
                    if (snake.contains(currentPoint)) {
                        if (currentPoint.equals(snake.get(0))) // Snake head
                            System.out.print("S ");
                        else if (currentPoint.equals(food)) // Snake body or head on food
                            System.out.print("# ");
                        else // Snake body
                            System.out.print("o ");
                    } else if (currentPoint.equals(food))
                        System.out.print("F "); // Food
                    else
                        System.out.print(". "); // Empty space
                }
                System.out.println();
            }

            // Get user input for snake direction
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter direction (up/down/right/left): ");
            String userInput = scanner.nextLine().toLowerCase();

            // Update snake position based on user input
            Point head = snake.get(0);
            Point newHead;
            switch (userInput) {
                case "up":
                    newHead = new Point(head.getX() - 1, head.getY());
                    break;
                case "down":
                    newHead = new Point(head.getX() + 1, head.getY());
                    break;
                case "right":
                    newHead = new Point(head.getX(), head.getY() + 1);
                    break;
                case "left":
                    newHead = new Point(head.getX(), head.getY() - 1);
                    break;
                default:
                    continue;
            }

            // Check if snake eats the food
            if (newHead.equals(food)) {
                snake.add(0, newHead);
                food = new Point(random.nextInt(5), random.nextInt(5)); // Get new food
                score += 10;
            } else {
                // Move the snake
                snake.add(0, newHead);
                snake.remove(snake.size() - 1);
            }

            // Check if the snake hits the wall and reset the game
            if (newHead.getX() < 0 || newHead.getX() >= 5 || newHead.getY() < 0 || newHead.getY() >= 5) {
                System.out.println("Game Over! Your score: " + score);
                break;
            }
        }

        // End of the game
        if (score >= 100)
            System.out.println("Congratulations! You reached 100 points and won the game!");
    }
}



