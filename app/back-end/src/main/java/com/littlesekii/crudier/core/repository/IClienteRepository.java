package com.littlesekii.crudier.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.littlesekii.crudier.core.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    
}
