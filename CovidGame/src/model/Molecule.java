package model;

public class Molecule {
    public double x = 0; // starting x coordinate
    public double y = 0; // starting y coordinate
    public int v = 1; // velocity of molecule if launched
    public String image; // image for the character design chosen

    public void updateImage(String filename) {
        image = filename;
        // insert code for drawing this image at the points x, y
        // update the image for every loop of the animation, everytime x and y is updated
    }

    public void updatePosition(double xCharacter, double yCharacter) {
        // update the position that their character image is drawn at based on the keyboard input
        // basically redraw their image at the new point fed in
        this.x = xCharacter;
        this.y = yCharacter;
    }

    public String getImage() {return image;}

}
