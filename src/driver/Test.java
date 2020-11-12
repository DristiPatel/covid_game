package driver;

import java.awt.*;
import view.Board;
import view.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Test {
    
    private JFrame f;
    private JPanel p;
    private JButton b1; 
    private JLabel lab;
    private Graphics g;

    public Test() {
        
        gui();
        
    }

    public void gui() {
        /* f = new JFrame("Testing");
        f.setVisible(true);
        f.setSize(1200,800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); */

        // draw and make the board visible
      
        f = new JFrame("Testing");
        f.setVisible(true);

        Dimension d = new Dimension(800,600);
        f.setSize(d);
        
       
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        p = new JPanel();
        // p.setBackground(Color.black);

        /*
        g.setColor(Color.blue);
        g.fillRect(0, 0, 800, 600);
        */
        /*
        g = p.getGraphics();

        
        BufferedImage blueBackground = null;
        try{
            blueBackground = ImageIO.read(new File("src/view/images/BlueBackground.jpeg"));
        }catch (IOException e){
            System.out.println("Error loading image");
        }
        
        g.drawImage(blueBackground, 0, 0, 1200, 800, f);
        // create button and label
        */
        b1 = new JButton("Play");
        lab = new JLabel("Click to Play");

        // add to panel
        p.add(b1);
        p.add(lab);


        p.setVisible(true);
        f.getContentPane().add(p);

    }

    public static void main(String[] args) {
        new Test();

    }
}
