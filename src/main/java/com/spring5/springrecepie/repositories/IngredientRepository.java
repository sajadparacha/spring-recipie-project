package com.spring5.springrecepie.repositories;


import com.spring5.springrecepie.domain.Ingredients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientRepository  extends CrudRepository<Ingredients,Long> {
}
