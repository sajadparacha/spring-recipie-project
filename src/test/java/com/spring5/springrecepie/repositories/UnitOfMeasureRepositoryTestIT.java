package com.spring5.springrecepie.repositories;

import com.spring5.springrecepie.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)//Load Spring Context
@DataJpaTest//Load JPA context
class UnitOfMeasureRepositoryTestIT {
    @Autowired//Autowie the Repositoyr
    UnitOfMeasureRepository unitOfMeasureRepository;
    @BeforeEach
    void setUp() {
    }

    @Test//Test the actual function
    void findByDescription() {
        String description="Cup";
       Optional<UnitOfMeasure> unitOfMeasure= unitOfMeasureRepository.findByDescription(description);
       assertEquals("Cup",unitOfMeasure.get().getDescription());
    }
}