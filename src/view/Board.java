package view;

import java.awt.*;
import javax.swing.*;

import driver.Driver;

public class Board extends JPanel{

    // FIELDS
    private Dimension dimOff;
	private Image imgOff;
    private Graphics grpOff;
    
	private GameFrame gmf; // they called gameframe with this but we don't have one
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
        if (grpOff == null || Driver.DIM.width != dimOff.width
				|| Driver.DIM.height != dimOff.height) {
			dimOff = Driver.DIM;
			imgOff = createImage(Driver.DIM.width, Driver.DIM.height);
			grpOff = imgOff.getGraphics();
		}
        //draw the background for entire program
        grpOff.setColor(Color.WHITE);
		grpOff.fillRect(0, 0, Driver.DIM.width, Driver.DIM.height);
        
        //demo
        drawMenuScreen(g);


        //draw the buffer image
        g.drawImage(imgOff, 0, 0, this);

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
        grpOff.setColor(Color.BLACK);
        grpOff.fillRect(25, 25, Driver.DIM.width - 75, Driver.DIM.height - 100);

        grpOff.setColor(Color.RED);
        grpOff.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 36));
        grpOff.drawString("COVID-19 GAME", Driver.DIM.width/2 - 200, Driver.DIM.height/2);

        //draw tutorial and start button from control
        
    }

    private void drawTutorial(){

    }

    private void drawGame(){

    }

}
