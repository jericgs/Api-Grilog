package com.grilog.grilogapi.domain.service;

import org.springframework.stereotype.Service;

import com.grilog.grilogapi.domain.exception.EntityNotFoundException;
import com.grilog.grilogapi.domain.model.Delivery;
import com.grilog.grilogapi.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SearchDeliveryService {

    private DeliveryRepository deliveryRepository;

    public Delivery search(final Long deliveryId) {

        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));
    }
}
