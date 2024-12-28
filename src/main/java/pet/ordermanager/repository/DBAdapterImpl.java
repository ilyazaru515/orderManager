package pet.ordermanager.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pet.ordermanager.exception.DBInvocationException;
import pet.ordermanager.model.entities.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DBAdapterImpl implements DBAdapter {
    private final CustomerCrudRepository customerCrudRepository;
    private final OrderHistoryCrudRepository orderHistoryCrudRepository;
    private final OrderDetailsCrudRepository orderDetailsCrudRepository;
    private final OrderCrudRepository orderCrudRepository;
    private final GoodCrudRepository goodCrudRepository;

    @Override
    public Optional<OrderEntity> findOrder(UUID orderId) {
        try {
            return orderCrudRepository.findById(orderId);
        } catch (RuntimeException e) {
            throw new DBInvocationException("Failed findOrder invocation: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<CustomerEntity> findCustomer(UUID userId) {
        try {
            return customerCrudRepository.findById(userId);
        } catch (RuntimeException e) {
            throw new DBInvocationException("Failed findCustomer invocation: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<GoodEntity> findGood(UUID goodId) {
        try {
            return goodCrudRepository.findById(goodId);
        } catch (RuntimeException e) {
            throw new DBInvocationException("Failed findGood invocation: " + e.getMessage(), e);
        }
    }

    @Override
    public List<OrderHistoryEntity> findOrderHistory(UUID orderId) {
        try {
            return orderHistoryCrudRepository.findByOrderId(orderId);
        } catch (RuntimeException e) {
            throw new DBInvocationException("Failed findOrderHistory invocation: " + e.getMessage(), e);
        }
    }

    @Override
    public void saveOrder(OrderEntity orderEntity) {
        try {
            orderCrudRepository.save(orderEntity);
        } catch (RuntimeException e) {
            throw new DBInvocationException("Failed saveOrder invocation: " + e.getMessage(), e);
        }
    }

    @Override
    public void saveOrderDetails(OrderDetailsEntity orderDetailsEntity) {
        try {
            orderDetailsCrudRepository.save(orderDetailsEntity);
        } catch (RuntimeException e) {
            throw new DBInvocationException("Failed saveOrderDetails invocation: " + e.getMessage(), e);
        }
    }
}
