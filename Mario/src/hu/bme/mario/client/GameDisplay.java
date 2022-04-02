package hu.bme.mario.client;

import hu.bme.mario.model.Block;
import hu.bme.mario.model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameDisplay extends JPanel {
    private final double marginX = 10;
    private final double displayHeightInBlock = 10;
    private final double scrollRatioBackground = 0.1;

    private BufferedImage buf;

    private BlockTextureCache blockTextures;
    private BufferedImage background;

    private double cameraX;

    public GameDisplay(){
        this.blockTextures = new BlockTextureCache();
        this.blockTextures.loadTextures();
        this.cameraX = 0;

        this.buf = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);

        try {
            this.background = ImageIO.read(new File("textures/background.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void forceCameraX(double cx){
        this.cameraX = cx;
    }

    public void displayGame(Game game){
        paintBackground();
        paintBlocks(game.getMap());
        this.repaint();
    }

    protected void paintComponent(Graphics g){
        Rectangle r = g.getClipBounds();
        if(this.buf.getWidth()!=r.width || this.buf.getHeight()!=r.height){
            this.buf = new BufferedImage(r.width, r.height, BufferedImage.TYPE_3BYTE_BGR);
        }


        g.drawImage(this.buf, 0, 0, this.buf.getWidth(), this.buf.getHeight(), null);
        //System.out.println(System.currentTimeMillis());


    }

    private void paintBackground(){

        double pixPerBlock = this.buf.getHeight()/this.displayHeightInBlock;

        double ratio = (double)this.buf.getHeight()/background.getHeight();
        int backgroundPictureWidth = (int)(ratio*background.getWidth());

        int multipleBackground = 2 + this.buf.getWidth()/backgroundPictureWidth;

        int xBegin = -((int)(cameraX*scrollRatioBackground*pixPerBlock))%backgroundPictureWidth;

        Graphics g = this.buf.getGraphics();

        for(int i=0;i<multipleBackground;i++) {
            g.drawImage(background, xBegin + i*backgroundPictureWidth, 0, backgroundPictureWidth, this.buf.getHeight(), null);
        }
    }

    private void paintBlocks(Block[][] map){
        for(int x=0;x<map.length;x++){
            for(int y=0;y<map[x].length;y++){
                if(map[x][map[x].length-y-1]!=null){
                    paintBlock(map[x][map[x].length-y-1], x, y);
                }
            }
        }
    }
    private void paintBlock(Block b, int x, int y){
        Image texture = this.blockTextures.get(b.getClass());
        int pixPerBlock = (int)(this.buf.getHeight()/this.displayHeightInBlock);

        this.buf.getGraphics().drawImage(texture, (int)((x-this.cameraX)*pixPerBlock), y*pixPerBlock, pixPerBlock, pixPerBlock, null);
    }
}
