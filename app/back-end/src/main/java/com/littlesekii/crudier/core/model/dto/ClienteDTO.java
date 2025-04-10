package com.littlesekii.crudier.core.model.dto;

import com.littlesekii.crudier.core.model.Cliente;

public record ClienteDTO(
    Long id,
    String nome,
    String cpf,
    String email,
    String telefone,
    String estado,
    String cidade,
    String endereco
)  
{
    public static ClienteDTO fromEntity(Cliente entity) {
        return new ClienteDTO(
            entity.getId(), 
            entity.getNome(), 
            entity.getCpf(), 
            entity.getEmail(), 
            entity.getTelefone(), 
            entity.getEstado(), 
            entity.getCidade(), 
            entity.getEndereco()
        );     
    }

    public Cliente toEntity() {
        return new Cliente(
            null,
            nome,
            cpf,
            email,
            telefone,
            estado,
            cidade,
            endereco 
        );
    }
}
