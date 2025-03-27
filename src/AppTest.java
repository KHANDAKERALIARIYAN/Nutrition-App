import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private App app;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        app = new App();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testManageUserProfile_AddProfile() {
        String input = "2\nJohn\n25\n70.5\nVegetarian\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.manageUserProfile(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Enter name:"));
        assertTrue(output.contains("Enter age:"));
        assertTrue(output.contains("Enter weight:"));
        assertTrue(output.contains("Enter dietary preferences:"));
    }

    @Test
    void testManageRecipes_AddRecipe() {
        String input = "1\nPasta\nTomato, Cheese\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.manageRecipes(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Add Recipe"));
    }

    @Test
    void testCalculateNutrition() {
        String input = "1\nPasta\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.calculateNutrition(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Enter recipe name to calculate nutrition:"));
    }

    @Test
    void testPlanDiet_CreateMealPlan() {
        String input = "1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.planDiet(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Create Meal Plan"));
    }

    @Test
    void testSuggestIngredientSubstitution() {
        String input = "1\nSugar\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.suggestIngredientSubstitution(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Enter ingredient to substitute:"));
    }

    @Test
    void testCategorizeRecipes() {
        String input = "1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.categorizeRecipes(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Categorize all recipes"));
    }

    @Test
    void testGenerateShoppingList() {
        String input = "1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.generateShoppingList(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Generate list from meal plan"));
    }

    @Test
    void testTrackNutritionalGoals() {
        String input = "1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.trackNutritionalGoals(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("View current progress"));
    }

    @Test
    void testSearchAndFilterRecipes_SearchByIngredient() {
        String input = "1\nTomato\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.searchAndFilterRecipes(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Search by Ingredient"));
    }

    @Test
    void testExportMealPlan() {
        String input = "1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        app.exportMealPlan(scanner);

        String output = outputStream.toString();
        assertTrue(output.contains("Export current meal plan"));
    }
}
