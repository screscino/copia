package it.tesi.java.accessi.view;



import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Finestra extends JFrame {
    private static final long serialVersionUID = 1L;

    public Finestra( ) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI(600, 600);
            }
        });
    }

    private void createGUI(int frameDimX, int frameDimY) {
        this.setSize(frameDimX + 16,frameDimY + 34); // 16,34 = bordi della finestra
        this.getContentPane().add(new Pannello("C:/immagine.jpg", 100, 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
