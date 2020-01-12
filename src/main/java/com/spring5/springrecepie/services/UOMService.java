package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.UnitOfMeasure;

import java.util.HashSet;
import java.util.Set;

public interface UOMService {
    Set<UnitOfMeasure>  unitsOfMeasure= new HashSet<>();
    public Set<UnitOfMeasure> listAllUoms();
}
