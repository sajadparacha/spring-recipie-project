package com.spring5.springrecepie.repositories;

import com.spring5.springrecepie.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
