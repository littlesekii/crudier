package com.littlesekii.crudier.core.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.littlesekii.crudier.core.model.dto.ClienteDTO;
import com.littlesekii.crudier.core.service.ClienteService;



@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<Set<ClienteDTO>> get() {
        Set<ClienteDTO> res = service.findAll();
        return ResponseEntity.ok().body(res);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> post(@RequestBody ClienteDTO req) {
        ClienteDTO res = service.insert(req);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.id())
            .toUri();

        return ResponseEntity.created(uri).body(res);
    }    
}
