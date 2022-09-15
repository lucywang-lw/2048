import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JPanel implements KeyListener {

    Board curGame = new Board();
    static JFrame f = new JFrame("Welcome to Game 2048");
    static App game = new App();

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            curGame.up();
        } else if (key == KeyEvent.VK_DOWN) {
            curGame.down();
        } else if (key == KeyEvent.VK_LEFT) {
            curGame.left();
        } else if (key == KeyEvent.VK_RIGHT) {
            curGame.right();
        } else if (key == KeyEvent.VK_ENTER) {
            curGame = new Board();
            curGame.createTile();
        }
        curGame.createTile();
        f.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("2048 Game", 170, 20);
        g2.drawString("Score " + curGame.getScore(), 180, 40);
        g2.drawString("Press enter to start game", 220, 350);
        g2.setColor(Color.gray);
        g2.fillRoundRect(70, 70, 250, 250, 2, 2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tileGraphics(j * 60 + 80, i * 60 + 80, g, curGame.board[i][j]);
            }
        }
        if (curGame.over()) {
            g2.setColor(new Color(133, 210, 255));
            g2.fillRoundRect(70, 70, 250, 250, 2, 2);
            g2.setColor(Color.black);
            g2.drawString("Game Over", 160, 170);
        }
    }

    public void tileGraphics(int x, int y, Graphics g, Tile sq) {
        Graphics2D g2 = (Graphics2D) g;
        int val = sq.getTile();
        Color color = sq.getCol();

        g2.setColor(color);
        g2.fillRoundRect(x, y, 50, 50, 5, 5);
        g2.setColor(Color.black);

        if (val != 0) {
            g.drawString(String.valueOf(val), x + 22, y + 30);
        }
    }

    public static void main(String[] args) {
        f.addKeyListener(game);
        f.getContentPane().add(game);
        f.setSize(400, 400);
        f.setVisible(true);
        f.setResizable(false);
    }
}
