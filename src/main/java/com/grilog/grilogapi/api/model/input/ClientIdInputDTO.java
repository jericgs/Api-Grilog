package com.grilog.grilogapi.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientIdInputDTO {

    @NotNull
    private Long id;
}
