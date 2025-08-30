/**
 * Interactive Demo showing user input functionality
 * This demonstrates how the Contact Vault takes user input
 */
import java.util.Scanner;

public class UserInputDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager manager = new ContactManager();
        
        System.out.println("=".repeat(60));
        System.out.println("        CONTACT VAULT - USER INPUT DEMO");
        System.out.println("=".repeat(60));
        
        // Demonstrate adding a contact with user input
        System.out.println("\n>> Let's add a new contact!");
        System.out.println("-".repeat(40));
        
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        
        // Create and add the contact
        Contact contact = new Contact(name, phone, email);
        boolean success = manager.addContact(contact);
        
        if (success) {
            System.out.println("\n>> Contact added successfully!");
            System.out.println("Here's your contact:");
            manager.displayAllContacts();
        }
        
        // Demonstrate search functionality
        System.out.println("\n>> Let's search for contacts!");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        
        if (!keyword.trim().isEmpty()) {
            manager.displaySearchResults(keyword);
        }
        
        // Ask if user wants to export
        System.out.print("\n💾 Would you like to export your contacts? (y/n): ");
        String exportChoice = scanner.nextLine();
        
        if (exportChoice.toLowerCase().startsWith("y")) {
            System.out.print("📁 Enter filename (without extension): ");
            String filename = scanner.nextLine();
            if (!filename.trim().isEmpty()) {
                manager.exportToFile(filename + ".csv");
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        DEMO COMPLETED - USER INPUT WORKS! ✨");
        System.out.println("=".repeat(60));
        
        scanner.close();
    }
}
