import java.io.*;
import java.util.*;

class DietPlanner {
    private final List<String> mealPlan;
    private final Map<String, Double> nutritionalGoals;
    private final Map<String, Double> currentIntake;

    public DietPlanner() {
        mealPlan = new ArrayList<>();
        nutritionalGoals = new HashMap<>();
        currentIntake = new HashMap<>();
        initializeGoals();
    }

    // Create a meal plan
    public void createMealPlan(Scanner scanner, RecipeManager recipeManager) {
        System.out.println("\n--- Create Meal Plan ---");
        recipeManager.viewRecipes();
        System.out.print("Enter recipe names to add to the plan (comma-separated): ");
        String[] recipes = scanner.nextLine().split(",");

        mealPlan.clear();
        for (String recipeName : recipes) {
            recipeName = recipeName.trim();
            if (recipeManager.recipes.containsKey(recipeName)) {
                mealPlan.add(recipeName);
            } else {
                System.out.println("Recipe not found: " + recipeName);
            }
        }

        if (!mealPlan.isEmpty()) {
            updateIntake(recipeManager);
            System.out.println("Meal plan created successfully.");
        } else {
            System.out.println("No valid recipes added to the meal plan.");
        }
    }

    // View the current meal plan
    public void viewMealPlan() {
        System.out.println("\n--- Meal Plan ---");
        if (mealPlan.isEmpty()) {
            System.out.println("No meal plan created.");
        } else {
            mealPlan.forEach(System.out::println);
        }
    }

    // Generate a shopping list based on the meal plan
    public void generateShoppingList(RecipeManager recipeManager) {
        System.out.println("\n--- Shopping List ---");
        Map<String, Integer> ingredientCount = new HashMap<>();

        for (String recipeName : mealPlan) {
            Recipe recipe = recipeManager.recipes.get(recipeName);
            if (recipe != null) {
                for (String ingredient : recipe.getIngredients().split(",")) {
                    ingredient = ingredient.trim();
                    ingredientCount.put(ingredient, ingredientCount.getOrDefault(ingredient, 0) + 1);
                }
            }
        }

        if (ingredientCount.isEmpty()) {
            System.out.println("No ingredients found. Please create a meal plan first.");
        } else {
            ingredientCount.forEach((ingredient, count) -> System.out.println(ingredient + " x" + count));
        }
    }

    // Track nutritional goals
    public void trackNutritionalGoals() {
        System.out.println("\n--- Nutritional Goal Tracking ---");
        System.out.println("Goals vs. Intake:");
        nutritionalGoals.forEach((key, goal) -> {
            double intake = currentIntake.getOrDefault(key, 0.0);
            System.out.printf("%s: Goal = %.2f, Intake = %.2f\n", key, goal, intake);
            if (intake > goal) {
                System.out.println("** Warning: Exceeded " + key + " goal **");
            }
        });
    }

    // Export the meal plan to a file
    public void exportMealPlan() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("mealPlan.txt"))) {
            writer.println("--- Meal Plan ---");
            for (String recipe : mealPlan) {
                writer.println(recipe);
            }
            System.out.println("Meal plan exported successfully to mealPlan.txt");
        } catch (IOException e) {
            System.out.println("Error exporting meal plan: " + e.getMessage());
        }
    }

    // Initialize default nutritional goals
    private void initializeGoals() {
        nutritionalGoals.put("Calories", 2000.0);
        nutritionalGoals.put("Protein", 75.0);
        nutritionalGoals.put("Fat", 70.0);
        nutritionalGoals.put("Carbs", 300.0);

        currentIntake.put("Calories", 0.0);
        currentIntake.put("Protein", 0.0);
        currentIntake.put("Fat", 0.0);
        currentIntake.put("Carbs", 0.0);
    }

    // Update nutritional intake based on the meal plan
    private void updateIntake(RecipeManager recipeManager) {
        currentIntake.replaceAll((_, _) -> 0.0); // Reset intake

        for (String recipeName : mealPlan) {
            Recipe recipe = recipeManager.recipes.get(recipeName);
            if (recipe != null) {
                currentIntake.put("Calories", currentIntake.get("Calories") + recipe.getCalories());
                currentIntake.put("Protein", currentIntake.get("Protein") + recipe.getProtein());
                currentIntake.put("Fat", currentIntake.get("Fat") + recipe.getFat());
                currentIntake.put("Carbs", currentIntake.get("Carbs") + recipe.getCarbs());
            }
        }
    }
}