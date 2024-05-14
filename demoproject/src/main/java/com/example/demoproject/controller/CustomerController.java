package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.CustomerDTO;
import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.service.CategoryService;
import com.example.demoproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/customers")
    public String findAllCustomers(Model model){
        List<CustomerDTO> customerDTOS = customerService.findAllCustomers();
        model.addAttribute("customers",customerDTOS);
        return "features/customer/all_customer";
    }
}
