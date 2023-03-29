import java.util.Scanner;

public class HT_v1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Geben Sie die Anzahl der Scheiben ein: ");
        int n = input.nextInt();

        int[][] towers = new int[3][n];
        for (int i = 0; i < n; i++) {
            towers[0][i] = n - i;
        }

        printTowers(towers);
        moveDisks(towers, n, 0, 2, 1);
    }

    public static void moveDisks(int[][] towers, int n, int source, int target, int auxiliary) {
        if (n == 1) {
            moveDisk(towers, source, target);
            printTowers(towers);
        } else {
            moveDisks(towers, n - 1, source, auxiliary, target);
            moveDisk(towers, source, target);
            printTowers(towers);
            moveDisks(towers, n - 1, auxiliary, target, source);
        }
    }

    public static void moveDisk(int[][] towers, int source, int target) {
        int disk = towers[source][topDisk(towers, source)];
        towers[source][topDisk(towers, source)] = 0;
        towers[target][topDisk(towers, target) + 1] = disk;
    }

    public static int topDisk(int[][] towers, int tower) {
        int top = -1;
        for (int i = 0; i < towers[tower].length; i++) {
            if (towers[tower][i] != 0) {
                top = i;
            }
        }
        return top;
    }

    public static void printTowers(int[][] towers) {
        for (int i = towers[0].length - 1; i >= 0; i--) {
            System.out.print("| ");
            for (int j = 0; j < towers.length; j++) {
                if (towers[j][i] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.printf("%-3d", towers[j][i]);
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println("---------------");
        System.out.println(" A   B   C");
        System.out.println();
    }
}
