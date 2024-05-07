package com.example.demoproject.service;

import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.dto.TransportMethodDTO;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.entities.TransportMethod;
import com.example.demoproject.mapper.PaymentMethodMapper;
import com.example.demoproject.mapper.TransportMethodMapper;
import com.example.demoproject.repository.PaymentMethodRepository;
import com.example.demoproject.repository.TransportMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransportMethodService {
    private final TransportMethodRepository transportMethodRepository;
    private final TransportMethodMapper transportMethodMapper;

    public List<TransportMethodDTO> findAllTransportMethod() {
        List<TransportMethod> transportMethodList =transportMethodRepository.findAll();
        return transportMethodMapper.toDto(transportMethodList);
    }

    public TransportMethodDTO findTransportMethodById(int id) {
        TransportMethod transportMethod= transportMethodRepository.findById(id).orElse(null);
        if (transportMethod == null) {
            return null;
        }
        return transportMethodMapper.toDto(transportMethod);
    }

    public String saveTransportMethod(TransportMethodDTO transportMethodDTO) {
        TransportMethod transportMethod = transportMethodMapper.toEntity(transportMethodDTO);
        transportMethodRepository.save(transportMethod);
        return "Thêm thành công";
    }

    public String updateTransportMethod(int id, TransportMethodDTO transportMethodDTO) {
        TransportMethod transportMethod =transportMethodRepository.findById(id).orElse(null);
        if (transportMethod == null) {
            return "Không tìm thấy phương thức thanh toán có ID = " + id;
        }
        transportMethod.setName(transportMethodDTO.getName());
        transportMethod.setNotes(transportMethodDTO.getNotes());
        transportMethod.setUpdatedDate(new java.sql.Timestamp(new Date().getTime()));
        transportMethod.setIsactive(transportMethodDTO.getIsactive());

        transportMethodRepository.save(transportMethod);

        return "Cập nhật phương thức thanh toán thành công";
    }

    public void deleteTransportMethod(int id) {
        transportMethodRepository.deleteById(id);
    }
}


