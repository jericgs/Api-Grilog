package com.grilog.grilogapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grilog.grilogapi.domain.model.Delivery;
import com.grilog.grilogapi.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegisterOccurrenceService {

    private SearchDeliveryService searchDeliveryService;

    @Transactional
    public Occurrence register(final Long deliveryId, final String description) {

        Delivery delivery = searchDeliveryService.search(deliveryId);
        return delivery.addOccurrence(description);

    }
}
