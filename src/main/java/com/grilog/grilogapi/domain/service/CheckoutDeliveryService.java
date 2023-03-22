package com.grilog.grilogapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grilog.grilogapi.domain.model.Delivery;
import com.grilog.grilogapi.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CheckoutDeliveryService {

    private SearchDeliveryService searchDeliveryService;
    private DeliveryRepository deliveryRepository;

    @Transactional
    public void end(final Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        delivery.end();

        deliveryRepository.save(delivery);
    }
}
