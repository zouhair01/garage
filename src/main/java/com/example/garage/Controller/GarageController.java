package com.example.garage.Controller;


import com.example.garage.Service.GarageService;
import com.example.garage.model.Accessory;
import com.example.garage.model.Garage;
import com.example.garage.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garages")
public class GarageController {
    @Autowired
    private GarageService garageService;

    @PostMapping
    public Garage addGarage(@RequestBody Garage garage) {
        return garageService.createGarage(garage);
    }

    @GetMapping("/{id}")
    public Garage getGarage(@PathVariable Long id) {
        return garageService.getGarageById(id);
    }

    @GetMapping("/all")
    public List<Garage> getAllGarages() {
        return garageService.getAllGarages();
    }

    @PostMapping("/{garageId}/vehicles")
    public Vehicle addVehicleToGarage(@PathVariable Long garageId, @RequestBody Vehicle vehicle) {
        return garageService.addVehicleToGarage(garageId, vehicle);
    }

    @PostMapping("/vehicles/{vehicleId}/accessories")
    public Accessory addAccessoryToVehicle(@PathVariable Long vehicleId, @RequestBody Accessory accessory) {
        return garageService.addAccessoryToVehicle(vehicleId, accessory);
    }

    @GetMapping("/sorted")
    public Page<Garage> getAllGaragesSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return garageService.getAllGaragesSorted(page, size, sortBy, direction);
    }

    @DeleteMapping("/{id}")
    public void deleteGarage(@PathVariable Long id) {
        garageService.deleteGarage(id);
    }
}
