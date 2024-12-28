package pet.ordermanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderCreateResult {
    private String result;
    private UUID orderId;
    private String errorDescription;
}
