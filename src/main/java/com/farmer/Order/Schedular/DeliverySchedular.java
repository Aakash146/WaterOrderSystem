package com.farmer.Order.Schedular;

import com.farmer.Order.Entity.Order;
import com.farmer.Order.Enum.OrderStatus;
import com.farmer.Order.Repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@EnableScheduling
public class DeliverySchedular {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliverySchedular.class);

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(fixedRate = 60*1000)
    @Transactional
    public void schedular(){
        final List<Order> requestedOrders = orderRepository.findByStatusAndCompletionTimeLessThanEqual(OrderStatus.INPROGRESS, LocalDateTime.now());
        if(Objects.nonNull(requestedOrders)){
            requestedOrders.forEach(order -> {
                order.setStatus(OrderStatus.DELIVERED);
                orderRepository.save(order);
                LOGGER.info("Water delivered to farm {} with order id {}.", order.getFarmer().getFarmId(), order.getOrderId());
            });
        }
    }
}
