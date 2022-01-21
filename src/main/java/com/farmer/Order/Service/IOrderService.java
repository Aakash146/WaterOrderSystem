package com.farmer.Order.Service;

import com.farmer.Order.DTO.FarmerDTO;
import com.farmer.Order.DTO.FarmerDetailDTO;
import com.farmer.Order.DTO.OrderDTO;
import com.farmer.Order.DTO.OrderDetailDTO;
import com.farmer.Order.Entity.Order;

import java.util.List;

public interface IOrderService {

    Order addNewOrder(OrderDTO orderDTO);

    String cancelOrder(Long orderId);

    List<OrderDetailDTO> getOrderDetails(Long farmId);
}
