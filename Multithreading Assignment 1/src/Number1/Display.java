package Number1;

import java.util.Random;

public class Display extends Thread{

    private Controller controller;
    private boolean threading = false;
    public Display(Controller controller) {
        this.controller = controller;
        start();
    }

    public synchronized void startThread(){
        if(!threading){
            threading = true;
        }
    }
    public void stopThread() {
        if(threading){
            threading = false;
        }
    }

    @Override
    public void run() {
        Random rng = new Random();

        int y, x;
        while(true){


            try{
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(threading){
                x = rng.nextInt(222);
                y = rng.nextInt(269);
                controller.randomTextInit("SOOO FAAAST",x,y);
            }
        }
    }
}