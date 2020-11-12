package driver;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import java.util.*;
import java.awt.Dimension;
import java.util.Random;
import model.*;
import view.*;

public class Driver implements Runnable, MouseMotionListener, MouseListener, KeyListener {
    public final static int SCREEN_WIDTH = 1200;
    public final static int SCREEN_HEIGHT = 800;

	public static final Dimension DIM = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT); //the dimension of the game.
    private Board board;
	public static Random R = new Random();
	public final static int ANI_DELAY = 45; // milliseconds between screen
											// updates (animation)
	private Thread thrAnim;
	private int nLevel = 1;
	private static int nTick = 0;
	private boolean bMuted = true;
    public int score = 0;

    public Molecule character; // character playing the game
    public double xCharacter; // x coordinate of the character
    public double yCharacter; // y coordinate of the character
    public int numberOfPeople; // number of people objects
    public int numberInfected = 0; // number of people infected
    public Adult[] adults; // array of adults
    public Child[] children; // array of children
    public Elderly[] elderly; // array of elderly
    public Boolean[] withinSixFeet; // array storing who is within six feet
    public Person[] people; // array of all the people

    /* CONSTRUCTOR */
    public Driver() throws IOException{
        //put in music?

        System.out.println("Initializing Game...");


        //initialize board and event listeners
        board = new Board(DIM);
		board.addKeyListener(this);
        board.addMouseListener(this);
        board.addMouseMotionListener(this); 
 
        System.out.println("Finished initializing listeners...");    
    }


    /*MAIN METHOD */
    public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() { // uses the Event dispatch thread from Java 5 (refactored)
            public void run() {
                try {
                    Driver game = new Driver(); // construct itself
                    game.initUpdateThread();
                  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}

    private void initUpdateThread(){
        System.out.println("Initializing Graphics Thread...");
        if (thrAnim == null) {
			thrAnim = new Thread(this); // pass the thread a runnable object (this)
			thrAnim.start();
		}
    }
    
    public void run() {
        while (Thread.currentThread() == thrAnim) {
            board.update(board.getGraphics());
            //System.out.println(board.isVisible());
        }

    }
    // increments score upon successful infection
    public void scoreIncrement() {
        score += 2; 
    }

    // decrements score upon failed, but attempted, infection
    public void scoreDecrement() {
        score--;
    }

    // returns the current score
    public int getScore() {return score;}


    // generate the character of the player
    public void generateCharacter(String filename) {
        // the filename is a jpeg image corresponding to the COVID-19 character they picked

    }
    // generate the start conditions in level, number of people corresponding to difficulty chosen
    public void startLevel(int numberOfPeople) {
        // generate people objects with random coordinates and conditions
        Random random = new Random();
        this.numberOfPeople = numberOfPeople;
        // initalize person array
        people = new Person[numberOfPeople];

        // initalize adults array
        adults = new Adult[numberOfPeople/3];
        for(int i = 0; i < numberOfPeople/3; i++) {
            int x = random.nextInt(1200); // generate random x coordinate
            int y = random.nextInt(800);// generate random y coordinate
            boolean mask = random.nextBoolean();// generate random boolean
            boolean vulnerable = random.nextBoolean(); // generate random boolean
            adults[i] = new Adult(x, y, mask, vulnerable); // initialize new adult object for each index of array
            people[i] = adults[i]; // fill people array
        }

        // initalize children array
        children = new Child[numberOfPeople/3];
        for(int i = 0; i < numberOfPeople/3; i++) {
            int x = random.nextInt(1200); // generate random x coordinate
            int y = random.nextInt(800);// generate random y coordinate
            boolean mask = random.nextBoolean();// generate random boolean
            boolean vulnerable = false; // no children are vulnerable
            children[i] = new Child(x, y, mask, vulnerable); // initialize new child object for each index of array
            people[i + numberOfPeople/3] = children[i]; // fill people array
        }

        // initalize elderly array
        elderly = new Elderly[numberOfPeople/3];
        for(int i = 0; i < numberOfPeople/3; i++) {
            int x = random.nextInt(1200); // generate random x coordinate
            int y = random.nextInt(800);// generate random y coordinate
            boolean mask = random.nextBoolean();// generate random boolean
            boolean vulnerable = true; // all elderly are vulnerable
            elderly[i] = new Elderly(x, y, mask, vulnerable); // initialize new elderly object for each index of array
            people[i + 2*(numberOfPeople/3)] = elderly[i]; // fill people array
        }

        // draw them on the board in their positions, somehow
        // insert code for this here
    }

    // gets the number of people
    public int getnumberOfPeople() {return numberOfPeople;}

    // initializes the molecule character at the starting position, with the filename of the image
    // corresponding to the character design that the player picked
    public void characterStart(String filename) {
        character = new Molecule();
        character.updateImage(filename);
        xCharacter = character.x;
        yCharacter = character.y;

    }
    // moves left, call this if keyboard input is left arrow key
    // feed in current position of molecule
    public void moveLeft() {
        character.updatePosition(xCharacter-1, yCharacter);
    }
    
    // moves right, call if keyboard input is right arrow key
    public void moveRight() {
        character.updatePosition(xCharacter+1, yCharacter);
    }
    
    // moves forward, call if keyboard input is up arrow key
    public void moveForward() {
        character.updatePosition(xCharacter, yCharacter+1);
    }

    // moves forward, call if keyboard input is up arrow key
    public void moveBackward() {
        character.updatePosition(xCharacter, yCharacter-1);
    }

    // check if there are any people objects within a six-foot radius
    // 6 feet (for now) defined as 6mm, which is 22.62 pixels
    public void checkWithinSixFeet() {
        // initalizes the boolean array
        withinSixFeet = new Boolean[numberOfPeople];
        // checks that square of the distance between the Person and character is less than 511.66, the square of 22.62 (6 feet)
        double distanceSquareNeeded = 511.66; 
        for(int i = 0; i < numberOfPeople; i++) {
            int xPerson = people[i].x;
            int yPerson = people[i].y;
            double distanceSquared = Math.pow(xPerson-xCharacter, 2) + Math.pow(yPerson-yCharacter, 2);
            if (distanceSquared <= distanceSquareNeeded) withinSixFeet[i] = true; 
        }
    }
    
    // things that occur if the character is within 6 "feet" of a person
    // 6 feet (for now) defined as 6mm, which is 22.62 pixels
    // 
    public void actionsWithinSixFeet() {
        // show the profile of each person within six feet
        for(int i = 0; i < numberOfPeople; i++) {
            if (withinSixFeet[i]) people[i].showProfile();
        }
    }

    // call this method if the mouse has clicked at a location to launch
    // animate the molecule (by erasing and redrawing position) on the trajectory chosen
    public void launch(double x, double y) {
        int xFinal = (int) x;
        int yFinal = (int) y;
        double deltaX = (xFinal - xCharacter)/100;
        double deltaY = (yFinal - yCharacter)/100;
        // while (xCharacter != xFinal) {
            // erase old character image (insert code)
            // update position
        xCharacter += deltaX;
        yCharacter += deltaY;
        character.updatePosition(xCharacter, yCharacter);
            // draw new character image at the specified position
            // pause for time step 
    }

    // call this method after a molecule has been launched, feed in the keyboard click coordinates
    public boolean checkIfPersonHit(double x, double y) {
        boolean hit = false;
        for(int i = 0; i < numberOfPeople; i++) {
            if ((people[i].x == xCharacter) && (people[i].y == yCharacter)) {
                people[i].attacked = true;
                people[i].attemptInfection();
                hit = true;
            }
        }
        return hit;
    }

    // call this method if person hit (if hit was true)
    public void checkNewInfection() {
        numberInfected = 0;
        for(int i = 0; i < numberOfPeople; i++) {
            if (people[i].infected) numberInfected++;
        }
    }
    
    

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
