package StarFighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing {

    private int speed;
    private boolean spent;

    public Ammo() {
        this(0, 0, 0);
        spent = false;
    }

    public Ammo(int x, int y) {
        this(x,y,0);
        spent = false;
    }

    public Ammo(int x, int y, int s) {
        super(x,y, 10, 10);
        speed = s;
        spent = false;
    }

    public void setSpeed(int s) {
        speed = s;
    }
    public boolean isSpent(){
        return spent;
    }
    public void used(){
        spent = true;
    }

    public int getSpeed() {
        return speed;
    }

    public void draw(Graphics window) {
        window.fillRect(this.getX(), this.getY(), this.getWidth(),this.getHeight());
    }

    public void move(String direction) {
        if(direction.toLowerCase().equals("up")){
            setY(getY() - speed);
        }
        if(direction.toLowerCase().equals("down")){
            setY(getY() + speed);
        }
        if(direction.toLowerCase().equals("left")){
            setX(getX() - speed);
        }
        if(direction.toLowerCase().equals("right")){
            setX(getX() + speed);
        }
    }

    public String toString() {
        return super.toString() + " " + speed;
    }
}
