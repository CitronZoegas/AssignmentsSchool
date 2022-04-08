package Number3;

import java.util.ArrayList;

import java.util.concurrent.Semaphore;

public class Buffer <FoodItem> {

    private ArrayList<FoodItem> buffer;

    private static MaxLimits maxLimits = new MaxLimits(100,100,100);


    private int capacity;
    private Semaphore MUTEX;
    private Semaphore counterSpotLeftProd;
    private Semaphore CONSUMER;



    public Buffer (int capacity) {

        buffer = new ArrayList<FoodItem>();

        MUTEX = new Semaphore(1);
        counterSpotLeftProd = new Semaphore(maxLimits.getMaxItems(),true);
        CONSUMER = new Semaphore(0);
    }

    public int bufferSize() {
        return buffer.size();
    }

    public void putItemToBuffer(FoodItem foodItem) throws InterruptedException {
        counterSpotLeftProd.acquire();
        try{
            MUTEX.acquire();
            buffer.add(foodItem);

        } finally {
            MUTEX.release();
        }
        CONSUMER.release();
    }
    public FoodItem takeItemOutOfBuffer() throws InterruptedException {
        FoodItem fooditem = null;

        try{

            CONSUMER.acquire();
            counterSpotLeftProd.release();

            MUTEX.acquire();

            fooditem = buffer.remove(0);
            MUTEX.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fooditem;
    }
}
