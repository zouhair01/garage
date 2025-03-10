package com.example.garage.Service;

import com.example.garage.Repository.AccessoryRepository;
import com.example.garage.Repository.GarageRepository;
import com.example.garage.Repository.VehiculeRepository;
import com.example.garage.model.Accessory;
import com.example.garage.model.Garage;
import com.example.garage.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GarageService {
    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private VehiculeRepository vehicleRepository;

    @Autowired
    private AccessoryRepository accessoryRepository;

    public Garage createGarage(Garage garage) {
        return garageRepository.save(garage);
    }

    public Garage getGarageById(Long id) {
        return garageRepository.findById(id).orElse(null);
    }

    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }


    public Vehicle addVehicleToGarage(Long garageId, Vehicle vehicle) {
        Garage garage = getGarageById(garageId);
        if (garage != null) {
            vehicle.setGarage(garage);
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    public Accessory addAccessoryToVehicle(Long vehicleId, Accessory accessory) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        if (vehicle != null) {
            accessory.setVehicle(vehicle);
            return accessoryRepository.save(accessory);
        }
        return null;
    }

    public Page<Garage> getAllGaragesSorted(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return garageRepository.findAll(pageable);
    }

    public void deleteGarage(Long garageId) {
        if (garageRepository.existsById(garageId)) {
            garageRepository.deleteById(garageId);
        } else {
            throw new RuntimeException("garage not found.");
        }
    }
}

