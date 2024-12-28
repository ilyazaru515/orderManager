package pet.ordermanager.model;

import lombok.Data;

import java.util.UUID;

@Data
public class GoodInfo {
    private UUID goodId;
    private Integer count;
}
