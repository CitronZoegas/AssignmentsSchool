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
        setName(consumerName);
        start();
    }

    public synchronized void startCons(){
        continueCheck = true;

    }
    public void stopCons() {
        continueCheck = false;
    }
    @Override
    public void run() {
        while (true) {

            try{
                Thread.sleep(2000);

                consumerList = new ArrayList<>();
                while      (currentItems < maxLimits.getMaxItems()
                        || (currentWeight < maxLimits.getMaxWeight())
                        || (currentVolume < maxLimits.getMaxVolume())) {


                    synchronized (this) {

                        while (continueCheck) {
                            Thread.sleep(2000);
                            FoodItem fooditem = threading.takeItemFromBuffer();

                            //Managing the integers for each item
                            if(fooditem != null) {
                                consumerList.add(fooditem);
                                currentWeight += fooditem.getWeight();
                                currentVolume += fooditem.getVolume();
                                currentItems++;
                                //System.out.println(currentItems + " Current items consumed");
                                //System.out.println(fooditem.getName());
                                threading.lblHandler(getName(), currentItems, currentVolume, currentVolume, fooditem.getName());
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
