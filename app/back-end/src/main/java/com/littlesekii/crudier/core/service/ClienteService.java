package com.littlesekii.crudier.core.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.littlesekii.crudier.core.model.dto.ClienteDTO;
import com.littlesekii.crudier.core.repository.IClienteRepository;

@Service
public class ClienteService {
    
    private IClienteRepository repository;

    public ClienteService(IClienteRepository repository) {
        this.repository = repository;
    }

    public Set<ClienteDTO> findAll() {
        Set<ClienteDTO> result = new HashSet<>();

        var data = repository.findAll(); 
        data.forEach(item -> result.add(ClienteDTO.fromEntity(item)));

        return result;
    }

    public ClienteDTO insert(ClienteDTO data) {
        ClienteDTO result;

        var inserted = repository.save(data.toEntity()); 
        result = ClienteDTO.fromEntity(inserted);
        
        return result;
    }
}
