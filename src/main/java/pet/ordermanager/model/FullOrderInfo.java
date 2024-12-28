package pet.ordermanager.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FullOrderInfo {
    private UUID orderId;
    private UUID userId;
    private String userName;
    private String userEmail;
    private Double amount;
    private List<FullGoodInfo> goodInfos;
    private String description;
}
