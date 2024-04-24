package com.farmer.Order.Service;

import com.farmer.Order.DTO.OrderDTO;
import com.farmer.Order.DTO.OrderDetailDTO;
import com.farmer.Order.Entity.Order;

import java.util.List;
import java.util.UUID;

public interface IOrderService {

    Order addNewOrder(OrderDTO orderDTO);

    Order cancelOrder(UUID orderId);

    List<OrderDetailDTO> getOrderDetails(UUID farmId);
}
