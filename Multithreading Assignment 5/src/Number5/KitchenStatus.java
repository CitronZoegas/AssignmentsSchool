package Number5;



public enum KitchenStatus {
    Received("order received"),
    Rejected("order rejected"),
    NotFound("order not found"),
    Served("order served"),
    Cooking("order is cooking");

    public final String text;

    KitchenStatus(String name){
        this.text = name;
    }
}
