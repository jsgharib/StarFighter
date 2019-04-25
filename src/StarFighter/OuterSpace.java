package StarFighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

    private Ship ship;
    private Alien alienOne;
    private Alien alienTwo;
    private AlienHorde horde; 
    private Bullets shots;
    private boolean[] keys;
    private BufferedImage back;
    private int time;

    public OuterSpace() {
        setBackground(Color.black);
         keys = new boolean[5];
         horde = new AlienHorde(20);
         ship = new Ship(400,300,50,50,5);
         shots = new Bullets();
         time = 0;
         for(int i = 0; i < 21; i++){
             if(i >13){
               horde.add(new Alien(70 + 100*(i-14) , -190, 60,50,1));
             }
             else if(i > 6){
               horde.add(new Alien(70 + 100*(i-7) , -90, 60,50,1));
             }
             else{
                horde.add(new Alien(70 + 100*i , 10, 60,50,1));
             }
         }
        this.addKeyListener(this);
        new Thread(this).start();
        
        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
         Graphics2D twoDGraph = (Graphics2D) window;
         time ++;
         if (back == null) {
             back = (BufferedImage) (createImage(getWidth(), getHeight()));
         }
        
         //create a graphics reference to the back ground image
         //we will draw all changes on the background image
         Graphics graphToBack = back.createGraphics();
         graphToBack.setColor(Color.BLACK);
         graphToBack.fillRect(0, 0, 800, 600);
         graphToBack.setColor(Color.BLUE);
         graphToBack.drawString("StarFighter : The Game ", 25, 50);
         ship.draw(graphToBack);
         if (keys[0] == true && ship.getX() > 0) {
             ship.move("left");
         }
         if (keys[1] == true && ship.getX() + ship.getWidth() < getWidth()) {
             ship.move("right");
         }
         if (keys[2] == true && ship.getY() > 0) {
             ship.move("up");
         }
         if (keys[3] == true  && ship.getY() + ship.getHeight() < getHeight()) {
             ship.move("down");
         }
         if(keys[4] == true && time > 25){
             time = 0;
             shots.add(new Ammo((int)(ship.getX()+(ship.getWidth()/2)-5), ship.getY(), 3));
         }
         ship.draw(graphToBack);
         shots.drawEmAll(graphToBack);
         shots.moveEmAll();
         horde.drawAll(graphToBack);
         horde.moveAll();
         alienHit();
        twoDGraph.drawImage(back, null, 0, 0);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
    }
    
    public void alienHit(){
        for(Alien al : horde.getList()){
            for(Ammo s : shots.getList()){
                if(s.getX() >= al.getX() && s.getX() + s.getWidth() <= al.getX() + al.getWidth() && s.getY() < al.getY() + al.getHeight() && s.getY() + s.getHeight() > al.getY()){
                    al.setHit();
                    s.used();
                }
            }
        }
        horde.removeDeadOnes();
        shots.cleanEmUp();
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}
