package com.example.garage.Controller;

import com.example.garage.Service.VehiculeService;
import com.example.garage.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {


        @Autowired
        private VehiculeService vehicleService;


        @PostMapping("/{garageId}")
        public Vehicle addVehicleToGarage(@PathVariable Long garageId, @RequestBody Vehicle vehicle) {
            return vehicleService.addVehicleToGarage(garageId, vehicle);
        }

        @GetMapping
        public List<Vehicle> getAllVehicles() {
            return vehicleService.getAllVehicles();
        }

        @GetMapping("/{id}")
        public Vehicle getVehicle(@PathVariable Long id) {
            return vehicleService.getVehicleById(id);
        }


        @GetMapping("/garage/{garageId}")
        public List<Vehicle> getVehiclesByGarage(@PathVariable Long garageId) {
            return vehicleService.getVehiclesByGarage(garageId);
        }


        @DeleteMapping("/{id}")
        public void deleteVehicle(@PathVariable Long id) {
            vehicleService.deleteVehicle(id);
        }
}


