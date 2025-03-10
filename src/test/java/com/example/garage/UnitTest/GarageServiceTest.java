package com.example.garage.UnitTest;


import com.example.garage.Repository.GarageRepository;
import com.example.garage.Service.GarageService;
import com.example.garage.model.Garage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GarageServiceTest {
    @Mock
    private GarageRepository garageRepository;

    @InjectMocks
    private GarageService garageService;

    private Garage garage;
    private Garage garage2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create sample garages
        garage = new Garage();
        garage.setId(1L);
        garage.setName("Alpha Garage");
        garage.setAddress("123 Test St.");
        garage.setTelephone("123-456-7890");
        garage.setEmail("alpha@example.com");

        garage2 = new Garage();
        garage2.setId(2L);
        garage2.setName("Beta Garage");
        garage2.setAddress("456 Sample St.");
        garage2.setTelephone("987-654-3210");
        garage2.setEmail("beta@example.com");
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

    @Test
    public void testGetAllGarages_PaginationAndSorting() {
        // Arrange
        List<Garage> garages = Arrays.asList(garage, garage2);
        int page = 0, size = 2;
        String sortBy = "name", direction = "asc";
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Garage> garagePage = new PageImpl<>(garages, pageable, garages.size());

        when(garageRepository.findAll(pageable)).thenReturn(garagePage);

        // Act
        Page<Garage> result = garageService.getAllGaragesSorted(page, size, sortBy, direction);

        // Assert
        assertEquals(2, result.getContent().size());
        assertEquals("Alpha Garage", result.getContent().get(0).getName()); // Checking sorting order
        assertEquals("Beta Garage", result.getContent().get(1).getName());

    }
}
