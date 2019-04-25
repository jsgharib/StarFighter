package StarFighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {

    private List<Alien> aliens;

    public AlienHorde(int size) {
        aliens = new ArrayList<Alien>(size);
    }

    public void add(Alien al) {
        aliens.add(al);
    }

    public void drawAll(Graphics window) {
        for(Alien al : aliens){
            al.draw(window);
        }
    }

    public void moveAll() {
        for(Alien al: aliens){
            if(al.getX() + al.getWidth() > 800){
                int speedTemp = al.getSpeed();
                al.setSpeed(0);
                al.setY(al.getY() + al.getHeight());
                al.setX(800 - al.getWidth());
                al.setSpeed(-speedTemp);
            }
            else if(al.getX() < 0){
                int speedTemp = al.getSpeed();
                al.setSpeed(0);
                al.setY(al.getY() + al.getHeight());
                al.setX(0);
                al.setSpeed(-speedTemp);
            }
            else if(al.getY() + al.getHeight()> 600){
                al.setY(0);
            }
            else{
                al.setX(al.getX() + al.getSpeed());
            }
        }
    }
    
    public List<Alien> getList(){
        return aliens;
    }

    public void removeDeadOnes() {
        for(int i = 0; i < aliens.size(); i++){
            if (aliens.get(i).getHit()){
                aliens.remove(i);
            }
        }
    }

    public String toString() {
         String longboi = "";
        for(Alien al : aliens){
            longboi += al.toString() + "\t";
        }
        return longboi;
    }
}
