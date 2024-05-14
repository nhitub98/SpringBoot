package com.example.demoproject.controller;

import com.example.demoproject.dto.*;
import com.example.demoproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    OrdersDetailsService orderDetailsService;
    @Autowired
    OrdersPaymentService ordersPaymentService;
    @Autowired
    OrdersTransportService ordersTransportService;

    @GetMapping("/orders")
    public String findAllOrders(Model model){
        List<OrdersDTO> ordersDTOList = ordersService.findAllOrders();
        model.addAttribute("orders", ordersDTOList);
        return "features/order/all_orders";
    }
    @GetMapping("/edit-orders/{id}")
    public String showUpdateOrdersForm(@PathVariable int id, Model model) {
        OrdersDTO ordersDTO = ordersService.findOrdersById(id);
        model.addAttribute("orders", ordersDTO);
        OrdersDetailsDTO ordersDetailsDTO = orderDetailsService.findOrdersDetailsById(id);
        model.addAttribute("orders_details", ordersDetailsDTO);
        OrdersPaymentDTO ordersPaymentDTO = ordersPaymentService.findOrdersPaymentById(id);
        model.addAttribute("orders_payment",ordersPaymentDTO);
        OrdersTransportDTO ordersTransportDTO = ordersTransportService.findOrdersTransportById(id);
        model.addAttribute("orders_transport",ordersTransportDTO);
        return "features/order/update_order";
    }

    @PostMapping("/edit-orders/{id}")
    public String updateOrders(@ModelAttribute("orders") OrdersDTO ordersDTO, @PathVariable int id, @RequestParam("status") int status,Model model) {
        ordersDTO.setId(id);
        ordersDTO.setStatus(status);
        ordersService.updateOrders(id, ordersDTO);
        OrdersDetailsDTO ordersDetailsDTO = orderDetailsService.findOrdersDetailsById(id);
        model.addAttribute("orders_details",ordersDetailsDTO);
        OrdersPaymentDTO ordersPaymentDTO = ordersPaymentService.findOrdersPaymentById(id);
        model.addAttribute("orders_payment",ordersPaymentDTO);
        OrdersTransportDTO ordersTransportDTO = ordersTransportService.findOrdersTransportById(id);
        model.addAttribute("orders_transport",ordersTransportDTO);
        return "redirect:/orders";
    }

    @GetMapping("/delete-orders/{id}")
    public String deleteOrders(@PathVariable("id") int id) {
        ordersService.deleteOrders(id);
        return "redirect:/orders";
    }
}
