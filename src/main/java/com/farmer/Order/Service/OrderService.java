package com.farmer.Order.Service;

import com.farmer.Order.DTO.OrderDTO;
import com.farmer.Order.DTO.OrderDetailDTO;
import com.farmer.Order.Entity.Order;
import com.farmer.Order.Enum.OrderStatus;
import com.farmer.Order.Exception.ApiRequestException;
import com.farmer.Order.Repository.FarmerRepository;
import com.farmer.Order.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Override
    @Transactional
    public Order addNewOrder(OrderDTO orderDTO) {
        final List<Order> orders = orderRepository.findByFarmer(orderDTO.getFarmId());
        final LocalDateTime completionTime = orderDTO.getStartDateTime().plusHours(orderDTO.getDuration());
        final Order order = new Order();
        if(orders.isEmpty()){
            order.setDuration(orderDTO.getDuration());
            order.setFarmer(farmerRepository.findByFarmId(orderDTO.getFarmId()).get());
            order.setStartDateTime(orderDTO.getStartDateTime());
            order.setStatus(OrderStatus.REQUESTED);
            order.setCompletionTime(orderDTO.getStartDateTime().plusHours(orderDTO.getDuration()));
            orderRepository.save(order);
            LOGGER.info("New Order created succesfully with details {}", order.toString());
        }
        else{
            for (Order lastorder : orders) {
                if (completionTime.isBefore(lastorder.getStartDateTime()) || orderDTO.getStartDateTime().isAfter(lastorder.getCompletionTime()) || lastorder.getStatus() == OrderStatus.CANCELLED) {
                    order.setDuration(orderDTO.getDuration());
                    order.setFarmer(farmerRepository.findByFarmId(orderDTO.getFarmId()).get());
                    order.setStartDateTime(orderDTO.getStartDateTime());
                    order.setStatus(OrderStatus.REQUESTED);
                    order.setCompletionTime(orderDTO.getStartDateTime().plusHours(orderDTO.getDuration()));
                    orderRepository.save(order);
                    LOGGER.info("New Order created succesfully with details {}", order.toString());
                }
                else{
                    LOGGER.error("Request Order overlaps with existing order.");
                    throw new ApiRequestException("Request Order overlaps with existing order.");
                }
            }
        }
        return order;
    }

    @Override
    @Transactional
    public Order cancelOrder(UUID orderId){

        boolean exists = orderRepository.existsById(orderId);
        if(!exists){
            LOGGER.error("Order with id " + orderId + " does not exist");
            throw new ApiRequestException("Order with order id " + orderId + " does not exist");
        }
        final Order order = orderRepository.findByOrderId(orderId);
        if(order.getStatus() == OrderStatus.CANCELLED)
        {
            LOGGER.error("Order with order id {} is already canceled.", order.getOrderId());
            throw new ApiRequestException("Order with order id " + order.getOrderId() + " is already canceled.");
        }else if(order.getStatus() == OrderStatus.INPROGRESS){
            LOGGER.error("Order with order id {} is InProgress so it can't be canceled.", order.getOrderId());
            throw new ApiRequestException("Order with order id " + order.getOrderId() + " is InProgress so it can't be canceled.");
        }else if(order.getStatus() == OrderStatus.DELIVERED) {
            LOGGER.error("Order with order id {} is delivered so it can't be canceled.", order.getOrderId());
            throw new ApiRequestException("Order with order id " + order.getOrderId() + " is delivered so it can't be canceled.");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        LOGGER.info("Order with order id {} cancelled for farm {}.", order.getOrderId(), order.getFarmer().getFarmId());
        return order;

    }

    @Override
    @Transactional
    public List<OrderDetailDTO> getOrderDetails(UUID farmId) {
        boolean exists = farmerRepository.existsById(farmId);
        if(!exists){
            LOGGER.error("Farmer with farmer_id: '"+ farmId + "' don't exists.");
            throw new ApiRequestException("Farmer with farmer_id: '"+ farmId + "' don't exists.");
        }
        final List<Order> orders = farmerRepository.findByFarmId(farmId).get().getOrderDetails();
        System.out.println(orders);
        final List<OrderDetailDTO> dtos = new ArrayList<>();
        orders.forEach(order -> {
            final OrderDetailDTO dto = new OrderDetailDTO();
            dto.setOrderId(order.getOrderId());
            dto.setFarmId(order.getFarmer().getFarmId());
            dto.setStartDateTime(order.getStartDateTime());
            dto.setDuration(order.getDuration());
            dto.setCompletionTime(order.getCompletionTime());
            dto.setStatus(order.getStatus());
            dtos.add(dto);
        });
        return dtos;
    }
}
