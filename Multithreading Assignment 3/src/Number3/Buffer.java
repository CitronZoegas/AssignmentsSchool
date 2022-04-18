package Number3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Buffer <FoodItem> {

    private LinkedList<FoodItem> buffer;

    private static MaxLimits maxLimits = new MaxLimits(100,100,100);


    private Semaphore MUTEX; // = new Semaphore(1,true);
    private Semaphore counterSpotLeftProd; // = new Semaphore(0,true);
    private Semaphore howMany; // = new Semaphore(maxLimits.getMaxItems(),true);

    public Buffer () {

        buffer = new LinkedList<>();

        MUTEX = new Semaphore(1,true);
        howMany = new Semaphore(0,true);
        counterSpotLeftProd = new Semaphore(maxLimits.getMaxItems(),true);
    }

    /**
     * Returns the LinkedList called Buffers length(size)
     * @return Buffer
     */
    public int bufferSize() {
        return buffer.size();
    }

    /**
     * Mutex surrounds the act of adding an item making it synchronized.
     * @param foodItem
     */
    public void putItemToBuffer(FoodItem foodItem) {

        try{
            howMany.release();
            counterSpotLeftProd.acquire();

            MUTEX.acquire();
            buffer.add(foodItem);
            MUTEX.release();


        } catch(Exception e)  {
            e.printStackTrace();
        }
    }

    /**
     * Mutex surrounds the act of removing an item making it synchronized.
     * @return fooditem
     */
    public FoodItem takeItemOutOfBuffer() {
        FoodItem fooditem = null;
        try{
            howMany.acquire();
            counterSpotLeftProd.release();

            MUTEX.acquire();
            fooditem = buffer.removeFirst();
            MUTEX.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fooditem;
    }
}