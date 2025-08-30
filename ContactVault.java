import java.util.*;

/**
 * Main class for the Contact Management System
 * Provides a console-based user interface for CRUD operations
 */
public class ContactVault {
    private ContactManager manager;
    private Scanner scanner;
    
    public ContactVault() {
        this.manager = new ContactManager();
        this.scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        ContactVault app = new ContactVault();
        app.run();
    }
    
    public void run() {
        System.out.println("=".repeat(60));
        System.out.println("        WELCOME TO CONTACT VAULT");
        System.out.println("     Your Personal Contact Manager");
        System.out.println("=".repeat(60));
        
        while (true) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addNewContact();
                    break;
                case 2:
                    viewAllContacts();
                    break;
                case 3:
                    searchContacts();
                    break;
                case 4:
                    updateContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    sortMenu();
                    break;
                case 7:
                    exportContacts();
                    break;
                case 8:
                    importContacts();
                    break;
                case 9:
                    showStatistics();
                    break;
                case 0:
                    exitApplication();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            pauseForUser();
        }
    }
    
    private void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Add New Contact");
        System.out.println("2. View All Contacts");
        System.out.println("3. Search Contacts");
        System.out.println("4. Update Contact");
        System.out.println("5. Delete Contact");
        System.out.println("6. Sort Contacts");
        System.out.println("7. Export to File");
        System.out.println("8. Import from File");
        System.out.println("9. Show Statistics");
        System.out.println("0. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");
    }
    
    private int getChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            return -1;
        } finally {
            scanner.nextLine(); // Consume newline
        }
    }
    
    private void addNewContact() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("           ADD NEW CONTACT");
        System.out.println("-".repeat(40));
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        Contact contact = new Contact(name, phone, email);
        manager.addContact(contact);
    }
    
    private void viewAllContacts() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          VIEW ALL CONTACTS");
        System.out.println("-".repeat(40));
        
        manager.displayAllContacts();
    }
    
    private void searchContacts() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          SEARCH CONTACTS");
        System.out.println("-".repeat(40));
        
        System.out.print("Enter search keyword (name/phone/email): ");
        String keyword = scanner.nextLine();
        
        if (!keyword.trim().isEmpty()) {
            manager.displaySearchResults(keyword);
        } else {
            System.out.println("Please enter a valid search keyword!");
        }
    }
    
    private void updateContact() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          UPDATE CONTACT");
        System.out.println("-".repeat(40));
        
        if (manager.isEmpty()) {
            System.out.println("No contacts available to update!");
            return;
        }
        
        manager.displayAllContacts();
        System.out.print("\nEnter contact number to update: ");
        
        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline
            
            Contact existingContact = manager.getContact(index);
            if (existingContact == null) {
                System.out.println("Invalid contact number!");
                return;
            }
            
            System.out.println("Current contact: " + existingContact);
            System.out.println("Enter new details (press Enter to keep current value):");
            
            System.out.print("Name [" + existingContact.getName() + "]: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                name = existingContact.getName();
            }
            
            System.out.print("Phone [" + existingContact.getPhone() + "]: ");
            String phone = scanner.nextLine();
            if (phone.trim().isEmpty()) {
                phone = existingContact.getPhone();
            }
            
            System.out.print("Email [" + existingContact.getEmail() + "]: ");
            String email = scanner.nextLine();
            if (email.trim().isEmpty()) {
                email = existingContact.getEmail();
            }
            
            Contact updatedContact = new Contact(name, phone, email);
            manager.updateContact(index, updatedContact);
            
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            System.out.println("Invalid input! Please enter a number.");
        }
    }
    
    private void deleteContact() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          DELETE CONTACT");
        System.out.println("-".repeat(40));
        
        if (manager.isEmpty()) {
            System.out.println("No contacts available to delete!");
            return;
        }
        
        manager.displayAllContacts();
        System.out.print("\nEnter contact number to delete: ");
        
        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline
            
            Contact contact = manager.getContact(index);
            if (contact == null) {
                System.out.println("Invalid contact number!");
                return;
            }
            
            System.out.print("Are you sure you want to delete '" + contact.getName() + "'? (y/N): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.toLowerCase().startsWith("y")) {
                manager.deleteContact(index);
            } else {
                System.out.println("Delete operation cancelled.");
            }
            
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            System.out.println("Invalid input! Please enter a number.");
        }
    }
    
    private void sortMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          SORT CONTACTS");
        System.out.println("-".repeat(40));
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Phone");
        System.out.print("Enter your choice: ");
        
        int choice = getChoice();
        
        switch (choice) {
            case 1:
                manager.sortContactsByName();
                manager.displayAllContacts();
                break;
            case 2:
                manager.sortContactsByPhone();
                manager.displayAllContacts();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private void exportContacts() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          EXPORT CONTACTS");
        System.out.println("-".repeat(40));
        
        if (manager.isEmpty()) {
            System.out.println("No contacts to export!");
            return;
        }
        
        System.out.print("Enter filename (without extension): ");
        String filename = scanner.nextLine();
        
        if (!filename.trim().isEmpty()) {
            if (!filename.endsWith(".csv")) {
                filename += ".csv";
            }
            manager.exportToFile(filename);
        } else {
            System.out.println("Invalid filename!");
        }
    }
    
    private void importContacts() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          IMPORT CONTACTS");
        System.out.println("-".repeat(40));
        
        System.out.print("Enter filename (with .csv extension): ");
        String filename = scanner.nextLine();
        
        if (!filename.trim().isEmpty()) {
            manager.importFromFile(filename);
        } else {
            System.out.println("Invalid filename!");
        }
    }
    
    private void showStatistics() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("          STATISTICS");
        System.out.println("-".repeat(40));
        System.out.println("Total Contacts: " + manager.getContactCount());
        
        if (!manager.isEmpty()) {
            // Count contacts with valid email and phone
            ArrayList<Contact> allContacts = new ArrayList<>();
            for (int i = 0; i < manager.getContactCount(); i++) {
                Contact contact = manager.getContact(i);
                if (contact != null) {
                    allContacts.add(contact);
                }
            }
            
            long validEmails = allContacts.stream().filter(Contact::isValidEmail).count();
            long validPhones = allContacts.stream().filter(Contact::isValidPhone).count();
            
            System.out.println("Contacts with valid email: " + validEmails);
            System.out.println("Contacts with valid phone: " + validPhones);
        }
        
        System.out.println("Storage: ArrayList (Dynamic Array)");
    }
    
    private void exitApplication() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     Thank you for using Contact Vault!");
        System.out.println("           Goodbye! 👋");
        System.out.println("=".repeat(50));
    }
    
    private void pauseForUser() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
