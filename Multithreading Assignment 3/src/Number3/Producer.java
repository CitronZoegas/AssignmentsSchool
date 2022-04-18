package Number3;

import java.util.Random;

public class Producer extends Thread {


    private Threading threading;
    private String name;
    private FoodItem[] foodItems;
    private Thread prodThread;


    private boolean producerCheck = false;

    public Producer(Threading threading, String name, FoodItem[] foodItems) {
        this.threading = threading;
        this.foodItems = foodItems;
        this.name = name;
        start();
    }
    public void startProd(){

        if(!producerCheck){
            producerCheck = true;
        }
    }
    public void stopProd() {
        if(producerCheck){
            producerCheck = false;
        }
    }
    public void randomItemToBuffer(FoodItem[] defaultFoodItems) throws InterruptedException {
        Random rng = new Random();
        System.out.println(rng);
        int i = rng.nextInt(defaultFoodItems.length);
        threading.prodItemToBuffer(defaultFoodItems[i]);
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(producerCheck){
                try{
                    randomItemToBuffer(foodItems);//Callar på threading med ett index till ett slumpvald föremål från "randomItem" metoden i FoodItem listan
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}