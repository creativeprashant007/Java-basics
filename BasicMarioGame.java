import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BasicMarioGame extends JPanel implements ActionListener, KeyListener {

    private int playerX = 50;  // Player's X position
    private int playerY = 300; // Player's Y position
    private int playerWidth = 40;
    private int playerHeight = 60;
    private int playerVelocityY = 0;
    private int playerVelocityX = 0;
    private boolean onGround = true;
    private boolean facingRight = true;
    
    private final Timer timer = new Timer(15, this); // Game loop timer

    private Image playerStandingRight;
    private Image playerStandingLeft;
    private Image playerRunningRight;
    private Image playerRunningLeft;

    public BasicMarioGame() {
        JFrame frame = new JFrame("Basic Mario Game");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setVisible(true);

        // Load player images
        playerStandingRight = new ImageIcon("player_standing_right.png").getImage();
        playerStandingLeft = new ImageIcon("player_standing_left.png").getImage();
        playerRunningRight = new ImageIcon("player_running_right.png").getImage();
        playerRunningLeft = new ImageIcon("player_running_left.png").getImage();

        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(new Color(135, 206, 250)); // Sky blue
        g.fillRect(0, 0, getWidth(), getHeight());

        // Ground
        g.setColor(new Color(34, 139, 34)); // Green
        g.fillRect(0, 340, getWidth(), 60);

        // Platform
        g.setColor(new Color(139, 69, 19)); // Brown
        g.fillRect(200, 280, 100, 20);

        // Player
        Image currentImage;
        if (playerVelocityX == 0) {
            currentImage = facingRight ? playerStandingRight : playerStandingLeft;
        } else {
            currentImage = facingRight ? playerRunningRight : playerRunningLeft;
        }
        g.drawImage(currentImage, playerX, playerY, playerWidth, playerHeight, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Gravity
        if (playerY + playerHeight < 340) {
            playerVelocityY += 1; // Simulate gravity
            onGround = false;
        } else {
            playerVelocityY = 0;
            onGround = true;
            playerY = 340 - playerHeight; // Reset position to ground level
        }

        // Horizontal movement
        playerX += playerVelocityX;

        // Ensure the player stays within bounds
        if (playerX < 0) playerX = 0;
        if (playerX + playerWidth > getWidth()) playerX = getWidth() - playerWidth;

        playerY += playerVelocityY;

        repaint(); // Redraw the game screen
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            playerVelocityX = -5; // Move left
            facingRight = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            playerVelocityX = 5; // Move right
            facingRight = true;
        } else if (key == KeyEvent.VK_SPACE && onGround) {
            playerVelocityY = -15; // Jump
            onGround = false;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            playerVelocityX = 0; // Stop horizontal movement
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action needed on key typed for this game
    }

    public static void main(String[] args) {
        new BasicMarioGame();
    }
}