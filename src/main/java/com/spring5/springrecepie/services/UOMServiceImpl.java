package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.UnitOfMeasure;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliterator;

@Service
public class UOMServiceImpl implements UOMService {
    UnitOfMeasureRepository unitOfMeasureRepository;

    public UOMServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasure> listAllUoms() {

        Set<UnitOfMeasure> unitOfMeasuresSet=new HashSet<>();
        Iterable<UnitOfMeasure> unitOfMeasures=unitOfMeasureRepository.findAll();
        if(unitOfMeasures!=null){
            Iterator<UnitOfMeasure> unitOfMeasureIterator =unitOfMeasures.iterator();
            while(unitOfMeasureIterator.hasNext()){
                unitOfMeasuresSet.add(unitOfMeasureIterator.next());
            }

        }
        return unitOfMeasuresSet;
    }
}
