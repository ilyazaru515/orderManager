package pet.ordermanager.service;

import pet.ordermanager.model.FullOrderInfo;
import pet.ordermanager.model.OrderInfo;
import pet.ordermanager.model.entities.OrderHistoryEntity;

import java.util.List;
import java.util.UUID;

public interface OrderManagerService {

    UUID newOrder(OrderInfo orderInfo);
    FullOrderInfo getFullOrder(UUID orderId);
    List<FullOrderInfo> getCustomerOrders(UUID userId);
    List<FullOrderInfo> getGoodOrders(UUID goodId);
    List<OrderHistoryEntity> getOrderHistory(UUID orderId);
}
