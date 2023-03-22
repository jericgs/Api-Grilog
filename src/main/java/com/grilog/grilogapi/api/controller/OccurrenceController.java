package com.grilog.grilogapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grilog.grilogapi.api.model.OccurrenceDTO;
import com.grilog.grilogapi.api.model.input.OccurrenceInputDTO;
import com.grilog.grilogapi.assembler.OccurrenceAssembler;
import com.grilog.grilogapi.domain.model.Delivery;
import com.grilog.grilogapi.domain.model.Occurrence;
import com.grilog.grilogapi.domain.service.RegisterOccurrenceService;
import com.grilog.grilogapi.domain.service.SearchDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
public class OccurrenceController {

    private RegisterOccurrenceService registerOccurrenceService;
    private OccurrenceAssembler occurrenceAssembler;
    private SearchDeliveryService searchDeliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceDTO register(@PathVariable final Long deliveryId,
            @Valid @RequestBody final OccurrenceInputDTO occurrenceInputDTO) {
        Occurrence occurrenceRegister = registerOccurrenceService.register(deliveryId,
                occurrenceInputDTO.getDescription());
        return occurrenceAssembler.toDto(occurrenceRegister);
    }

    @GetMapping
    public List<OccurrenceDTO> list(@PathVariable final Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);
        return occurrenceAssembler.toCollectionDto(delivery.getOccurrences());
    }

}
