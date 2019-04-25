package StarFighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {

    private int speed;
    private Image image;
    private boolean isHit;

    public Alien() {
        this(0, 0, 30, 30, 0);
        isHit = false;
    }

    public Alien(int x, int y) {
        this(x, y, 30, 30, 0);
        isHit = false;
    }

    public Alien(int x, int y, int s) {
        this(x, y, 30, 30, s);
        isHit = false;
    }

    public Alien(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
        isHit = false;
        try {
            URL url = getClass().getResource("/images/alien.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
        }
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setHit(){
         isHit = true;
    }
    
    public Boolean getHit(){
        return isHit;
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

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public String toString() {
        return super.toString() + " " + getSpeed();
    }
}
