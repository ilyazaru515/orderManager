package pet.ordermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.ordermanager.model.entities.OrderEntity;

import java.util.UUID;
@Repository
public interface OrderCrudRepository extends JpaRepository<OrderEntity, UUID> {
}
