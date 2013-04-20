import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class ImagePanel extends JPanel {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    protected void repaint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}