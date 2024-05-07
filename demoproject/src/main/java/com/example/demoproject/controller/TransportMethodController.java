package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.dto.TransportMethodDTO;
import com.example.demoproject.service.CategoryService;
import com.example.demoproject.service.TransportMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/transportmethod")
public class TransportMethodController {
    @Autowired
    private TransportMethodService transportMethodService;

    @GetMapping("/transportmethods")
    public List<TransportMethodDTO> getAllTransportMethod() {
        return transportMethodService.findAllTransportMethod();
    }

    @GetMapping("/transportmethod/{id}")
    public TransportMethodDTO getTransportMethodById(@PathVariable int id) {

        return transportMethodService.findTransportMethodById(id);
    }

    @PostMapping("/add")
    public String saveTransportMethod(@RequestBody TransportMethodDTO transportMethodDTO) {
        String message = transportMethodService.saveTransportMethod(transportMethodDTO);
        return message;
    }

    @PutMapping("/update/{id}")
    public String updateTransportMethod(@RequestBody TransportMethodDTO transportMethodDTO, @PathVariable int id) {
        String message = transportMethodService.updateTransportMethod(id, transportMethodDTO);
        return message;
    }
    @DeleteMapping("/delete/{id}")
    public String deleteTransportMethod(@PathVariable int id) {
        transportMethodService.deleteTransportMethod(id);
        return "Xóa thành công";
    }
}


