package hu.bme.mario.client;

import hu.bme.mario.model.Block;
import hu.bme.mario.model.Entity;
import hu.bme.mario.model.Game;
import hu.bme.mario.model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameDisplay extends JPanel {
    private final double marginX = 8;
    private final double displayHeightInBlock = 10.5;
    private final double scrollRatioBackground = 0.1;

    private BufferedImage buf;

    private BlockTextureCache blockTextures;
    private EntityTextureCache entityTextures;
    private BufferedImage background;

    private String os;
    private String path;

    private double cameraX;

    public GameDisplay(){
        this.blockTextures = new BlockTextureCache();
        this.blockTextures.loadTextures();
        this.entityTextures = new EntityTextureCache();
        this.entityTextures.loadTextures();
        this.cameraX = 0;

        this.buf = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);

        os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            path = "Mario/textures/background.png";
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            path = "textures/background.png";
        }

        try {
            this.background = ImageIO.read(new File(path));
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
        for(Entity e : game.getEntities()) {
            paintEntity(e);
        }
        recomputeCamera(game);
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
                if(map[x][y]!=null){
                    paintBlock(map[x][y], x, y);
                }
            }
        }
    }
    private void paintBlock(Block b, int x, int y){
        Image texture = this.blockTextures.get(b.getClass()).getTexture();
        int pixPerBlock = (int)(this.buf.getHeight()/this.displayHeightInBlock);

        this.buf.getGraphics().drawImage(texture, (int)((x-this.cameraX)*pixPerBlock), this.buf.getHeight()-(y+1)*pixPerBlock, pixPerBlock, pixPerBlock, null);
    }

    private void paintEntity(Entity e){
        int pixPerBlock = (int)(this.buf.getHeight()/this.displayHeightInBlock);
        EntityTexture et = this.entityTextures.get(e.getClass());

        Image texture = null;

        if(e.isMoving()){
            texture = et.getMovingTexture();
        }else{
            texture = et.getStandingTexture();
        }

        if(e.isLookingLeft()){
            this.buf.getGraphics().drawImage(texture, (int)(pixPerBlock*(e.getX()-this.cameraX))+(int)(pixPerBlock*et.getWidth()), this.buf.getHeight()-((int)(pixPerBlock*e.getY())+(int)(pixPerBlock*et.getHeight())), -(int)(pixPerBlock*et.getWidth()), (int)(pixPerBlock*et.getHeight()), null);

        }else{
            this.buf.getGraphics().drawImage(texture, (int)(pixPerBlock*(e.getX()-this.cameraX)), this.buf.getHeight()-((int)(pixPerBlock*e.getY())+(int)(pixPerBlock*et.getHeight())), (int)(pixPerBlock*et.getWidth()), (int)(pixPerBlock*et.getHeight()), null);
        }
    }

    private void recomputeCamera(Game g){
        Entity centerEntity = g.getEntities().get(0);

        double pixPerBlock = this.buf.getHeight()/this.displayHeightInBlock;

        if(centerEntity.getX()>this.cameraX+(this.buf.getWidth()/pixPerBlock)-this.marginX){
            this.cameraX = Math.min(-(this.buf.getWidth()/pixPerBlock)+this.marginX + centerEntity.getX(), g.getMap().length - this.buf.getWidth()/pixPerBlock);
        }else if(centerEntity.getX()<this.cameraX+this.marginX){
            this.cameraX = Math.max(centerEntity.getX()-this.marginX, 0);
        }


    }
}
