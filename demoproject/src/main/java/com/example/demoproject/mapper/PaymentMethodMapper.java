package com.example.demoproject.mapper;

import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentMethodMapper implements EntityMapper<PaymentMethod, PaymentMethodDTO> {
    @Override
    public PaymentMethod toEntity(PaymentMethodDTO dto) {
//        Timestamp createdDate = DateUtils.stringToTimestamp(dto.getCreatedDate());
//        Timestamp updatedDate = DateUtils.stringToTimestamp(dto.getUpdatedDate());
        return PaymentMethod.builder()
                .id(dto.getId())
                .name(dto.getName())
                .notes(dto.getNotes())
                .createdDate(dto.getCreatedDate())
                .updatedDate(dto.getUpdatedDate())
                .isactive(dto.getIsactive())
                .build();
    }

    @Override
    public PaymentMethodDTO toDto(PaymentMethod entity) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//        String createdDate = dateFormat.format(entity.getCreatedDate());
//        String updatedDate = dateFormat.format(entity.getUpdatedDate());
        return PaymentMethodDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .notes(entity.getNotes())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .isactive(entity.getIsactive())
                .build();
    }

    @Override
    public List<PaymentMethod> toEntity(List<PaymentMethodDTO> dto) {
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        dto.forEach(paymentMethodDTO -> paymentMethodList.add(toEntity(paymentMethodDTO)));
        return paymentMethodList;
    }

    @Override
    public List<PaymentMethodDTO> toDto(List<PaymentMethod> entity) {
        List<PaymentMethodDTO> paymentMethodDTOList = new ArrayList<>();
        entity.forEach(paymentMethod -> paymentMethodDTOList.add(toDto(paymentMethod)));
        return paymentMethodDTOList;
    }
}
