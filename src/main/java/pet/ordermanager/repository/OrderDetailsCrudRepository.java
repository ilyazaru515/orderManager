package pet.ordermanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pet.ordermanager.model.entities.OrderDetailsEntity;

import java.util.UUID;

@Repository
public interface OrderDetailsCrudRepository extends CrudRepository<OrderDetailsEntity, UUID> {
}
