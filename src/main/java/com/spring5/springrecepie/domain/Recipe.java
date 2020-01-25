package com.spring5.springrecepie.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@Data
@EqualsAndHashCode(exclude = {"ingredients"})
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Length(min = 3,max = 255)
    private String description;
    @Min(1)
    @Max(1000)
    private Integer prepTime;
    @Min(1)
    @Max(1000)
    private Integer cookTime;
    @Min(1)
    @Max(1000)
    private Integer serving;
    private String source;
    @URL
    private String url;

    @Lob
    @NotBlank
    private String directions;

    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    /*
    recepie is the target property in ingridient class
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredients> ingredients=new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "recipe_category",
            joinColumns= @JoinColumn(name = "recipe_id"),
            inverseJoinColumns=@JoinColumn(name="category_id")
    )
    private Set<Category> categories= new HashSet<>();
    /*
    EnumType.STRING will save the value if this enum as a string in the database, i.e EASY,MODERATE,KINF_OF_HARD,HARD;
    if you use EnumType.ORDINAL this will store the position of the enum i.e 1,2,3,4
     */
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;



    /**
     * Add convinience method to centeralize managing the bidirectional relationships between objects
     * if we add ingridients to recipe but forget to add recipe to ingridient we will see a nell value for a recipe in the ingridient object.
     * So we don't want to forget managing the bidirectional relationship and have it in one central place
     * @param ingredients
     * @return
     */
    public  Recipe addIngrediant(Ingredients ingredients){
        ingredients.setRecipe(this);
        this.ingredients.add(ingredients);
        return this;
    }

    public  Recipe addCategory(Category category){
        category.getRecipes().add(this);
        this.categories.add(category);
        return this;
    }
}
