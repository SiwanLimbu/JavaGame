package objectgame;

import util.Animation;
import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static userinterface.GameScreen.GRAVITY;
import static userinterface.GameScreen.GROUNDY;

public class MainCharacter {
    private float x = 0;
    private float y = 0;


    private float speedY = 0;

    private Animation characterRun;
    private Rectangle rect;
    private  boolean isAlive = true;



    public MainCharacter() throws IOException {
        characterRun = new Animation(60);
        characterRun.addFrame(Resource.getResourceImage("data/main-character1.png"));
//        characterImage = Resource.getResourceImage("data/main-character1.png");
//        adding another frame here
        characterRun.addFrame(Resource.getResourceImage("data/main-character2.png"));
        rect = new Rectangle();

    }
    public void update(){
        characterRun.update();
//        This code for jumping
        if(y>=GROUNDY - characterRun.getFrame().getHeight()){
            speedY = 1000;
            y = GROUNDY - characterRun.getFrame().getHeight();
        }else{
            speedY+=GRAVITY;
            y+=speedY;
        }
        rect.x = (int) x;
        rect.y = (int) y;
        rect.width = characterRun.getFrame().getWidth();
        rect.height = characterRun.getFrame().getHeight();
    }

    public Rectangle getBound(){
        return rect;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
//        g.drawLine(0, (int)GROUNDY, getWidth(),(int)GROUNDY);

//        g.drawRect((int) x, (int) y , characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
        g.drawImage(characterRun.getFrame(), (int) x, (int) y, null);
    }

    public void jump(){
        speedY = -5;
        y += speedY;
    }


    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setAlive(boolean alive){
        isAlive = alive;
    }

    public boolean getAlive(){
        return isAlive;
    }
}
