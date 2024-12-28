package pet.ordermanager.model.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "CUSTOMER", schema = "ORDERS")
public class CustomerEntity {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private UUID userId;
    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private Set<OrderEntity> orderEntities;
}
