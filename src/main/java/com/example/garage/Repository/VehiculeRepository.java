package com.example.garage.Repository;

import com.example.garage.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository  extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByGarageId(Long garageId);
}
