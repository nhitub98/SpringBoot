package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/pttt")
public class PaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping("/paymentmethods")
    public List<PaymentMethodDTO> getAllPaymentMethod() {

        return paymentMethodService.findAllPaymentMethod();
    }
    @GetMapping("/payment-methods/{id}")
    public PaymentMethodDTO getPaymentMethodById(@PathVariable int id) {

        return paymentMethodService.findPaymentMethodById(id);
    }

    @PostMapping("/add")
    public String savePaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        String message = paymentMethodService.savePaymentMethod(paymentMethodDTO);
        return message;
    }

    @PutMapping("/update/{id}")
    public String updatePaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO, @PathVariable int id) {
        String message = paymentMethodService.updatePaymentMethod(id, paymentMethodDTO);
        return message;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePaymentMethod(@PathVariable int id) {
        paymentMethodService.deletePaymentMethod(id);
        return "Xóa thành công";
    }


}

