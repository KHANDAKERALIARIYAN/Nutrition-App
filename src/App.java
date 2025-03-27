import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    
    private final UserProfile userProfile;
    private final RecipeManager recipeManager;
    private final DietPlanner dietPlanner;

    public App() {
        userProfile = new UserProfile();
        recipeManager = new RecipeManager();
        dietPlanner = new DietPlanner();
    }

    // Helper method to get profile input
    private UserProfile.UserProfileEntry getProfileInput(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter dietary preferences: ");
        String preferences = scanner.nextLine();
        return new UserProfile.UserProfileEntry(name, age, weight, preferences);
    }

    // Feature: Manage User Profile
    public void manageUserProfile(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Manage User Profiles ---");
            System.out.println("1. View All Profiles");
            System.out.println("2. Add New Profile");
            System.out.println("3. Update Profile");
            System.out.println("4. Delete Profile");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> userProfile.displayProfiles();
                    case 2 -> {
                        UserProfile.UserProfileEntry profile = getProfileInput(scanner);
                        userProfile.addProfile(profile);
                    }
                    case 3 -> {
                        System.out.print("Enter name of the profile to update: ");
                        String name = scanner.nextLine();
                        UserProfile.UserProfileEntry profile = getProfileInput(scanner);
                        userProfile.updateProfile(name, profile);
                    }
                    case 4 -> {
                        System.out.print("Enter name of the profile to delete: ");
                        String name = scanner.nextLine();
                        userProfile.deleteProfile(name);
                    }
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Manage Recipes
    public void manageRecipes(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Manage Recipes ---");
            System.out.println("1. Add Recipe");
            System.out.println("2. View Recipes");
            System.out.println("3. Edit Recipe");
            System.out.println("4. Delete Recipe");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> recipeManager.addRecipe(scanner);
                    case 2 -> recipeManager.viewRecipes();
                    case 3 -> recipeManager.editRecipe(scanner);
                    case 4 -> recipeManager.deleteRecipe(scanner);
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Calculate Nutrition for a Recipe
    public void calculateNutrition(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Calculate Nutrition ---");
            System.out.println("1. Calculate for a recipe");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter recipe name to calculate nutrition: ");
                        String recipeName = scanner.nextLine();
                        recipeManager.calculateNutrition(recipeName);
                    }
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Plan Diet
    public void planDiet(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Plan Diet ---");
            System.out.println("1. Create Meal Plan");
            System.out.println("2. View Meal Plan");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> dietPlanner.createMealPlan(scanner, recipeManager);
                    case 2 -> dietPlanner.viewMealPlan();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Ingredient Substitution Suggestion
    public void suggestIngredientSubstitution(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Ingredient Substitution ---");
            System.out.println("1. Get substitution suggestions");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter ingredient to substitute: ");
                        String ingredient = scanner.nextLine();
                        recipeManager.suggestSubstitution(ingredient);
                    }
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Categorize Recipes
    public void categorizeRecipes(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Categorize Recipes ---");
            System.out.println("1. Categorize all recipes");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> recipeManager.categorizeRecipes();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Generate Shopping List
    public void generateShoppingList(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Generate Shopping List ---");
            System.out.println("1. Generate list from meal plan");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> dietPlanner.generateShoppingList(recipeManager);
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Track Nutritional Goals
    public void trackNutritionalGoals(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Track Nutritional Goals ---");
            System.out.println("1. View current progress");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> dietPlanner.trackNutritionalGoals();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Search and Filter Recipes
    public void searchAndFilterRecipes(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Search and Filter Recipes ---");
            System.out.println("1. Search by Ingredient");
            System.out.println("2. Filter by Calories");
            System.out.println("3. Filter by Food Type");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> recipeManager.searchByIngredient(scanner);
                    case 2 -> recipeManager.filterByCalories(scanner);
                    case 3 -> recipeManager.filterByType(scanner);
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Feature: Export Meal Plan
    public void exportMealPlan(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Export Meal Plan ---");
            System.out.println("1. Export current meal plan");
            System.out.println("0. Return to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> dietPlanner.exportMealPlan();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}