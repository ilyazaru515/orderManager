package pet.ordermanager.model;

import lombok.Data;

import java.util.UUID;

@Data
public class FullGoodInfo {
    private UUID goodId;
    private String goodName;
    private Double goodPrice;
    private Integer goodCount;
}
