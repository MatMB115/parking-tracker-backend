package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByEmail(String email);
}
