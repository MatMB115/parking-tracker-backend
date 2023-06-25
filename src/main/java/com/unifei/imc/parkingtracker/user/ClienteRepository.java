package com.unifei.imc.parkingtracker.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByUserId(Integer idRelUser);
}
