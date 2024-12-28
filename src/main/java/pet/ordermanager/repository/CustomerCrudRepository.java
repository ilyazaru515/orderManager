package pet.ordermanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pet.ordermanager.model.entities.CustomerEntity;

import java.util.UUID;

@Repository
public interface CustomerCrudRepository extends CrudRepository<CustomerEntity, UUID> {
}
