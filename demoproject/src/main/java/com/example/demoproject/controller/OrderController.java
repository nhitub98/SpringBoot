package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.OrdersDTO;
import com.example.demoproject.service.CategoryService;
import com.example.demoproject.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/orders")
    public List<OrdersDTO> getAllOrders() {
        return ordersService.findAllOrders();
    }

    @GetMapping("/order/{id}")
    public OrdersDTO getOrdersById(@PathVariable int id) {
        return ordersService.findOrdersById(id);
    }
//
//    @PostMapping("/add")
//    public String saveOrder(@RequestBody OrdersDTO ordersDTO) {
//        String message = ordersService.saveOrders(ordersDTO);
//        return message;
//    }
//
//    @PutMapping("/update/{id}")
//    public String updateOrder(@RequestBody OrdersDTO ordersDTO, @PathVariable int id) {
//        String message = ordersService.updateOrders(id, ordersDTO);
//        return message;
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteOrder(@PathVariable int id) {
//        ordersService.deleteOrders(id);
//        return "Xóa thành công";
//    }
}
