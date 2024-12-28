package pet.ordermanager.model.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ORDER", schema = "ORDERS")
public class OrderEntity implements Serializable {
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private UUID orderId;
    @Column(name = "USER_ID", nullable = false)
    private UUID userId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CustomerEntity customer;
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private Set<OrderDetailsEntity> orderDetailsEntities;
    @ManyToMany
    @JoinTable(
            schema = "orders",
            name = "order_details",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "good_id"))
    private Set<GoodEntity> goodEntities;
}
