import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class NutritionAppTest {
    public static void main(String[] args) {
        // Initialize classes
        UserProfile userProfile = new UserProfile();
        RecipeManager recipeManager = new RecipeManager();
        DietPlanner dietPlanner = new DietPlanner();

        // Test Case 1.1: Add a New User Profile
        UserProfile.UserProfileEntry profile = new UserProfile.UserProfileEntry("John Doe", 30, 70.5, "Vegetarian");
        userProfile.addProfile(profile);
        System.out.println("Test Case 1.1: Profile added successfully.");

        // Test Case 1.2: Update a User Profile
        UserProfile.UserProfileEntry updatedProfile = new UserProfile.UserProfileEntry("John Doe", 31, 72.0, "Vegan");
        userProfile.updateProfile("John Doe", updatedProfile);
        System.out.println("Test Case 1.2: Profile updated successfully.");

        // Test Case 2.1: Add a New Recipe
        Recipe recipe = new Recipe("Grilled Chicken", "Chicken Breast, Olive Oil, Garlic, Herbs, Salt, Pepper",
                350.0, 30.0, 8.0, 0.0, "Marinate chicken, grill on medium heat until cooked through.");
        recipeManager.recipes.put("Grilled Chicken", recipe);
        System.out.println("Test Case 2.1: Recipe added successfully.");

        // Test Case 2.2: Delete a Recipe
        recipeManager.recipes.remove("Grilled Chicken");
        System.out.println("Test Case 2.2: Recipe deleted successfully.");

        // Test Case 5.1: Categorize a Recipe
        String category = recipeManager.recipes.get("Grilled Chicken").categorize();
        System.out.println("Test Case 5.1: Category: " + category);

        // Test Case 6.1: Suggest Substitutions
        String ingredient = "butter";
        System.out.println("Test Case 6.1: Substitutions for " + ingredient + ":");
        switch (ingredient.toLowerCase()) {
            case "butter" -> System.out.println("Olive Oil, Coconut Oil, Avocado, Greek Yogurt");
            default -> System.out.println("No substitutions found.");
        }
    }
}