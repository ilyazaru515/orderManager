package pet.ordermanager.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pet.ordermanager.model.FullGoodInfo;
import pet.ordermanager.model.FullOrderInfo;
import pet.ordermanager.model.entities.OrderDetailsEntity;
import pet.ordermanager.model.entities.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderEntityToFullOrderInfo implements Converter<OrderEntity, FullOrderInfo> {
    @Override
    public FullOrderInfo convert(OrderEntity source) {
        var info = new FullOrderInfo();
        info.setOrderId(source.getOrderId());
        info.setUserId(source.getCustomer().getUserId());
        info.setUserName(source.getCustomer().getUserName());
        info.setUserEmail(source.getCustomer().getEmail());
        var detailsMap = source.getOrderDetailsEntities().stream()
                .collect(Collectors.toMap(OrderDetailsEntity::getGoodId, OrderDetailsEntity::getGoodCount));
        var goodInfos = source.getGoodEntities().stream()
                        .map(good -> {
                            var fullGoodInfo = new FullGoodInfo();
                            fullGoodInfo.setGoodId(good.getGoodId());
                            fullGoodInfo.setGoodName(good.getGoodName());
                            fullGoodInfo.setGoodPrice(good.getGoodPrice());
                            fullGoodInfo.setGoodCount(detailsMap.get(good.getGoodId()));
                            return fullGoodInfo;
                        })
                .toList();
        info.setGoodInfos(goodInfos);
        info.setAmount(computeAmount(goodInfos));
        return info;
    }

    private Double computeAmount(List<FullGoodInfo> goodInfos) {
        return goodInfos.stream()
                .mapToDouble(info -> info.getGoodPrice() * info.getGoodCount())
                .sum();
    }
}
