package com.grilog.grilogapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grilog.grilogapi.domain.model.Client;
import com.grilog.grilogapi.domain.repository.ClientRepository;
import com.grilog.grilogapi.domain.service.RegisterClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientRepository clientRepository;
    private RegisterClientService registerClientService;

    @GetMapping
    public List<Client> list() {

        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> search(@PathVariable final Long clientId) {

        return clientRepository.findById(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@Valid @RequestBody final Client client) {
        return registerClientService.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@Valid @PathVariable final Long clientId, @RequestBody final Client client) {

        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        client.setId(clientId);
        return ResponseEntity.ok(registerClientService.save(client));

    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable final Long clientId) {

        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        registerClientService.delete(clientId);

        return ResponseEntity.noContent().build();
    }
}
