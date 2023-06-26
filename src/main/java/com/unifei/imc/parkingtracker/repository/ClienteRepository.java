package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.user.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByUserId(Integer idRelUser);
}
