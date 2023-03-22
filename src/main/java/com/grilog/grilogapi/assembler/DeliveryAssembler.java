package com.grilog.grilogapi.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grilog.grilogapi.api.model.DeliveryDTO;
import com.grilog.grilogapi.api.model.input.DeliveryInputDTO;
import com.grilog.grilogapi.domain.model.Delivery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliveryAssembler {

    private ModelMapper modelMapper;

    public DeliveryDTO toDto(final Delivery delivery) {
        return modelMapper.map(delivery, DeliveryDTO.class);
    }

    public List<DeliveryDTO> toCollectionDto(final List<Delivery> deliveries) {
        return deliveries.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Delivery toDelivery(final DeliveryInputDTO deliveryInputDTO) {
        return modelMapper.map(deliveryInputDTO, Delivery.class);
    }
}
