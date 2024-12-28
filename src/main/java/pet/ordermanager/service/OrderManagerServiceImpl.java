package pet.ordermanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import pet.ordermanager.exception.NotConsistencyDataException;
import pet.ordermanager.model.FullOrderInfo;
import pet.ordermanager.model.GoodInfo;
import pet.ordermanager.model.OrderInfo;
import pet.ordermanager.model.entities.OrderDetailsEntity;
import pet.ordermanager.model.entities.OrderEntity;
import pet.ordermanager.model.entities.OrderHistoryEntity;
import pet.ordermanager.repository.DBAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class OrderManagerServiceImpl implements OrderManagerService {
    private final DBAdapter dbAdapter;
    private final Converter<OrderInfo, OrderEntity> orderInfoOrderEntityConverter;
    private final Converter<OrderEntity, FullOrderInfo> orderEntityFullOrderInfoConverter;

    @Override
    public UUID newOrder(OrderInfo orderInfo) {
        if (dbAdapter.findCustomer(orderInfo.getUserId()).isEmpty()) {
            throw new NotConsistencyDataException("Not found customer with such user id");
        }
        var orderEntity = orderInfoOrderEntityConverter.convert(orderInfo);
        List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();;
        for (GoodInfo goodInfo : orderInfo.getGoodInfos()) {
            var goodEntityOpt = dbAdapter.findGood(goodInfo.getGoodId());
            if (goodEntityOpt.isEmpty()) {
                throw new NotConsistencyDataException(("Not found good with good id = " + goodInfo.getGoodId()));
            } else {
                var orderDetail = new OrderDetailsEntity();
                orderDetail.setDetailId(UUID.randomUUID());
                orderDetail.setOrderId(orderEntity.getOrderId());
                orderDetail.setGoodId(goodInfo.getGoodId());
                orderDetail.setGoodCount(goodInfo.getCount());
                orderDetailsEntities.add(orderDetail);
            }
        }
        dbAdapter.saveOrder(orderEntity);
        orderDetailsEntities.forEach(dbAdapter::saveOrderDetails);
        return orderEntity.getOrderId();
    }

    @Override
    public FullOrderInfo getFullOrder(UUID orderId) {
        return dbAdapter.findOrder(orderId)
                .map(orderEntity -> {
                    var info = orderEntityFullOrderInfoConverter.convert(orderEntity);
                    info.setDescription("Order exists");
                    return info;
                })
                .orElse(createNotExistsOrderInfo().get());
    }

    @Override
    public List<FullOrderInfo> getCustomerOrders(UUID userId) {
        return dbAdapter.findCustomer(userId)
                .map(customerEntity -> new ArrayList<>(customerEntity.getOrderEntities()))
                .map(orderEntities -> orderEntities.stream()
                        .map(orderEntityFullOrderInfoConverter::convert)
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public List<FullOrderInfo> getGoodOrders(UUID goodId) {
        return dbAdapter.findGood(goodId)
                .map(goodEntity -> new ArrayList<>(goodEntity.getOrderEntities()))
                .map(orderEntities -> orderEntities.stream()
                        .map(orderEntityFullOrderInfoConverter::convert)
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public List<OrderHistoryEntity> getOrderHistory(UUID orderId) {
        return dbAdapter.findOrderHistory(orderId);
    }

    private Supplier<FullOrderInfo> createNotExistsOrderInfo() {
        return () -> {
            var info = new FullOrderInfo();
            info.setDescription("Order not exists");
            return info;
        };
    }
}
