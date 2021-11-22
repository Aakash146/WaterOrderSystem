package com.farmer.Order.Repository;

import com.farmer.Order.Entity.Farmer;
import com.farmer.Order.Entity.Order;
import com.farmer.Order.Enum.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderId(Long orderId);

    @Query(value = "select o from Order o where o.farmer.farmId = :farmId")
    List<Order> findByFarmer(Long farmId);

    List<Order> findByStatusAndStartDateTimeLessThanEqual(OrderStatus status,LocalDateTime currentTime);

    List<Order> findByStatusAndCompletionTimeLessThanEqual(OrderStatus status,LocalDateTime currentTime);

}