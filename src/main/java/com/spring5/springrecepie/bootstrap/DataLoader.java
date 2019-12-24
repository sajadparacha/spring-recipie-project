package com.spring5.springrecepie.bootstrap;

import com.spring5.springrecepie.domain.*;
import com.spring5.springrecepie.repositories.CategoryRepository;
import com.spring5.springrecepie.repositories.RecipeRepository;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository=unitOfMeasureRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("DataLoader:  Start preparing recipes");
        /*
        *
        * For the chicken:

    2 cups buttermilk
    1/2 cup hot sauce
    1 teaspoon kosher salt
    1 teaspoon ground black pepper
    1 teaspoon cayenne pepper
    1 teaspoon garlic powder
    3 1/2 pounds bone-in, skin-on chicken parts

For the batter:

    2 cups all-purpose flour
    1 tablespoon ground black pepper
    1 tablespoon cayenne pepper
    2 teaspoons kosher salt
    1 teaspoon garlic powder

For frying:

    Canola, vegetable, or peanut oil

        * */
        createSpicyFriedChickenRecipe();

        createPerfectGuacamoleRecipe();
    }

    private void createPerfectGuacamoleRecipe() {
        log.debug("DateLoader :  Preparing perfectGuacamoleRecipe");
        Recipe perfectGuacamoleRecipe= new Recipe();
        perfectGuacamoleRecipe.setDescription("Perfect Guacamole");
        perfectGuacamoleRecipe.setDifficulty(Difficulty.HARD);
        perfectGuacamoleRecipe.setCookTime(10);
        perfectGuacamoleRecipe.setPrepTime(10);
        perfectGuacamoleRecipe.setServing(4);
        perfectGuacamoleRecipe.setDirections(" xmlns:th=\"https://www.thymeleaf.org\"\n" +
                "\n" +
                "<h1 th:text=\"'List Of Owners'\">List of Owners    </h1>\n" +
                "Making Guacamole is easy\n" +
                "\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "\n" +
                "Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. You can get creative with homemade guacamole!\n" +
                "Guacamole Tip: Use Ripe Avocados\n" +
                "\n" +
                "The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n" +
                "Watch how to make guacamole\n" +
                "Guacamole\n" +
                "Current Time 0:00\n" +
                "/\n" +
                "Duration 1:05\n" +
                " \n" +
                "Follow me on Pinterest\n" +
                "Save It Print\n" +
                "How to Make Perfect Guacamole Recipe\n" +
                "\n" +
                "    Prep time: 10 minutesYield: Serves 2-4 \n" +
                "\n" +
                "Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.\n" +
                "Ingredients\n" +
                "\n" +
                "    2 ripe avocados\n" +
                "    1/2 teaspoon Kosher salt\n" +
                "    1 Tbsp of fresh lime juice or lemon juice\n" +
                "    2 Tbsp to 1/4 cup of minced red onion or thinly sliced green onion\n" +
                "    1-2 serrano chiles, stems and seeds removed, minced\n" +
                "    2 tablespoons cilantro (leaves and tender stems), finely chopped\n" +
                "    A dash of freshly grated black pepper\n" +
                "    1/2 ripe tomato, seeds and pulp removed, chopped\n" +
                "\n" +
                "Garnish with red radishes or jicama. Serve with tortilla chips.\n" +
                "MethodHide Photos\n" +
                "\n" +
                "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n" +
                "\n" +
                "Hello! All photos and content are copyright protected. Please do not use our photos without prior written permission. Thank you!\n" +
                "\n");

        /**
      *     2 cups buttermilk
      *     1/2 cup hot sauce
      *     1 teaspoon kosher salt
      *     1 teaspoon ground black pepper
      *     1 teaspoon cayenne pepper
      *     1 teaspoon garlic powder
      *     3 1/2 pounds bone-in, skin-on chicken parts
      */
        Optional<UnitOfMeasure> cupUnitOfMeasure= unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> teaSpoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        Ingredients avocados = new Ingredients();
        avocados.setAmount(new BigDecimal(2));
        avocados.setDescription("avocados");
        avocados.setRecipe(perfectGuacamoleRecipe);
        avocados.setUnitOfMeasure(cupUnitOfMeasure.get());
       // perfectGuacamoleRecipe.getIngredients().add(avocados);
        perfectGuacamoleRecipe.addIngrediant(avocados);
        Ingredients kosherSalt = new Ingredients();
        kosherSalt.setAmount(new BigDecimal(1.5));
        kosherSalt.setDescription("Kosher salt");
        kosherSalt.setRecipe(perfectGuacamoleRecipe);
        kosherSalt.setUnitOfMeasure(teaSpoonUnitOfMeasure.get());
        //perfectGuacamoleRecipe.getIngredients().add(kosherSalt);
        perfectGuacamoleRecipe.addIngrediant(kosherSalt);

        Ingredients lemonJuice = new Ingredients();
        lemonJuice.setAmount(new BigDecimal(1));
        lemonJuice.setDescription("garlic Powder");
        lemonJuice.setRecipe(perfectGuacamoleRecipe);
        lemonJuice.setUnitOfMeasure(teaSpoonUnitOfMeasure.get());
        perfectGuacamoleRecipe.addIngrediant(lemonJuice);

        Notes notes= new Notes();
        notes.setRecipe(perfectGuacamoleRecipe);
        notes.setRecipeNotes(" Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
        );
        perfectGuacamoleRecipe.setNotes(notes);
        perfectGuacamoleRecipe.addCategory(categoryRepository.findByDescription("American").get());
        perfectGuacamoleRecipe.addCategory(categoryRepository.findByDescription("Italian").get());
       log.debug("DateLoader :  Saving perfectGuacamoleRecipe");
        recipeRepository.save(perfectGuacamoleRecipe);
    }

    private void createSpicyFriedChickenRecipe() {
        log.debug("DateLoader :  Preparing SpicyFriedChickenRecipe");
        Recipe spicyFriedChickenRecipe= new Recipe();
        spicyFriedChickenRecipe.setDescription("Spicy Fried Chicken");
        spicyFriedChickenRecipe.setDifficulty(Difficulty.MODERATE);
        spicyFriedChickenRecipe.setCookTime(30);
        spicyFriedChickenRecipe.setPrepTime(10);
        spicyFriedChickenRecipe.setServing(4);
        spicyFriedChickenRecipe.setDirections("1 Marinate chicken in buttermilk mixture: Mix the buttermilk, hot sauce, salt, black pepper, cayenne, and garlic powder in a large bowl. Put the chicken pieces in the buttermilk mixture and coat completely.");

        /**
 *
 *     2 ripe avocados
 *     1/2 teaspoon Kosher salt
 *     1 Tbsp of fresh lime juice or lemon juice
 *     2 Tbsp to 1/4 cup of minced red onion or thinly sliced green onion
 *     1-2 serrano chiles, stems and seeds removed, minced
 *     2 tablespoons cilantro (leaves and tender stems), finely chopped
 *     A dash of freshly grated black pepper
 *     1/2 ripe tomato, seeds and pulp removed, chopped
 */
        Optional<UnitOfMeasure> cupUnitOfMeasure= unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> teaSpoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        Ingredients butterMilk = new Ingredients();
        butterMilk.setAmount(new BigDecimal(2));
        butterMilk.setDescription("butterMilk");
        butterMilk.setRecipe(spicyFriedChickenRecipe);
        butterMilk.setUnitOfMeasure(cupUnitOfMeasure.get());
        spicyFriedChickenRecipe.addIngrediant(butterMilk);

        Ingredients hotSauce = new Ingredients();
        hotSauce.setAmount(new BigDecimal(2));
        hotSauce.setDescription("hot Sauce");
        hotSauce.setRecipe(spicyFriedChickenRecipe);
        hotSauce.setUnitOfMeasure(cupUnitOfMeasure.get());
        spicyFriedChickenRecipe.addIngrediant(hotSauce);


        Ingredients garlicPowder = new Ingredients();
        garlicPowder.setAmount(new BigDecimal(2));
        garlicPowder.setDescription("garlic Powder");
        garlicPowder.setRecipe(spicyFriedChickenRecipe);
        garlicPowder.setUnitOfMeasure(teaSpoonUnitOfMeasure.get());
        spicyFriedChickenRecipe.addIngrediant(garlicPowder);

        Notes notes= new Notes();
        notes.setRecipe(spicyFriedChickenRecipe);
        notes.setRecipeNotes("What do you serve with fried chicken?\n" +
                "\n" +
                "The possibilities are endless! I think extra hot sauce for the true heat seekers is always a must."
        );
        spicyFriedChickenRecipe.setNotes(notes);
        spicyFriedChickenRecipe.addCategory(categoryRepository.findByDescription("Italian").get());
        log.debug("DateLoader :  Saving SpicyFriedChickenRecipe");
        recipeRepository.save(spicyFriedChickenRecipe);
    }
}
