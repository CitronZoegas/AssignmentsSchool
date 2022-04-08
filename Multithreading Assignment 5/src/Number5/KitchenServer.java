package Number5;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KitchenServer extends AbstractKitchenServer {

    private KitchenStatus kStatus;
    private Order order;

    public KitchenServer() {
        orderMap = new HashMap<>();

    }

    public void setOrderMap(Map<String, Order> orderMap) {
        this.orderMap = orderMap;
        orderMap = new HashMap<>();
    }
    @Override
    public CompletableFuture<KitchenStatus> receiveOrder(Order order) {
        CompletableFuture<KitchenStatus> compFut = new CompletableFuture<>();

        try{
            Map<String, Order> tempMap = new LinkedHashMap<>();
            tempMap.put(order.getOrderID(),order);
            compFut.complete(KitchenStatus.Received);
            setOrderMap(tempMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        //Runnable task = () -> cook(order);
        ExecutorService ES = Executors.newFixedThreadPool(10);
        ES.submit(() -> {
            try {
                Thread.sleep(1500);
                cook(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
                compFut.complete(KitchenStatus.Rejected);
            }

        });
        return compFut;

    }

    @Override
    public CompletableFuture<OrderStatus> checkStatus(String orderID) throws InterruptedException {
        CompletableFuture<OrderStatus> compFut = new CompletableFuture<>();


        Thread.sleep(1500);
        compFut.complete(orderMap.get(orderID).getStatus());

        return compFut;
    }
    @Override
    protected void cook(Order order) {

        try{
            Random rng = new Random();
            orderMap.get(order.getOrderID()).setStatus(OrderStatus.Received);
            Thread.sleep(rng.nextInt(9000) + 3000);
            orderMap.get(order.getOrderID()).setStatus(OrderStatus.BeingPrepared);
            Thread.sleep(1500);
            orderMap.get(order.getOrderID()).setStatus(OrderStatus.Ready);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompletableFuture<KitchenStatus> serveOrder(String orderID) {
        CompletableFuture<KitchenStatus> compFut = new CompletableFuture<>();
        try{
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        compFut.complete(KitchenStatus.Served);
        orderMap.remove(orderID);

        return compFut;
    }
}
