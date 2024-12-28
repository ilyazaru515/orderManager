package pet.ordermanager.repository;

import pet.ordermanager.model.entities.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DBAdapter {

    Optional<OrderEntity> findOrder(UUID orderId);
    Optional<CustomerEntity> findCustomer(UUID userId);
    Optional<GoodEntity> findGood(UUID goodId);
    List<OrderHistoryEntity> findOrderHistory(UUID orderId);

    void saveOrder(OrderEntity orderEntity);
    void saveOrderDetails(OrderDetailsEntity orderDetailsEntity);
}
