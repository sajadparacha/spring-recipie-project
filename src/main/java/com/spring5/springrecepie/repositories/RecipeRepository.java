package com.spring5.springrecepie.repositories;

import com.spring5.springrecepie.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
