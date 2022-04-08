package Number5;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderClient extends AbstractOrderClient{

    private Order order;
    private KitchenServer kServer;
    private OrderStatus orderStatus;
    private GenericRestaurantForm gui;
    private Handler handler;
    List<OrderItem> listOfOrders;
    String ID;


    public OrderClient(Handler handler, KitchenServer kServer) {
        this.handler = handler;
        this.kServer = kServer;
        order = new Order();
    }

    @Override
    public void submitOrder() {
        order = handler.getOrder();
        System.out.println(order);
        try{
            //ExecutorService ES = Executors.newFixedThreadPool(10);

            CompletableFuture<KitchenStatus> compFut = kServer.receiveOrder(order);
            String str = compFut.get().name();
            handler.setStatus(str);
            //kServer.receiveOrder(order);

        } catch (Exception e) {
            e.printStackTrace();
        }
        startPollingServer(order.getOrderID());
    }

    @Override
    protected void startPollingServer(String orderId) {
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                boolean infinite = true;
                while(infinite){
                    try {
                        CompletableFuture<OrderStatus> compFut = kServer.checkStatus(orderId);
                        handler.setStatus(compFut.get().name());
                        if(compFut.get().equals(OrderStatus.Ready)){
                            pickUpOrder(orderId);
                            t.cancel();
                            break;
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    //CompletableFuture<OrderStatus> compFut = kServer.checkStatus(orderId);
                    //handler.setStatus(compFut.get().name());
                    //if (kServer.checkStatus(orderId).equals(OrderStatus.Ready)){
                    //    pickUpOrder(orderId);
                    //    t.cancel();
                    //}
                }
            }
        };
        t.scheduleAtFixedRate(tt,500,1000);
    }

    @Override
    protected void pickUpOrder(String orderID) {
        //ExecutorService ES = Executors.newFixedThreadPool(10);
        CompletableFuture<KitchenStatus> compFut = kServer.serveOrder(orderID);
        try {
            String str = compFut.get().name();
            handler.getOrderStatus(str);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    public void addItemToOrder(OrderItem item) {
        order.addOrderItem(item);
    }

    public void removeItemToOrder(OrderItem item) {
        order.removeOrderItem(item);
    }
}