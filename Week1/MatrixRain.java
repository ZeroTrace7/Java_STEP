import java.util.Random;

public class MatrixRain {

    public static void main(String[] args) throws InterruptedException {
        final int WIDTH = 200;
        final int HEIGHT = 50;
        final char[] CHARS = "01".toCharArray(); // Can also use Unicode characters

        int[] drops = new int[WIDTH];
        Random rand = new Random();

        for (int i = 0; i < WIDTH; i++) {
            drops[i] = rand.nextInt(HEIGHT);
        }

        while (true) {
            // ANSI escape code to clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            for (int row = 0; row < HEIGHT; row++) {
                for (int col = 0; col < WIDTH; col++) {
                    if (drops[col] == row) {
                        System.out.print("\033[32m" + CHARS[rand.nextInt(CHARS.length)]); // Green text
                        drops[col] = (drops[col] + 1) % HEIGHT;
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }

            Thread.sleep(75); // Controls the speed
        }
    }
}
