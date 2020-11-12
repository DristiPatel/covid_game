package model;
import java.util.Random;

public abstract class Person {
    public boolean attacked;
    public boolean infected;
    public boolean mask;
    public boolean vulnerable;
    public double walkSpeed;
    public String image;
    public String profile;
    public int x, y;
    
    public Person(int x, int y, boolean mask, boolean vulnerable){
        this.x = x;
        this.y = y;
        this.mask = mask;
        this.vulnerable = vulnerable;
        this.attacked = false;
    }


    public void walk() {
       //make it walk through animation
    }

    public void cough() {
        // "cough" on a nearby Person
        
    }

    public void changeMask(boolean mask){
        // put a mask on or take it off, depending on previous state
        mask = !mask;
        updateImage();

    }

    public void setImage(String filename) {
        image = filename;
        // insert code below to draw the image at x, y
    }

    public void updateImage() {
        // update the position of the image of person based on the x and y positions of the Person
        // if (mask && child) image = "childMask.jpeg" 
        // if (!mask && child) image = "childNoMask.jpeg"
        // if (mask && adult) image = "adultMask.jpeg" 
        // if (!mask && adult) image = "adultNoMask.jpeg"
        // if (mask && elderly) image = "elderlyMask.jpeg" 
        // if (!mask && elderly) image = "elderlyNoMask.jpeg"
        setImage(image);
    }

    public void setProfile(String filename) {
        profile = filename;
    }

    public void showProfile() {
        // check if the position of the molecule is "within 6 feet"
        // if it is within 6 feet, show the profile image above the person
        // add code here to draw it at the necessary position
    }

    public void infect(boolean infected) {
        // if this person is coughed on and elderly, vulnerable, or without a mask, change infected to true
    }

    public void updatePosition(int x, int y) {
        Random random = new Random();

        boolean right = random.nextBoolean();
        if (right) x += 1;
        else x -= 1;

        boolean forward = random.nextBoolean();
        if (forward) y += 1;
        else y -= 1;

        setImage(image);
    }

    public void attemptInfection() {
        if (attacked) {
            if (!mask) infected = true;
            if (vulnerable) infected = true;
        }
    }

    // return whether or not the person is wearing a mask
    public boolean getMaskState() {return mask;}

    // return infection state 
    public boolean getInfected() {return infected;}

    // get whether or not the person is "vulnerable" (has underlying conditions)
    public boolean getVulnerable() {return vulnerable;}

    // get the walkspeed of the person
    public double getWalkSpeed() {return walkSpeed;}

    // get the image of the person
    public String image() {return image;}

    // get the profile of the person
    public String profile() {return profile;}

    // return the x coordinate
    public double getX() {return x;}

    // return the y coordinate
    public double getY() {return y;}



    
    // public void withinSixFeet() {}

    
}
