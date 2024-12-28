package pet.ordermanager.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pet.ordermanager.model.OrderInfo;
import pet.ordermanager.model.entities.OrderEntity;

import java.util.UUID;

@Component
public class OrderInfoToOrderConverter implements Converter<OrderInfo, OrderEntity> {

    @Override
    public OrderEntity convert(OrderInfo source) {
        var orderEntity = new OrderEntity();
        orderEntity.setUserId(source.getUserId());
        orderEntity.setOrderId(UUID.randomUUID());
        return orderEntity;
    }
}
