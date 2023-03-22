package com.grilog.grilogapi.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OccurrenceInputDTO {

    @NotBlank
    private String description;
}
