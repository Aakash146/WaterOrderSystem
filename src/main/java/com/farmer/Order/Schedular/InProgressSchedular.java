package com.farmer.Order.Schedular;

import com.farmer.Order.Entity.Order;
import com.farmer.Order.Enum.OrderStatus;
import com.farmer.Order.Repository.OrderRepository;
import com.farmer.Order.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@EnableScheduling
public class InProgressSchedular {
    private static final Logger LOGGER = LoggerFactory.getLogger(InProgressSchedular.class);

    @Autowired
    private OrderRepository orderRepository;

    @Async
    @Scheduled(fixedRate = 60*1000)
    @Transactional
    public void schedular(){
        final List<Order> requestedOrders = orderRepository.findByStatusAndStartDateTimeLessThanEqual(OrderStatus.REQUESTED, LocalDateTime.now());
        if(Objects.nonNull(requestedOrders)){
            requestedOrders.forEach(order -> {
                order.setStatus(OrderStatus.INPROGRESS);
                orderRepository.save(order);
                LOGGER.info("Water delivery to farm {} started with order id {}.", order.getFarmer().getFarmId(), order.getOrderId());
            });
        }
    }
}
