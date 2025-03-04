package com.example.garage.Repository;


import com.example.garage.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage,Long> {
    List<Garage> findByNameContainingIgnoreCase(String name);

}
