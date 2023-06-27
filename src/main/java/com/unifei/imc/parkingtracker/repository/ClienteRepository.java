package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.entity.user.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByUserId(Integer idRelUser);
}
