package com.example.garage.UnitTest;

import com.example.garage.Repository.GarageRepository;
import com.example.garage.Repository.VehiculeRepository;
import com.example.garage.Service.VehiculeService;
import com.example.garage.model.Garage;
import com.example.garage.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VehiculeTest {
    @Mock
    private GarageRepository garageRepository;

    @Mock
    private VehiculeRepository vehicleRepository;

    @InjectMocks
    private VehiculeService vehicleService;

    private Garage garage;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        garage = new Garage();
        garage.setId(1L);
        garage.setName("Test Garage");
        garage.setVehicles(new ArrayList<>());

        vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setBrand("Test Brand");

    }

    @Test
    public void testAddVehicleToGarage_Success() {
        when(garageRepository.findById(1L)).thenReturn(Optional.of(garage));
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        Vehicle savedVehicle = vehicleService.addVehicleToGarage(1L, vehicle);

        assertNotNull(savedVehicle);
        assertEquals(garage, savedVehicle.getGarage());
        assertEquals(1, garage.getVehicles().size());
    }

    @Test
    public void testAddVehicleToGarage_ExceedsCapacity() {
        for (int i = 0; i < 50; i++) {
            garage.getVehicles().add(new Vehicle());
        }
        when(garageRepository.findById(1L)).thenReturn(Optional.of(garage));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vehicleService.addVehicleToGarage(1L, vehicle);
        });

        assertEquals("Garage has reached its maximum vehicle capacity.", exception.getMessage());
    }

    @Test
    public void testAddVehicleToGarage_GarageNotFound() {
        when(garageRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vehicleService.addVehicleToGarage(1L, vehicle);
        });

        assertEquals("Garage not found.", exception.getMessage());
    }

    @Test
    public void testGetVehiclesByGarage() {

        when(garageRepository.findById(1L)).thenReturn(Optional.of(garage));

        List<Vehicle> vehicles = vehicleService.getVehiclesByGarage(1L);

        assertNotNull(vehicles);
        assertTrue(vehicles.isEmpty());
    }
}
