package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.dto.TransportMethodDTO;
import com.example.demoproject.service.CategoryService;
import com.example.demoproject.service.TransportMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransportMethodController {
    @Autowired
    TransportMethodService transportMethodService;

    @GetMapping("/transportmethods")
    public String findAllTransportMethods(Model model){
        List<TransportMethodDTO> transportMethodDTOList = transportMethodService.findAllTransportMethod();
        model.addAttribute("transportmethods", transportMethodDTOList);
        return "features/transportmethod/all_transportmethods";
    }
    @GetMapping("/add-transportmethod")
    public String showTransportMethodForm(Model model) {
        model.addAttribute("transportmethod", new TransportMethodDTO());
        return "features/transportmethod/add_transportmethod";
    }

    @PostMapping("/add-transportmethod")
    public String addTransportMethod(@ModelAttribute("transportmethod") TransportMethodDTO transportMethodDTO, Model model) {
        if (transportMethodDTO != null) {
            transportMethodService.saveTransportMethod(transportMethodDTO);
        }
        return "redirect:/transportmethods";
    }

    @GetMapping("/edit-transportmethod/{id}")
    public String showUpdateTransportMethodForm(@PathVariable int id, Model model) {
        TransportMethodDTO transportMethodDTO= transportMethodService.findTransportMethodById(id);
        model.addAttribute("transportmethod", transportMethodDTO); // Đổi từ "categories" thành "category"
        return "features/transportmethod/update_transportmethod";
    }

    @PostMapping("/edit-transportmethod/{id}")
    public String updateTransportMethod(@ModelAttribute("transportmethod") TransportMethodDTO transportMethodDTO, @PathVariable int id) { // Thêm @PathVariable cho id
        transportMethodService.updateTransportMethod(id, transportMethodDTO);
        return "redirect:/transportmethods";
    }

    @GetMapping("/delete-transportmethod/{id}")
    public String deleteTransportMethod(@PathVariable("id") int id) {
        transportMethodService.deleteTransportMethod(id);
        return "redirect:/transportmethods";
    }

}


