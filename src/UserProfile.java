import java.io.*;
import java.util.*;

public class UserProfile {

    private final List<UserProfileEntry> profiles;

    public UserProfile() {
        profiles = new ArrayList<>();
        loadProfiles();
    }

    // Display all profiles
    public void displayProfiles() {
        System.out.println("\n--- User Profiles ---");
        if (profiles.isEmpty()) {
            System.out.println("No profiles available.");
        } else {
            for (UserProfileEntry profile : profiles) {
                System.out.println(profile);
            }
        }
    }

    // Add a new profile
    public void addProfile(UserProfileEntry profile) {
        if (profile.getAge() <= 0 || profile.getWeight() <= 0) {
            System.out.println("Age and weight must be positive numbers.");
            return;
        }
        profiles.add(profile);
        saveProfiles();
        System.out.println("Profile added successfully.");
    }

    // Update an existing profile
    public void updateProfile(String name, UserProfileEntry updatedProfile) {
        boolean updated = false;
        for (UserProfileEntry profile : profiles) {
            if (profile.getName().equalsIgnoreCase(name)) {
                profile.setAge(updatedProfile.getAge());
                profile.setWeight(updatedProfile.getWeight());
                profile.setPreferences(updatedProfile.getPreferences());
                updated = true;
                break;
            }
        }
        if (updated) {
            saveProfiles();
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Profile not found.");
        }
    }

    // Delete a profile
    public void deleteProfile(String name) {
        boolean removed = profiles.removeIf(profile -> profile.getName().equalsIgnoreCase(name));
        if (removed) {
            saveProfiles();
            System.out.println("Profile deleted successfully.");
        } else {
            System.out.println("Profile not found.");
        }
    }

    // Load profiles from file
    private void loadProfiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userProfiles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) { // Ensure correct format
                    String name = data[0];
                    int age = Integer.parseInt(data[1]);
                    double weight = Double.parseDouble(data[2]);
                    String preferences = data[3];
                    profiles.add(new UserProfileEntry(name, age, weight, preferences));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing profiles found.");
        }
    }

    // Save profiles to file
    private void saveProfiles() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("userProfiles.txt"))) {
            for (UserProfileEntry profile : profiles) {
                writer.printf("%s,%d,%.2f,%s%n",
                        profile.getName(), profile.getAge(), profile.getWeight(), profile.getPreferences());
            }
        } catch (IOException e) {
            System.out.println("Error saving profiles: " + e.getMessage());
        }
    }

    // Inner class to represent a single profile entry
    public static class UserProfileEntry {
        private String name;
        private int age;
        private double weight;
        private String preferences;

        public UserProfileEntry(String name, int age, double weight, String preferences) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.preferences = preferences;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getPreferences() {
            return preferences;
        }

        public void setPreferences(String preferences) {
            this.preferences = preferences;
        }

        @Override
        public String toString() {
            return String.format("Name: %s, Age: %d, Weight: %.2f KG, Preferences: %s",name, age, weight, preferences);
        }
    }
}