package pet.ordermanager.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "GOOD", schema = "ORDERS")
public class GoodEntity {
    @Id
    @Column(name = "GOOD_ID", nullable = false)
    private UUID goodId;
    @Column(name = "GOOD_NAME", nullable = false)
    private String goodName;
    @Column(name = "GOOD_PRICE", nullable = false)
    private Double goodPrice;
    @ManyToMany(mappedBy = "goodEntities")
    private Set<OrderEntity> orderEntities;
}
