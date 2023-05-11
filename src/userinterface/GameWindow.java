package userinterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {

    private GameScreen gameScreen;
    public GameWindow() throws IOException {
        super("Java T-Rex game");
        setSize(600, 200    );
        setLocation(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }
    public void startGame(){
        gameScreen.startGame();
    }
    public static void main(String arg[]) throws IOException {
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }

}
