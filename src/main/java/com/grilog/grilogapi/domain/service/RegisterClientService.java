package com.grilog.grilogapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grilog.grilogapi.domain.exception.BusinessException;
import com.grilog.grilogapi.domain.exception.EntityNotFoundException;
import com.grilog.grilogapi.domain.model.Client;
import com.grilog.grilogapi.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegisterClientService {

    private ClientRepository clientRepository;

    public Client search(final Long clientId) {

        return clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

    }

    @Transactional
    public Client save(final Client client) {

        boolean emailUse = clientRepository.findByEmail(client.getEmail())
                .stream().anyMatch(existClient -> !existClient.equals(client));
        if (emailUse) {
            throw new BusinessException("Já existe um cliente cadastrado com esse e-mail.");
        }
        return clientRepository.save(client);
    }

    @Transactional
    public void delete(final Long clientId) {
        clientRepository.deleteById(clientId);
    }

}
