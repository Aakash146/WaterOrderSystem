package com.farmer.Order.Controller;

import com.farmer.Order.DTO.OrderDTO;
import com.farmer.Order.DTO.OrderDetailDTO;
import com.farmer.Order.Entity.Order;
import com.farmer.Order.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path ="api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping(path = "{farmId}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetails(@PathVariable("farmId") UUID farmId){

        return ResponseEntity.ok(orderService.getOrderDetails(farmId));
    }

    @PostMapping
    public ResponseEntity<Order> addNewOrder(@RequestBody OrderDTO orderDTO){

        return ResponseEntity.ok(orderService.addNewOrder(orderDTO));
    }

    @PutMapping(path = "{orderId}")
    public ResponseEntity<Order> cancelOrder(@PathVariable("orderId") UUID id){

        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}
