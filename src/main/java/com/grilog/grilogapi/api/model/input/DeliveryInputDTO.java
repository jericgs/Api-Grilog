package com.grilog.grilogapi.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeliveryInputDTO {

    @Valid
    @NotNull
    private ClientIdInputDTO client;

    @Valid
    @NotNull
    private RecipientInputDTO recipient;

    @NotNull
    private BigDecimal tax;

}
