package com.farmer.Order.Service;

import com.farmer.Order.DTO.FarmerDTO;
import com.farmer.Order.DTO.FarmerDetailDTO;
import com.farmer.Order.DTO.OrderDTO;
import com.farmer.Order.DTO.OrderDetailDTO;

import java.util.List;

public interface IOrderService {

    String addNewOrder(OrderDTO orderDTO);

    String cancelOrder(Long orderId);

    List<OrderDetailDTO> getOrderDetails(Long farmId);
}
