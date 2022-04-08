package Number3;

import java.util.ArrayList;

public class Consumer extends Thread {

    private double currentVolume;
    private double currentWeight;
    private int currentItems;
    private Threading threading;

    private ArrayList<FoodItem> consumerList;
    private boolean continueCheck = false;
    private String consumerName;
    private Thread consThread;

    private MaxLimits maxLimits = new MaxLimits(100, 100, 100);

    public Consumer(Threading threading, String consumerName, MaxLimits maxLimits) {
        this.threading = threading;
        this.consumerName = consumerName;
        this.maxLimits = maxLimits;



    }

    public synchronized void startCons(){

        if(continueCheck == false){
            continueCheck = true;
            start();
        }
    }
    public void stopCons() {
        if(continueCheck == true){
            continueCheck = false;
        }
    }


    @Override
    public void run() {

        while (true) {

            try{
                Thread.sleep(2000);

                FoodItem fooditem = threading.takeItemFromBuffer();
                consumerList = new ArrayList<FoodItem>();
                while (currentItems < maxLimits.getMaxItems()
                        || (currentWeight < maxLimits.getMaxWeight())
                        || (currentVolume < maxLimits.getMaxVolume())) {

                    synchronized (this) {

                        while (continueCheck = true) {
                            Thread.sleep(2000);


                            //Managing the integers for each item
                            if(fooditem != null) {
                                consumerList.add(fooditem);
                                currentWeight += fooditem.getWeight();
                                currentVolume += fooditem.getVolume();
                                fooditem.getName();
                                currentItems++;
                                System.out.println(currentItems + "Current items consumed");
                                System.out.println(fooditem.getName());
                                threading.lblHandler(Thread.currentThread().getName(), currentItems, currentVolume, currentVolume, fooditem.getName());
                            }
                        }
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            consumerList.clear();
            currentVolume = 0;
            currentItems = 0;
            currentWeight = 0;

        }
    }
}
