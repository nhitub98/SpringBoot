package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class PaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping("/paymentmethods")
    public String findAllPaymentMethods(Model model){
        List<PaymentMethodDTO> paymentMethodDTOList = paymentMethodService.findAllPaymentMethod();
        model.addAttribute("paymentmethods", paymentMethodDTOList);
        return "features/paymentmethod/all_paymentmethod";
    }
    @GetMapping("/add-paymentmethod")
    public String showAddPaymentMethodForm(Model model) {
        model.addAttribute("paymentmethod", new PaymentMethodDTO());
        return "features/paymentmethod/add_paymentmethod";
    }

    @PostMapping("/add-paymentmethod")
    public String addPaymentMethod(@ModelAttribute("paymentmethod") PaymentMethodDTO paymentMethodDTO, Model model) {
        if (paymentMethodDTO != null) {
            paymentMethodService.savePaymentMethod(paymentMethodDTO);
        }
        return "redirect:/paymentmethods";
    }

    @GetMapping("/edit-paymentmethod/{id}")
    public String showUpdatePaymentMethodForm(@PathVariable int id, Model model) {
        PaymentMethodDTO paymentMethodDTO = paymentMethodService.findPaymentMethodById(id);
        model.addAttribute("paymentmethod", paymentMethodDTO);
        return "features/paymentmethod/update_paymentmethod";
    }

    @PostMapping("/edit-paymentmethod/{id}")
    public String updatePaymentMethod(@ModelAttribute("paymentmethod") PaymentMethodDTO paymentMethodDTO, @PathVariable int id) { // Thêm @PathVariable cho id
        paymentMethodDTO.setId(id);
        paymentMethodService.updatePaymentMethod(id, paymentMethodDTO);
        return "redirect:/paymentmethods";
    }
    @GetMapping("/delete-paymentmethod/{id}")
    public String deletePaymentMethod(@PathVariable("id") int id) {
        paymentMethodService.deletePaymentMethod(id);
        return "redirect:/paymentmethods";
    }

}

