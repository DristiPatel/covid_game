package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import driver.Driver;

public class Board extends JPanel{

    // DOUBLE BUFFERED FIELDS
    private Dimension dimOff;
	private Image imgStaticOff;
    private Graphics grpStaticOff;
    private Image imgMovOff;
    private Graphics grpMovOff;
    
    //GAME FRAME
    public GameFrame gmf; // they called gameframe with this but we don't have one
    
    //BUTTONS
    private JButton bStart; // start button
    private JLabel lStart; // start label

    //FONT SETUP
	public static Font fnt = new Font("Times", Font.BOLD, 20);  // set font size.
    public static Font fntBig = new Font("Times", Font.BOLD + Font.ITALIC, 36);
    private FontMetrics fmt; 
	private int nFontWidth;
	private int nFontHeight;
    private String strDisplay = "";
    

    //CONSTRUCTOR
    public Board(Dimension dim){
        System.out.println("Creating Board...");

        gmf = new GameFrame();
        gmf.getContentPane().add(this);
        gmf.pack();
        initView();

        gmf.setSize(dim);
        gmf.setTitle("Covid Game uwu");
		gmf.setResizable(false);
		gmf.setVisible(true);
		this.setFocusable(true);
    }

    //method to initialize graphics parameters
    private void initView() {
        Graphics g = getGraphics();			// get the graphics context for the panel
		g.setFont(fnt);						// take care of some simple font stuff
		fmt = g.getFontMetrics();
		nFontWidth = fmt.getMaxAdvance();
		nFontHeight = fmt.getHeight();
        g.setFont(fntBig);					// take care of some simple font stuff

	}

    //updates all the games graphics based on state of game
    public void update(Graphics g){

        System.out.println("Updating...");
        //TODO: add code to change dimensions if we make resizable
        
        
        //check if game state has changed, update background
        if (Driver.PREV_GAME_STATE != Driver.CUR_GAME_STATE){
            
            imgStaticOff = createImage(Driver.DIM.width, Driver.DIM.height);

            //change background
            switch(Driver.CUR_GAME_STATE){
                case START_MENU:
                    drawMenuScreen(grpStaticOff);       
                    break;
                case TUTORIAL:
                    drawTutorial(grpStaticOff);
                    break;
                case GAME:
                    drawGameBackground(grpStaticOff);
                    break;
                case END_SCREEN:
                    drawEndScreen(grpStaticOff);
                case NULL:
                    break;       
            }
            g.drawImage(imgStaticOff, 0, 0, this);
        }

        if (Driver.CUR_GAME_STATE == Driver.gameStates.GAME){
            //update the graphics of the game
            //draw people/virus here, draw the already generated array of people objects
            
            //bring in array of movable objects
            
            //get updated internal values to draw 
        }    
        
        
        


        //draw the buffer image on the actual graphics context
    

    }

     // draw the score of the player
    /*
    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(fnt);
        // we have to create a public variable for score somewhere lmao
        if (score) g.drawString("SCORE :  " + score, nFontWidth, nFontHeight);
        
    }
    */

    private void drawMenuScreen(Graphics g){

        System.out.println("Drawing Menu Screen...");
        BufferedImage blueBackground;
        try{
            blueBackground = ImageIO.read(new File("src/view/images/BlueBackground.jpeg"));
            grpStaticOff.drawImage(blueBackground, 0, 0, 1200, 800, this);
        }catch (IOException e){
            System.out.println("Error loading image");
        }

        bStart = new JButton();
        lStart = new JLabel();
        

    
    
        /*
        grpOff.setColor(Color.BLACK);
        grpOff.fillRect(25, 25, Driver.DIM.width - 75, Driver.DIM.height - 100);
        grpOff.setColor(Color.RED);
        grpOff.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 36));
        grpOff.drawString("COVID-19 GAME", Driver.DIM.width/2 - 200, Driver.DIM.height/2);
        */
        //draw tutorial and start button from control
        
    }

    private void drawTutorial(Graphics g){

    }

    public void drawGameBackground(Graphics g){
        BufferedImage blueBackground;
        try{
            blueBackground = ImageIO.read(new File("src/view/images/BlueBackground.jpeg"));
            grpOff.drawImage(blueBackground, 0, 0, 1200, 800, this);
        }catch (IOException e){
            System.out.println("Error loading image");
        }
    }

    public void drawEndScreen(Graphics g) {
        //TODO
    }

}
