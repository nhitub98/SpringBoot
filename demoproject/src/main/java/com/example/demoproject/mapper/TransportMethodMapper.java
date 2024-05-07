package com.example.demoproject.mapper;

import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.dto.TransportMethodDTO;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.entities.Product;
import com.example.demoproject.entities.TransportMethod;
import com.example.demoproject.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransportMethodMapper implements EntityMapper<TransportMethod, TransportMethodDTO> {
    @Override
    public TransportMethod toEntity(TransportMethodDTO dto) {
        Timestamp createdDate = DateUtils.stringToTimestamp(dto.getCreatedDate());
        Timestamp updatedDate = DateUtils.stringToTimestamp(dto.getUpdatedDate());
        return TransportMethod.builder()
                .id(dto.getId())
                .name(dto.getName())
                .notes(dto.getNotes())
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .isactive(dto.getIsactive())
                .build();
    }
    @Override
    public TransportMethodDTO toDto(TransportMethod entity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String createdDate = dateFormat.format(entity.getCreatedDate());
        String updatedDate = dateFormat.format(entity.getUpdatedDate());
        return  TransportMethodDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .notes(entity.getNotes())
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .isactive(entity.getIsactive())
                .build();
    }

    @Override
    public List<TransportMethod> toEntity(List<TransportMethodDTO> dto) {
        List<TransportMethod> transportMethodList = new ArrayList<>();
        dto.forEach( transportMethodDTO-> {
            transportMethodList.add(toEntity(transportMethodDTO));
        });
        return transportMethodList;
    }

    @Override
    public List<TransportMethodDTO> toDto(List<TransportMethod> entity) {
        List<TransportMethodDTO> transportMethodDTOList = new ArrayList<>();
        entity.forEach(transportMethod -> {
            TransportMethodDTO transportMethodDTO = toDto(transportMethod);
            transportMethodDTOList.add(transportMethodDTO);
        });
        return transportMethodDTOList;
    }
}
