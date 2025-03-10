package com.example.garage.Controller;

import com.example.garage.Service.AccessoryService;
import com.example.garage.model.Accessory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accessories")
public class AccessoryController {


    @Autowired
    private AccessoryService accessoryService  ;

    @PostMapping
    public Accessory addAccessory(@RequestBody Accessory accessory) {
        return accessoryService.createAccessory(accessory);
    }


    @GetMapping
    public List<Accessory> getAllAccessories() {
        return accessoryService.getAllAccessories();
    }


    @GetMapping("/{id}")
    public Accessory getAccessoryById(@PathVariable Long id) {
        return accessoryService.getAccessoryById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteAccesory(@PathVariable Long id) {
        accessoryService.deleteAccessory(id);
    }

    @GetMapping("/vehicles/{vehicleId}/accessories")
    public List<Accessory> getAccessories(@PathVariable Long vehicleId) {
        return accessoryService.getAccessoriesByVehicleId(vehicleId);
    }
}
