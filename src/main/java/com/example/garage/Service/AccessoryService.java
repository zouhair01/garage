package com.example.garage.Service;

import com.example.garage.Repository.AccessoryRepository;
import com.example.garage.Repository.VehiculeRepository;
import com.example.garage.model.Accessory;
import com.example.garage.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessoryService {
    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private VehiculeRepository vehicleRepository;


    public Accessory addAccessoryToVehicle(Long vehicleId, Accessory accessory) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            accessory.setVehicle(vehicle);
            return accessoryRepository.save(accessory);
        }
        throw new RuntimeException("Vehicle not found.");
    }

    public Accessory updateAccessory(Long accessoryId, Accessory updatedAccessory) {
        Optional<Accessory> accessoryOptional = accessoryRepository.findById(accessoryId);
        if (accessoryOptional.isPresent()) {
            Accessory accessory = accessoryOptional.get();
            accessory.setDescription(updatedAccessory.getDescription());
            accessory.setType(updatedAccessory.getType());
            return accessoryRepository.save(accessory);
        }
        throw new RuntimeException("Accessory not found.");
    }

    public void deleteAccessory(Long accessoryId) {
        if (accessoryRepository.existsById(accessoryId)) {
            accessoryRepository.deleteById(accessoryId);
        } else {
            throw new RuntimeException("Accessory not found.");
        }
    }

    public List<Accessory> getAccessoriesByVehicle(Long vehicleId) {
        return accessoryRepository.findByVehicleId(vehicleId);
    }

}
