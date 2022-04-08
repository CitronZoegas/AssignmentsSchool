package Number3;

public class FoodItem {

    private String name;
    private double weight;
    private double volume;
    private Threading threading;
    private GUI gui;

    private FoodItem[] foodItems = new FoodItem[20];


    public FoodItem(double weight, double volume, String name) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
    }

    public FoodItem[] everyFoodItem() {
        foodItems = new FoodItem[20];

        foodItems[0] = new FoodItem(1.1,0.5,"Milk");foodItems[1] = new FoodItem(0.6,0.1,"Cream");
        foodItems[2] = new FoodItem(1.1,0.5,"Yoghurt");foodItems[3] = new FoodItem(2.34,0.66,"Butter");
        foodItems[4] = new FoodItem(3.4,1.2,"Flower");foodItems[5] = new FoodItem(13.7,1.8,"Sugar");
        foodItems[6] = new FoodItem(1.4,0.18,"Salt");foodItems[7] = new FoodItem(1.1,0.5,"Almonds");
        foodItems[8] = new FoodItem(1.1,0.1,"Bread");foodItems[9] = new FoodItem(5,0.5,"Donut");
        foodItems[10] = new FoodItem(2,0.8,"Jam");foodItems[11] = new FoodItem(1,0.5,"Ham");
        foodItems[12] = new FoodItem(0.7,1,"Chicken");foodItems[13] = new FoodItem(1.1,2,"Salad");
        foodItems[14] = new FoodItem(2,3,"Orange");foodItems[15] = new FoodItem(3,4,"Apple");
        foodItems[16] = new FoodItem(4,1,"Banana");foodItems[17] = new FoodItem(3,2,"Pear");
        foodItems[18] = new FoodItem(2,3,"Soda");foodItems[19] = new FoodItem(1,4,"Hotdogs");

        return foodItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
