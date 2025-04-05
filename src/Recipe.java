public class Recipe {

    private final String name;
    private String ingredients;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private String steps;

    // Constructor

    public Recipe(String name, String ingredients, double calories, double protein, double fat, double carbs, String steps) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }
        if (ingredients == null || ingredients.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredients cannot be null or empty.");
        }
        if (calories < 0 || protein < 0 || fat < 0 || carbs < 0) {
            throw new IllegalArgumentException("Nutritional values must be non-negative.");
        }
        if (steps == null || steps.trim().isEmpty()) {
            throw new IllegalArgumentException("Preparation steps cannot be null or empty.");
        }

        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.steps = steps;

    }

    // Getters
    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public String getSteps() {
        return steps;
    }

    // Setters with validation

    public void setIngredients(String ingredients) {
        if (ingredients == null || ingredients.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredients cannot be null or empty.");
        }
        this.ingredients = ingredients;
    }

    public void setCalories(double calories) {
        if (calories < 0) {
            throw new IllegalArgumentException("Calories must be non-negative.");
        }
        this.calories = calories;
    }

    public void setProtein(double protein) {
        if (protein < 0) {
            throw new IllegalArgumentException("Protein must be non-negative.");
        }
        this.protein = protein;
    }

    public void setFat(double fat) {
        if (fat < 0) {
            throw new IllegalArgumentException("Fat must be non-negative.");
        }
        this.fat = fat;
    }

    public void setCarbs(double carbs) {
        if (carbs < 0) {
            throw new IllegalArgumentException("Carbs must be non-negative.");
        }
        this.carbs = carbs;
    }

    public void setSteps(String steps) {
        if (steps == null || steps.trim().isEmpty()) {
            throw new IllegalArgumentException("Preparation steps cannot be null or empty.");
        }
        this.steps = steps;
    }

    // Categorize the recipe based on ingredients

    public String categorize() {
        if (ingredients.toLowerCase().contains("meat") || ingredients.toLowerCase().contains("chicken") ||
                ingredients.toLowerCase().contains("beef") || ingredients.toLowerCase().contains("mutton") ||
                ingredients.toLowerCase().contains("prawn") || ingredients.toLowerCase().contains("fish")) {
            return "NonVegetarian";
        } 
        else if (ingredients.toLowerCase().contains("milk") || ingredients.toLowerCase().contains("cheese")) {
            return "Vegetarian";
        } 
        else {
            return "Vegan";
        }
    }

    // Override toString() for a formatted string representation

    @Override
    public String toString() {
        return String.format(
                "Recipe: %s\nIngredients: %s\nCalories: %.2f\nProtein: %.2f g\nFat: %.2f g\nCarbs: %.2f g\nSteps: %s\n",
                name, ingredients, calories, protein, fat, carbs, steps
        );
    }
}