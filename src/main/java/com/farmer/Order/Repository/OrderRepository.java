package com.farmer.Order.Repository;

import com.farmer.Order.Entity.Order;
import com.farmer.Order.Enum.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Order findByOrderId(UUID orderId);

    @Query(value = "select o from Order o where o.farmId = :farmId")
    List<Order> findByFarmer(UUID farmId);

    List<Order> findByStatusAndStartDateTimeLessThanEqual(OrderStatus status,LocalDateTime currentTime);

    List<Order> findByStatusAndCompletionTimeLessThanEqual(OrderStatus status,LocalDateTime currentTime);

}