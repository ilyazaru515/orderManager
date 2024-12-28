package pet.ordermanager.model.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "ORDER_DETAILS", schema = "ORDERS")
public class OrderDetailsEntity {
    @Id
    @Column(name = "DETAIL_ID", nullable = false)
    private UUID detailId;
    @Column(name = "ORDER_ID", nullable = false)
    private UUID orderId;
    @Column(name = "GOOD_ID", nullable = false)
    private UUID goodId;
    @Column(name = "GOOD_COUNT", nullable = false)
    private Integer goodCount;
//    @ManyToOne
//    @JoinColumn(name = "good_id", nullable = false, insertable = false, updatable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private GoodEntity goodEntity;
}
