package it.tesi.java.accessi.view;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pannello extends JPanel {
    private static final long serialVersionUID = 1L;

    private BufferedImage image;
    private int x;
    private int y;
 
    // "imagePath" è il path dell'immagine da caricare
    // x e y sono le coordinate dell'immagine
    public Pannello(String imagePath, int x, int y) {
        image = loadImage(imagePath);
        this.x = x;
        this.y = y;
    }

    public void paintComponent(Graphics g) {

        g.drawImage(image, x, y, this);

        repaint();
    }

    // il parametro "path" è il path dell'immagine da caricare
    private static BufferedImage loadImage(String path) {
        BufferedImage bimg = null;
        BufferedImage ret = null;
        try {
            bimg = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
            ret = new BufferedImage(
                bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = ret.createGraphics();
            g.drawImage(bimg, 0, 0, null);
            g.dispose();

            return ret;
        }
}