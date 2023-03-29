import java.util.Scanner;

public class HanoiTowers2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Anzahl der Scheiben: ");
        int n = input.nextInt();

        moveDisks(n, 'A', 'C', 'B');
    }

    public static void moveDisks(int n, char source, char target, char auxiliary) {
        if (n == 1) {
            System.out.println("Scheibe 1 von " + source + " nach " + target);
        } else {
            moveDisks(n - 1, source, auxiliary, target);
            System.out.println("Scheibe " + n + " von " + source + " nach " + target);
            moveDisks(n - 1, auxiliary, target, source);
        }
    }
}
