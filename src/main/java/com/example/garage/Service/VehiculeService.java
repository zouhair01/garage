package com.example.garage.Service;

import com.example.garage.Repository.GarageRepository;
import com.example.garage.Repository.VehiculeRepository;
import com.example.garage.model.Garage;
import com.example.garage.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

        @Autowired
        private VehiculeRepository vehicleRepository;

        @Autowired
        private GarageRepository garageRepository;


        public Vehicle addVehicleToGarage(Long garageId, Vehicle vehicle) {
            Optional<Garage> garageOptional = garageRepository.findById(garageId);
            if (garageOptional.isPresent()) {
                Garage garage = garageOptional.get();
                if (garage.getVehicles().size() >= 50) {
                    throw new RuntimeException("Garage has reached its maximum vehicle capacity.");
                }
                vehicle.setGarage(garage);
                garage.getVehicles().add(vehicle);
                return vehicleRepository.save(vehicle);
            }
            throw new RuntimeException("Garage not found.");
        }

        public Vehicle updateVehicle(Long vehicleId, Vehicle updatedVehicle) {
            Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
            if (vehicleOptional.isPresent()) {
                Vehicle vehicle = vehicleOptional.get();
                vehicle.setBrand(updatedVehicle.getBrand());
                vehicle.setTypeCarburant(updatedVehicle.getTypeCarburant());
                return vehicleRepository.save(vehicle);
            }
            throw new RuntimeException("Vehicle not found.");
        }

        public void deleteVehicle(Long vehicleId) {
            if (vehicleRepository.existsById(vehicleId)) {
                vehicleRepository.deleteById(vehicleId);
            } else {
                throw new RuntimeException("Vehicle not found.");
            }
        }

        public Vehicle getVehicleById(Long vehicleId) {
            Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
            if (vehicle.isPresent()) {
                return vehicle.get();
            }
            throw new RuntimeException("Vehicle not found.");
        }

        public List<Vehicle> getVehiclesByGarage(Long garageId) {
            return vehicleRepository.findByGarageId(garageId);
        }




        public List<Vehicle> getAllVehicles() {
            return vehicleRepository.findAll();
        }
}
