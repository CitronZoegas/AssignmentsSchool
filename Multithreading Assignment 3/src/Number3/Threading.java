package Number3;

public class Threading {

    private Producer PThread1, PThread2, PThread3;

    private Consumer CThread4,CThread5, CThread6;

    private Buffer<FoodItem> foodItemsInStore;

    private FoodItem fooditem;

    private GUI gui;

    private MaxLimits maxLimits = new MaxLimits(100,100,100);

    public Threading() {
        gui = new GUI(this);
        fooditem = new FoodItem(0,0,null);
        foodItemsInStore = new Buffer<>();

        PThread1 = new Producer(this,"SCAN",fooditem.everyFoodItem());
        PThread2 = new Producer(this,"ARLA",fooditem.everyFoodItem());
        PThread3 = new Producer(this,"AXFOOD",fooditem.everyFoodItem());

        CThread4 = new Consumer(this,"ICA", maxLimits);
        CThread5 = new Consumer(this,"COOP", maxLimits);
        CThread6 = new Consumer(this, "CITY GROSS", maxLimits);
    }

    public void startProd(int index) {
        switch (index) {
            case 1 -> PThread1.startProd();
            case 2 -> PThread2.startProd();
            case 3 -> PThread3.startProd();
        }
    }
    public void stopProd(int index) {
        switch (index) {
            case 1 -> PThread1.stopProd();
            case 2 -> PThread2.stopProd();
            case 3 -> PThread3.stopProd();
        }
    }
    public void startCon(int index){
        switch (index) {
            case 1 -> CThread4.startCons();
            case 2 -> CThread5.startCons();
            case 3 -> CThread6.startCons();
        }
    }
    public void stopCon(int index) {
        switch (index) {
            case 1 -> CThread4.stopCons();
            case 2 -> CThread5.stopCons();
            case 3 -> CThread6.stopCons();
        }
    }
    public FoodItem takeItemFromBuffer()  {
        gui.setSizeToBuffer(foodItemsInStore.bufferSize());
        return foodItemsInStore.takeItemOutOfBuffer();
    }
    public void prodItemToBuffer(FoodItem fooditem) {
        foodItemsInStore.putItemToBuffer(fooditem);
        gui.setSizeToBuffer(foodItemsInStore.bufferSize());
    }
    /*public void insertItemToBuffer(FoodItem fooditem) throws InterruptedException {
        foodItemsInStore.putItemToBuffer(fooditem);
        gui.setSizeToBuffer(foodItemsInStore.bufferSize());
    }*/
    public void lblHandler(String name, int fooditems, double volume, double weight,String foodItemName) {
        gui.updateLabelValues(name, fooditems,volume,weight, foodItemName);
    }
}
