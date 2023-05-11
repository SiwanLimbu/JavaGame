package userinterface;

import objectgame.*;
import util.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameScreen extends JPanel implements Runnable, KeyListener {

    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;




    public static final float GRAVITY = 0.2f;
    public static final float GROUNDY = 110; //by pixel

    private MainCharacter mainCharacter;


    private Thread thread;
    private Land land;
     private Clounds clouds;
     private EnemiesManager enemiesManager;
     private int score;

     private int gameState = GAME_FIRST_STATE;

     private BufferedImage imageGameOverText;

    public GameScreen() throws IOException {
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        land = new Land(this);
        clouds = new Clounds();
        enemiesManager = new EnemiesManager(mainCharacter, this);
        imageGameOverText = Resource.getResourceImage("data/gameover_text.png");


    }

    public void startGame(){
        thread.start();
    }
    @Override
    public void run() {
        while(true) {
//            System.out.println(i ++);
            try {
//               x+=1;
                  update();

                repaint();
                Thread.sleep(20);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() throws IOException {
        switch(gameState){
            case GAME_PLAY_STATE:
            mainCharacter.update();
            land.update();
            clouds.update();
            enemiesManager.update();
            if(!mainCharacter.getAlive()){
                gameState = GAME_OVER_STATE;
            }
            break;
        }

    }
    public void plusScore(int score){
        this.score += score;
    }
    @Override
    public void paint(Graphics g){
//        coordination x, y,width, height of the rectangle
//        super.paint(g);
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0,0,getWidth(),getHeight()); //sabai fill garcha
//        g.setColor(Color.red);
//        g.drawLine(0, (int)GROUNDY, getWidth(),(int)GROUNDY);

        switch(gameState){
            case GAME_FIRST_STATE :
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                g.drawString( "HI " +String.valueOf(score), 500, 20);
                break;
            case GAME_OVER_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesManager.draw(g);
                g.drawImage(imageGameOverText, 100,50,null);
                break;
        }


//        g.drawRect((int) x, (int) y , 100, 100);
    }

    private void resetGame() throws IOException {
        mainCharacter.setAlive(true);
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        enemiesManager.reset();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        mainCharacter.jump();
//        System.out.println("Key pressed");

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Key released");
        switch (e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if(gameState == GAME_FIRST_STATE){
                    gameState = GAME_PLAY_STATE;
                }else if(gameState == GAME_PLAY_STATE){
                    mainCharacter.jump();
                } else if (gameState == GAME_OVER_STATE) {
                    try {
                        resetGame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    gameState = GAME_PLAY_STATE;
                }
                break;
        }
    }

}
