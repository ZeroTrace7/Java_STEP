import java.util.Random;

public class Fireworks {

    public static void main(String[] args) throws InterruptedException {
        final int WIDTH = 80;
        final int HEIGHT = 30;
        final String[] COLORS = {
            "\033[31m", // Red
            "\033[32m", // Green
            "\033[33m", // Yellow
            "\033[34m", // Blue
            "\033[35m", // Purple
            "\033[36m"  // Cyan
        };
        final String RESET = "\033[0m";

        Random rand = new Random();

        while (true) {
            // Clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Coordinates of the "explosion"
            int centerX = rand.nextInt(WIDTH - 20) + 10;
            int centerY = rand.nextInt(HEIGHT - 10) + 5;
            String color = COLORS[rand.nextInt(COLORS.length)];

            // Draw firework
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    int distance = (int) Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                    if (distance > 2 && distance < 6 && rand.nextInt(6) == 0) {
                        System.out.print(color + "*" + RESET);
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }

            Thread.sleep(300); // Time between each "explosion"
        }
    }
}
