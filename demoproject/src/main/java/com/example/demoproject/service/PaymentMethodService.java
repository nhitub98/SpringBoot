package com.example.demoproject.service;

import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.entities.Product;
import com.example.demoproject.mapper.PaymentMethodMapper;
import com.example.demoproject.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    public List<PaymentMethodDTO> findAllPaymentMethod() {
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
        return paymentMethodMapper.toDto(paymentMethodList);
    }

    public PaymentMethodDTO findPaymentMethodById(int id) {
        PaymentMethod paymentMethod= paymentMethodRepository.findById(id).orElse(null);
        if (paymentMethod == null) {
            return null;
        }
        return paymentMethodMapper.toDto(paymentMethod);
    }

    public String savePaymentMethod(PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = paymentMethodMapper.toEntity(paymentMethodDTO);
        paymentMethodRepository.save(paymentMethod);
        paymentMethod.setCreatedDate(LocalDateTime.now());
        paymentMethod.setUpdatedDate(LocalDateTime.now());
        return "Thêm thành công";
    }

    public String updatePaymentMethod(int id, PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElse(null);
        if (paymentMethod == null) {
            return "Không tìm thấy phương thức thanh toán có ID = " + id;
        }
        paymentMethod.setName(paymentMethodDTO.getName());
        paymentMethod.setNotes(paymentMethodDTO.getNotes());
        paymentMethod.setCreatedDate(LocalDateTime.now());
        paymentMethod.setUpdatedDate(LocalDateTime.now());
        paymentMethod.setIsactive(paymentMethodDTO.getIsactive());

        paymentMethodRepository.save(paymentMethod);

        return "Cập nhật phương thức thanh toán thành công";
    }

    public void deletePaymentMethod(int id) {
        paymentMethodRepository.deleteById(id);
    }
}
