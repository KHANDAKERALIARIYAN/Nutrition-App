import java.io.*;
import java.util.*;

class RecipeManager {
    
    public final Map<String, Recipe> recipes;

    public RecipeManager() {
        recipes = new HashMap<>();
        loadRecipes();
    }

    // Add a new recipe

    public void addRecipe(Scanner scanner) {
        try {
            System.out.print("Enter recipe name: ");
            String name = scanner.nextLine();
            System.out.print("Enter ingredients (comma-separated): ");
            String ingredients = scanner.nextLine();
            System.out.print("Enter nutritional values (calories, protein, fat, carbs): ");
            String[] nutrition = scanner.nextLine().split(",");
            double calories = Double.parseDouble(nutrition[0]);
            double protein = Double.parseDouble(nutrition[1]);
            double fat = Double.parseDouble(nutrition[2]);
            double carbs = Double.parseDouble(nutrition[3]);
            System.out.print("Enter preparation steps: ");
            String steps = scanner.nextLine();

            Recipe recipe = new Recipe(name, ingredients, calories, protein, fat, carbs, steps);
            recipes.put(name, recipe);
            saveRecipes();
            System.out.println("Recipe added successfully.");
        } 
        catch (NumberFormatException e) {
            System.out.println("Invalid nutritional values. Please enter numbers.");
        } 
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter all four nutritional values (calories, protein, fat, carbs).");
        }
    }

    // View all recipes
    public void viewRecipes() {
        System.out.println("\n--- Recipes ---");
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            for (Recipe recipe : recipes.values()) {
                System.out.println(recipe);
            }
        }
    }

    // Edit an existing recipe
    public void editRecipe(Scanner scanner) {
        System.out.print("Enter the name of the recipe to edit: ");
        String name = scanner.nextLine();

        Recipe recipe = recipes.get(name);
        if (recipe == null) {
            System.out.println("Recipe not found.");
            return;
        }

        System.out.println("\nCurrent Recipe Details:");
        System.out.println(recipe);

        System.out.print("Enter new ingredients (comma-separated, or press Enter to keep unchanged): ");
        String newIngredients = scanner.nextLine();
        if (!newIngredients.isEmpty()) {
            recipe.setIngredients(newIngredients);
        }

        System.out.print("Enter new nutritional values (calories, protein, fat, carbs, or press Enter to keep unchanged): ");
        String newNutrition = scanner.nextLine();
        if (!newNutrition.isEmpty()) {
            try {
                String[] nutrition = newNutrition.split(",");
                recipe.setCalories(Double.parseDouble(nutrition[0]));
                recipe.setProtein(Double.parseDouble(nutrition[1]));
                recipe.setFat(Double.parseDouble(nutrition[2]));
                recipe.setCarbs(Double.parseDouble(nutrition[3]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid nutritional values. No changes made.");
            }
        }

        System.out.print("Enter new preparation steps (or press Enter to keep unchanged): ");
        String newSteps = scanner.nextLine();
        if (!newSteps.isEmpty()) {
            recipe.setSteps(newSteps);
        }

        saveRecipes();
        System.out.println("Recipe updated successfully.");
    }

    // Delete a recipe
    public void deleteRecipe(Scanner scanner) {
        System.out.print("Enter the name of the recipe to delete: ");
        String name = scanner.nextLine();

        if (recipes.containsKey(name)) {
            recipes.remove(name);
            saveRecipes();
            System.out.println("Recipe deleted successfully.");
        } else {
            System.out.println("Recipe not found.");
        }
    }

    // Calculate nutrition for a recipe
    public void calculateNutrition(String recipeName) {
        Recipe recipe = recipes.get(recipeName);
        if (recipe != null) {
            System.out.println("\n--- Nutritional Values for " + recipeName + " ---");
            System.out.printf("Calories: %.2f\n", recipe.getCalories());
            System.out.printf("Protein: %.2f g\n", recipe.getProtein());
            System.out.printf("Fat: %.2f g\n", recipe.getFat());
            System.out.printf("Carbs: %.2f g\n", recipe.getCarbs());
            System.out.println("-------------------------------------------");
        } 
        else {
            System.out.println("Recipe not found.");
        }
    }

    // Suggest ingredient substitutions
    public void suggestSubstitution(String ingredient) {
        System.out.println("\n--- Suggested Substitutions for " + ingredient + " ---");
        switch (ingredient.toLowerCase()) {
            case "sugar" -> System.out.println("Honey, Stevia, Maple Syrup, Coconut Sugar");
            case "butter" -> System.out.println("Olive Oil, Coconut Oil, Avocado, Greek Yogurt");
            case "flour" -> System.out.println("Almond Flour, Coconut Flour, Oat Flour, Whole Wheat Flour");
            case "milk" -> System.out.println("Almond Milk, Soy Milk, Coconut Milk, Oat Milk");
            case "salt" -> System.out.println("Herbs, Spices, Lemon Juice, Seaweed");
            case "egg" -> System.out.println("Flaxseed Meal, Chia Seeds, Unsweetened Applesauce, Mashed Banana");
            case "cream" -> System.out.println("Coconut Cream, Cashew Cream, Greek Yogurt, Silken Tofu");
            case "cheese" -> System.out.println("Nutritional Yeast, Vegan Cheese, Tofu, Cashew Cheese");
            case "oil" -> System.out.println("Unsweetened Applesauce, Mashed Banana, Greek Yogurt, Buttermilk");
            case "rice" -> System.out.println("Quinoa, Cauliflower Rice, Couscous, Barley");
            case "potato" -> System.out.println("Sweet Potato, Turnip, Cauliflower, Carrot");
            case "mayonnaise" -> System.out.println("Greek Yogurt, Avocado, Hummus, Mashed Silken Tofu");
            case "bread crumbs" -> System.out.println("Crushed Crackers, Oats, Cornflakes, Almond Flour");
            case "soy sauce" -> System.out.println("Tamari, Coconut Aminos, Worcestershire Sauce, Miso Paste");
            case "yogurt" -> System.out.println("Coconut Yogurt, Almond Yogurt, Soy Yogurt, Silken Tofu");
            case "chocolate" -> System.out.println("Carob Powder, Cocoa Powder, Dark Chocolate, Raw Cacao");
            case "meat" -> System.out.println("Tofu, Tempeh, Jackfruit, Mushrooms, Lentils");
            case "fish" -> System.out.println("Tofu, Tempeh, Seitan, Hearts of Palm, Eggplant");
            case "cream cheese" -> System.out.println("Greek Yogurt, Ricotta, Cottage Cheese, Cashew Cream");
            case "pasta" -> System.out.println("Zucchini Noodles, Spaghetti Squash, Shirataki Noodles, Whole Grain Pasta");
            default -> System.out.println("No specific substitutions available for " + ingredient + ".");
        }
    }

    // Categorize recipes
    public void categorizeRecipes() {
        System.out.println("\n--- Categorized Recipes ---");

        List<Recipe> vegetarianRecipes = new ArrayList<>();
        List<Recipe> nonVegetarianRecipes = new ArrayList<>();
        List<Recipe> veganRecipes = new ArrayList<>();
        List<Recipe> uncategorizedRecipes = new ArrayList<>();

        for (Recipe recipe : recipes.values()) {
            String category = recipe.categorize();
            switch (category.toLowerCase()) {
                case "vegetarian" -> vegetarianRecipes.add(recipe);
                case "nonvegetarian" -> nonVegetarianRecipes.add(recipe);
                case "vegan" -> veganRecipes.add(recipe);
                default -> uncategorizedRecipes.add(recipe);
            }
        }

        printCategory("Vegetarian Recipes", vegetarianRecipes);
        printCategory("Non-Vegetarian Recipes", nonVegetarianRecipes);
        printCategory("Vegan Recipes", veganRecipes);
        printCategory("Uncategorized Recipes", uncategorizedRecipes);
    }

    // Helper method to print recipes by category
    private void printCategory(String categoryName, List<Recipe> recipes) {
        System.out.println("\n" + categoryName + ":");
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } 
        else {
            for (Recipe recipe : recipes) {
                System.out.println("- " + recipe.getName());
            }
        }
    }

    // Search recipes by ingredient
    public void searchByIngredient(Scanner scanner) {
        System.out.print("Enter the ingredient to search for: ");
        String ingredient = scanner.nextLine().toLowerCase();

        System.out.println("\n--- Recipes Containing '" + ingredient + "' ---");
        boolean found = false;

        for (Recipe recipe : recipes.values()) {
            if (recipe.getIngredients().toLowerCase().contains(ingredient)) {
                System.out.println("- " + recipe.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No recipes found containing '" + ingredient + "'.");
        }
    }

    // Filter recipes by calories
    public void filterByCalories(Scanner scanner) {
        System.out.print("Enter the maximum calorie limit: ");
        double maxCalories;

        try {
            maxCalories = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric value.");
            scanner.nextLine(); // Clear the invalid input
            return;
        }

        System.out.println("\n--- Recipes with Calories <= " + maxCalories + " ---");
        boolean found = false;

        for (Recipe recipe : recipes.values()) {
            if (recipe.getCalories() <= maxCalories) {
                System.out.println("- " + recipe.getName() + " (" + recipe.getCalories() + " calories)");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No recipes found with calories less than or equal to " + maxCalories + ".");
        }
    }

    // Filter recipes by food type
    public void filterByType(Scanner scanner) {
        System.out.print("Enter the food type to filter by (Vegetarian, NonVegetarian, Vegan): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.println("\n--- Recipes of Type: " + type.substring(0, 1).toUpperCase() + type.substring(1) + " ---");
        boolean found = false;

        for (Recipe recipe : recipes.values()) {
            String category = recipe.categorize().toLowerCase();
            if (category.equals(type)) {
                System.out.println("- " + recipe.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No recipes found for the food type: " + type + ".");
        }
    }

    // Load recipes from file
    private void loadRecipes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("recipes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 7) { // Ensure correct format
                    String name = data[0];
                    String ingredients = data[1];
                    double calories = Double.parseDouble(data[2]);
                    double protein = Double.parseDouble(data[3]);
                    double fat = Double.parseDouble(data[4]);
                    double carbs = Double.parseDouble(data[5]);
                    String steps = data[6];
                    recipes.put(name, new Recipe(name, ingredients, calories, protein, fat, carbs, steps));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing recipes found.");
        }
    }

    // Save recipes to file
    private void saveRecipes() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("recipes.txt"))) {
            for (Recipe recipe : recipes.values()) {
                writer.printf("%s;%s;%.2f;%.2f;%.2f;%.2f;%s\n",
                        recipe.getName(), recipe.getIngredients(),
                        recipe.getCalories(), recipe.getProtein(),
                        recipe.getFat(), recipe.getCarbs(), recipe.getSteps());
            }
        } 
        catch (IOException e) {
            System.out.println("Error saving recipes: " + e.getMessage());
        }
    }
}