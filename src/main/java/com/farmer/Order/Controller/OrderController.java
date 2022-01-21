package com.farmer.Order.Controller;

import com.farmer.Order.DTO.OrderDTO;
import com.farmer.Order.DTO.OrderDetailDTO;
import com.farmer.Order.Entity.Order;
import com.farmer.Order.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path ="api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping(path = "{farmId}")
    public List<OrderDetailDTO> getOrderDetails(@PathVariable("farmId") Long farmId){

        return orderService.getOrderDetails(farmId);
    }

    @PostMapping
    public Order addNewOrder(@RequestBody OrderDTO orderDTO){

        return orderService.addNewOrder(orderDTO);
    }

    @PutMapping(path = "{orderId}")
    public String cancelOrder(@PathVariable("orderId") Long id){

        return orderService.cancelOrder(id);
    }
}
