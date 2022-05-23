package hu.bme.mario.model;

public class ModelThread extends Thread{
    protected Game game;

    public ModelThread(Game g){
        super();
        this.game = g;
    }

    public Game getGame(){
        return this.game;
    }

    public void setGame(Game g){
        this.game = g;
    }

    public void run(){
        double fps = 100;
        long tps = (long)(1000/fps);

        while(true) {
            try{
                long l = System.currentTimeMillis()+tps;
                synchronized (this.game){
                    this.game.update((double)tps/1000);
                }
                try {
                    Thread.sleep(Math.max(0,l-System.currentTimeMillis()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

}
