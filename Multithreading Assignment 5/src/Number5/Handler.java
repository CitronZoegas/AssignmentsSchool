package Number5;

import java.util.ArrayList;

public class Handler {

    private GenericRestaurantForm gui;
    private KitchenServer kServer;
    private OrderClient oClient;
    private Order order;
    public Handler() {

        kServer = new KitchenServer();
        oClient = new OrderClient(this, kServer);
        gui = new GenericRestaurantForm(this);
        gui.Start();

    }
    public String[] getOrderValues() {
        order = getOrder();
        return order.getNamesFromOrderList();
    }
    public void getOrderStatus(String orderStatus) {
        gui.updateStatusList(orderStatus);
    }

    public Order getOrder() {
        return order;
    }

    public void setStatus(String str){
        gui.updateStatusList(str);
    }

    public void submitOrder(){
        oClient.submitOrder();
    }


    public void removeItem(OrderItem orderItem) {
        oClient.removeItemToOrder(orderItem);
    }

    public void applyOrder(ArrayList<OrderItem> orderitemArrayList) {
        order = new Order();
        for (OrderItem orderitem : orderitemArrayList) {
            order.addOrderItem(orderitem);
        }
    }
}