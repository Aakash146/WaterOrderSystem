package com.farmer.Order.Repository;

import com.farmer.Order.Entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, UUID> {

    Optional<Farmer> findByEmail(String email);
    Optional<Farmer> findByFarmId(UUID farmId);
}
