import java.io.Console;

public class LoginSystem {
    public static void main(String[] args) {
        // Hardcoded credentials for the "database"
        String correctUsername = "admin";
        String correctPassword = "password123";
        
        // Setup for the 3-try logic
        int maxAttempts = 3;
        int attempts = 0;
        boolean loginSuccessful = false;

        // Get the system console to hide password input
        Console console = System.console();

        if (console == null) {
            System.out.println("No console available. Please run this in a terminal/command prompt.");
            return;
        }

        System.out.println("--- System Login ---");

        // Loop until user succeeds or runs out of tries
        while (attempts < maxAttempts) {
            System.out.print("\nEnter Username: ");
            String inputUser = console.readLine();

            // Read password (it will be invisible as you type, which is standard security)
            char[] passwordChars = console.readPassword("Enter Password: ");
            String inputPass = new String(passwordChars);

            // Check if credentials match
            if (inputUser.equals(correctUsername) && inputPass.equals(correctPassword)) {
                loginSuccessful = true;
                break; // Exit the loop early
            } else {
                attempts++;
                int remaining = maxAttempts - attempts;
                
                // Provide feedback based on remaining attempts
                if (remaining > 0) {
                    System.out.println("Incorrect! You have " + remaining + " attempts left.");
                } else {
                    System.out.println("Access Denied. Account locked.");
                }
            }
        }

        // Final result
        if (loginSuccessful) {
            System.out.println("\nWelcome, " + correctUsername + "! Login successful.");
        }
    }
}
