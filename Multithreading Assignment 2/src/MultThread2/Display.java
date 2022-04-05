package MultThread2;

import java.io.IOException;
import java.util.Random;

public class Display extends Thread {

    private Controller controller;

    private boolean threading = false;
    private int x;
    private int y;

    private int xPlusTwo;
    private int yPlusTwo;
    private GUIAssignment2 gui;
    public Display(Controller controller, int x, int y,int xPlusTwo, int yPlusTwo) throws IOException {
        this.controller = controller;
        this.x = x;
        this.y = y;
        this.xPlusTwo = xPlusTwo;
        this.yPlusTwo = yPlusTwo;
        gui = new GUIAssignment2(controller);
    }
    public void removeTextField() {


    }


    public void update() {

        x += xPlusTwo;
        y += yPlusTwo;
        //gui.updateTextField(x,y);
    }
    @Override
    public void run() {
        gui.removeTextField(currentThread());
        Random rng = new Random();

        while(true) {
            gui.repaintGUI();
            System.out.println("repainted");
            gui.updateGUI();
            System.out.println("updated");
            //x = bredden av skärmen, alla trådar skaffar ett slumpvalt nummer mellan 0-800
            //y = toppen av skärmen
            x = rng.nextInt(800);
            y = 10;
            WordBox wordB = new WordBox(controller, "Multithreading Assignment 2/resources/Random words.txt", null);
            try {
                Thread.sleep(1000);
                //ger täxtfältet sitt ord och ger den X och Y värden.

                controller.randomTextInit(wordB.getRandomWord(), x , y+yPlusTwo);
                System.out.println("created thread" + currentThread().getName());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            /*while (y <= 800) {

                this.y = yPlusTwo;
                try {
                    Thread.sleep(1000);
                    controller.randomTextInit(wordB.getRandomWord(), x, y);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                yPlusTwo = yPlusTwo + 25;
            }*/
        }
    }
}