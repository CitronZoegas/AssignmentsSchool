package Number3;

public class MaxLimits {


    private int maxItems;
    private int maxWeight;
    private int maxVolume;


    public MaxLimits( int maxItems, int maxWeight, int maxVolume) {
        this.maxItems = maxItems;
        this.maxWeight = maxWeight;
        this.maxVolume = maxVolume;

    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setMaxVolume(int maxVolume) {
        this.maxVolume = maxVolume;
    }

    public int getMaxItems() {
        System.out.println("1");
        return maxItems;
    }
    public int getMaxWeight() {
        System.out.println("2");
        return maxWeight;
    }
    public int getMaxVolume() {
        System.out.println("3");
        return maxVolume;
    }
}

