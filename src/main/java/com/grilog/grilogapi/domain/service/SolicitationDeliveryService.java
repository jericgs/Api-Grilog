package com.grilog.grilogapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grilog.grilogapi.domain.model.Client;
import com.grilog.grilogapi.domain.model.Delivery;
import com.grilog.grilogapi.domain.model.DeliveryStatus;
import com.grilog.grilogapi.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitationDeliveryService {

    private RegisterClientService registerClientService;
    private DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery solicitation(final Delivery delivery) {

        Client client = registerClientService.search(delivery.getClient().getId());

        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }
}
