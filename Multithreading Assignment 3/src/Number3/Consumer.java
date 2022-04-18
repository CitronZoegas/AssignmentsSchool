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



    private MaxLimits maxLimits;// = new MaxLimits(100, 100, 100);

    public Consumer(Threading threading, String consumerName, MaxLimits maxLimits) {
        this.consumerList = new ArrayList<>();
        this.threading = threading;
        this.consumerName = consumerName;
        this.maxLimits = maxLimits;
        //setName(consumerName);
        start();
    }

    /**
     * Sets the boolean variable which lets the program run to "true"
     */
    public void startCons(){
        continueCheck = true;

    }

    /**
     * Sets the boolean variable which lets the program run to "false"
     */
    public void stopCons() {
        continueCheck = false;
    }
    @Override
    public void run() {
        while (true) {
            try{
                Thread.sleep(2000);
                //Creating the arrayList
                consumerList = new ArrayList<>();
            }catch (Exception e) {
                e.printStackTrace();
            }
                //Checking if all the values are below the maximum limit, making the while loop continue
                while      (currentItems < maxLimits.getMaxItems()
                        || (currentWeight < maxLimits.getMaxWeight())
                        || (currentVolume < maxLimits.getMaxVolume())) {

                        synchronized (this){


                        while (continueCheck) {
                            try{
                                Thread.sleep(2000);
                                FoodItem fooditem = threading.takeItemFromBuffer();

                                //Managing the integers/doubles for each item
                                if(fooditem != null) {
                                    consumerList.add(fooditem);
                                    currentWeight += fooditem.getWeight();
                                    currentVolume += fooditem.getVolume();
                                    currentItems++;
                                    threading.lblHandler(Thread.currentThread().getName(), currentItems, currentVolume, currentWeight, fooditem.getName());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            consumerList.clear();
            currentVolume = 0;
            currentItems = 0;
            currentWeight = 0;

        }
    }
}
