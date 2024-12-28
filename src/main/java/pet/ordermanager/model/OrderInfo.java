package pet.ordermanager.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderInfo {
    private UUID userId;
    private List<GoodInfo> goodInfos;
}
