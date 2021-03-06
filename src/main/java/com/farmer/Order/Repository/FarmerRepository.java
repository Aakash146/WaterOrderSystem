package com.farmer.Order.Repository;

import com.farmer.Order.Entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    Farmer findByEmail(String email);
    Farmer findByFarmId(Long farmId);
}
