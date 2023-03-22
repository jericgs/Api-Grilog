package com.grilog.grilogapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grilog.grilogapi.api.model.DeliveryDTO;
import com.grilog.grilogapi.api.model.input.DeliveryInputDTO;
import com.grilog.grilogapi.assembler.DeliveryAssembler;
import com.grilog.grilogapi.domain.repository.DeliveryRepository;
import com.grilog.grilogapi.domain.service.CheckoutDeliveryService;
import com.grilog.grilogapi.domain.service.SolicitationDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private SolicitationDeliveryService solicitationDeliveryService;
    private DeliveryRepository deliveryRepository;
    private DeliveryAssembler deliveryAssembler;
    private CheckoutDeliveryService checkoutDeliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDTO solicit(final @Valid @RequestBody DeliveryInputDTO deliveryInputDTO) {
        return deliveryAssembler
                .toDto(solicitationDeliveryService.solicitation(deliveryAssembler.toDelivery(deliveryInputDTO)));
    }

    @GetMapping
    public List<DeliveryDTO> list() {
        return deliveryAssembler.toCollectionDto(deliveryRepository.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryDTO> getMethodName(@PathVariable final Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .map(delivery -> ResponseEntity.ok(deliveryAssembler.toDto(delivery)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{deliveryId}/finished")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void end(@PathVariable final Long deliveryId) {
        checkoutDeliveryService.end(deliveryId);
    }

}
