public class HanoiTowers {
    // Main
    public static void main(String[] args){

        int n;
        if (args.length != 0)
            n = Integer.parseInt(args[0]);
        else
            n = 4;

        hanoi(n, "A", "B", "C");
    }

    // Verschieben der Scheiben
    private static void hanoi(int hoehe, String quelle, String ablage, String ziel) {
        if (hoehe == 1)
            System.out.println("Bewegen von " + quelle + " zu " + ziel);
        else {
            hanoi(hoehe - 1, quelle, ziel, ablage);
            System.out.println("Bewegen von " + quelle + " zu " + ziel);
            hanoi(hoehe - 1, ablage, quelle, ziel);
        }
    }
}

    // ================
    //   ============
    //     ========
    //       ====
    //        ==