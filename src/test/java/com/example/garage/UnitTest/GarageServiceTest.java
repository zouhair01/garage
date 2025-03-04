package com.example.garage.UnitTest;


import com.example.garage.Repository.GarageRepository;
import com.example.garage.Service.GarageService;
import com.example.garage.model.Garage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GarageServiceTest {
    @Mock
    private GarageRepository garageRepository;

    @InjectMocks
    private GarageService garageService;

    private Garage garage;

    @BeforeEach
    public void setUp() {
        // Create a sample garage for testing
        garage = new Garage();
        garage.setName("Test Garage");
        garage.setAddress("123 Test St.");
        garage.setTelephone("123-456-7890");
        garage.setEmail("test@example.com");
    }

    @Test
    public void testCreateGarage() {
        when(garageRepository.save(garage)).thenReturn(garage);

        Garage createdGarage = garageService.createGarage(garage);


        assertThat(createdGarage).isNotNull();
        assertThat(createdGarage.getName()).isEqualTo("Test Garage");
        assertThat(createdGarage.getAddress()).isEqualTo("123 Test St.");
    }

    @Test
    public void testGetGarageById() {
        when(garageRepository.findById(1L)).thenReturn(java.util.Optional.of(garage));

        Garage foundGarage = garageService.getGarageById(1L);

        assertThat(foundGarage).isNotNull();
        assertThat(foundGarage.getName()).isEqualTo("Test Garage");
    }
}
