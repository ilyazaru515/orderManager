package pet.ordermanager.model.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "ORDER_HISTORY", schema = "ORDERS")
public class OrderHistoryEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "OPERATION", nullable = false)
    private String operation;
    @Column(name = "TIME", nullable = false)
    private Timestamp time;
    @Column(name = "ORDER_ID", nullable = false)
    private UUID orderId;
}
