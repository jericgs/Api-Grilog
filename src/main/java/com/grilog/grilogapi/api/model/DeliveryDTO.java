package com.grilog.grilogapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.grilog.grilogapi.domain.model.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeliveryDTO {

    private Long id;
    private ClientDTO client;
    private RecipientDTO recipient;
    private BigDecimal tax;
    private DeliveryStatus status;
    private OffsetDateTime orderDate;
    private OffsetDateTime completionDate;
}
