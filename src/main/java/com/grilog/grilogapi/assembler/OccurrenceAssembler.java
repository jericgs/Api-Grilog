package com.grilog.grilogapi.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grilog.grilogapi.api.model.OccurrenceDTO;
import com.grilog.grilogapi.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OccurrenceAssembler {

    private ModelMapper modelMapper;

    public OccurrenceDTO toDto(final Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceDTO.class);
    }

    public List<OccurrenceDTO> toCollectionDto(final List<Occurrence> occurrences) {
        return occurrences.stream().map(this::toDto).collect(Collectors.toList());
    }

}
