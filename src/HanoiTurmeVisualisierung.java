import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HanoiTurmeVisualisierung extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int ROD_WIDTH = 10;
    private static final int DISK_HEIGHT = 15;
    private static final int DISK_WIDTH_INCREMENT = 20;
    private static final int DISK_Y_INCREMENT = 15;
    private static final Color[] DISK_COLORS = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW};
    private int numDisks;
    private int numMoves;
    private Stack<Integer>[] rods;
    private JFrame frame;

    @SuppressWarnings("unchecked")
    public HanoiTurmeVisualisierung(int numDisks) {
        this.numDisks = numDisks;
        rods = (Stack<Integer>[]) new Stack[3];
        for (int i = 0; i < 3; i++) {
            rods[i] = new Stack<Integer>();
        }
        for (int i = numDisks; i >= 1; i--) {
            rods[0].push(i);
        }
        numMoves = 0;
        frame = new JFrame("Towers of Hanoi");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void solve() {
        solve(numDisks, 0, 1, 2);
    }

    private void solve(int numDisks, int fromRod, int toRod, int auxRod) {
        if (numDisks == 1) {
            moveDisk(fromRod, toRod);
        } else {
            solve(numDisks - 1, fromRod, auxRod, toRod);
            moveDisk(fromRod, toRod);
            solve(numDisks - 1, auxRod, toRod, fromRod);
        }
    }

    private void moveDisk(int fromRod, int toRod) {
        numMoves++;
        int disk = rods[fromRod].pop();
        rods[toRod].push(disk);
        repaint();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int rodWidth = width / 3;
        int rodHeight = height - 50;
        int y = height - 20;
        for (int i = 0; i < 3; i++) {
            int x = i * rodWidth + rodWidth / 2 - ROD_WIDTH / 2;
            g2d.setColor(Color.BLACK);
            g2d.fillRect(x, height - rodHeight, ROD_WIDTH, rodHeight);
            Stack<Integer> rod = rods[i];
            int diskY = y;
            for (int j = 0; j < rod.size(); j++) {
                int diskWidth = rod.get(j) * DISK_WIDTH_INCREMENT;
                int diskX = i * rodWidth + rodWidth / 2 - diskWidth / 2;
                g2d.setColor(DISK_COLORS[rod.get(j) % DISK_COLORS.length]);
                g2d.fillRect(diskX, diskY, diskWidth, DISK_HEIGHT);
                diskY -= DISK_Y_INCREMENT;
            }
        }
        g2d.setColor(Color.BLACK);
        g2d.drawString("Moves: " + numMoves, 5, height - 10);
    }

    public static void main(String[] args) {
        HanoiTurmeVisualisierung HanoiTurmeVisualisierung = new HanoiTurmeVisualisierung(3); // Anzahl der Scheiben als Parameter
        HanoiTurmeVisualisierung.solve();
    }
}
