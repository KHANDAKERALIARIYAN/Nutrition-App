import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            App app = new App();

            while (true) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("------------------------- Nutrition App -------------------------");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("1.  Manage User Profile");
                System.out.println("2.  Manage Recipes");
                System.out.println("3.  Calculate Nutrition for a Recipe");
                System.out.println("4.  Plan Diet");
                System.out.println("5.  Ingredient Substitution Suggestion");
                System.out.println("6.  Categorize Recipes");
                System.out.println("7.  Generate Shopping List");
                System.out.println("8.  Track Nutritional Goals");
                System.out.println("9.  Search and Filter Recipes");
                System.out.println("10. Export Meal Plan");
                System.out.println("0.  Exit ");
                System.out.print("Choose an option: ");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1 -> app.manageUserProfile(scanner);
                        case 2 -> app.manageRecipes(scanner);
                        case 3 -> app.calculateNutrition(scanner);
                        case 4 -> app.planDiet(scanner);
                        case 5 -> app.suggestIngredientSubstitution(scanner);
                        case 6 -> app.categorizeRecipes(scanner);
                        case 7 -> app.generateShoppingList(scanner);
                        case 8 -> app.trackNutritionalGoals(scanner);
                        case 9 -> app.searchAndFilterRecipes(scanner);
                        case 10 -> app.exportMealPlan(scanner);
                        case 0 -> {
                            System.out.println("Exiting...");
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid choice. Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
        }
    }
}