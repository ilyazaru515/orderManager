package pet.ordermanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.ordermanager.model.entities.OrderHistoryEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderHistoryCrudRepository extends CrudRepository<OrderHistoryEntity, Long> {
    @Query(value = "SELECT * FROM orders.order_history WHERE order_id = :orderId", nativeQuery = true)
    List<OrderHistoryEntity> findByOrderId(@Param("orderId") UUID orderId);

}
