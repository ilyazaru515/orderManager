package pet.ordermanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pet.ordermanager.model.entities.GoodEntity;

import java.util.UUID;

@Repository
public interface GoodCrudRepository extends CrudRepository<GoodEntity, UUID> {
}
