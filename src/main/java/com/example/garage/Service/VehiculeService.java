package com.example.garage.Service;

import com.example.garage.Kafka.VehicleEventPublisher;
import com.example.garage.Repository.GarageRepository;
import com.example.garage.Repository.VehiculeRepository;
import com.example.garage.model.Garage;
import com.example.garage.model.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        private  VehicleEventPublisher vehicleEventPublisher;
        @Autowired
        private  ObjectMapper objectMapper;

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
                Vehicle savedVehicle = vehicleRepository.save(vehicle);
                try {
                    // Convert saved vehicle to JSON.
                    String vehicleJson = objectMapper.writeValueAsString(savedVehicle);
                    // Publish the JSON event to Kafka.
                    vehicleEventPublisher.publishVehicleEvent(vehicleJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return savedVehicle;
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

        public List<Vehicle> getVehiclesByBrand(String brand) {
            return vehicleRepository.findByBrand(brand);
        }


        public List<Vehicle> getAllVehicles() {
            return vehicleRepository.findAll();
        }
}
