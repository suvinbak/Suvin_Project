package RhythmGame;

import javax.swing.*;
import java.awt.*;

public class RhythmGame_beat extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;

    private Image introBackGround;

    public RhythmGame_beat(){
        setTitle("Dynamic Beat");
        setSize(RhythmGame_main.SCREEN_WIDTH, RhythmGame_main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        introBackGround = new ImageIcon("/Users/apple/Documents/Suvin_Project/src/main/resources/RhythmGame_image/music-g048cb599c_1280.jpg").getImage();
    }

    public void paint(Graphics g){
        screenImage = createImage(RhythmGame_main.SCREEN_WIDTH, RhythmGame_main.SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics g){
        g.drawImage(introBackGround, 0, 0, null);
        this.repaint();
    }
}
