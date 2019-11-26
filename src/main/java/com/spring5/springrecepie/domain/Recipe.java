package com.spring5.springrecepie.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    @Lob
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }




    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServing() {
        return serving;
    }

    public void setServing(Integer serving) {
        this.serving = serving;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
