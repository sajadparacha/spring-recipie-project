package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.UnitOfMeasure;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class UOMServiceImplTest {
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    UOMService uomService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //**Initiaizing the service and injecting dependencies
        uomService= new UOMServiceImpl(unitOfMeasureRepository);
    }

    @Test
    void listAllUoms() {
        //Given
        Set<UnitOfMeasure> unitOfMeasureSet=new HashSet<UnitOfMeasure>();
        UnitOfMeasure unitOfMeasure1=new UnitOfMeasure();
        unitOfMeasure1.setId(1L);

        UnitOfMeasure unitOfMeasure2=new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        unitOfMeasureSet.add(unitOfMeasure1);
        unitOfMeasureSet.add(unitOfMeasure2);
        //When
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureSet);
        //Then
        assertEquals(uomService.listAllUoms().size(),unitOfMeasureSet.size());
        //uomService.listAllUoms();
        Mockito.verify(unitOfMeasureRepository,times(1)).findAll();
    }
}