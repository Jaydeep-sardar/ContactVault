/**
 * Test class to demonstrate Contact Management System functionality
 * This class shows how to use the ContactManager programmatically
 */
public class ContactVaultTest {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("        CONTACT VAULT - AUTOMATED TEST");
        System.out.println("=".repeat(60));
        
        ContactManager manager = new ContactManager();
        
        // Test 1: Adding contacts
        System.out.println("\n1. Testing Add Contact functionality:");
        System.out.println("-".repeat(40));
        
        Contact contact1 = new Contact("John Doe", "1234567890", "john.doe@email.com");
        Contact contact2 = new Contact("Jane Smith", "9876543210", "jane.smith@gmail.com");
        Contact contact3 = new Contact("Alice Johnson", "5555123456", "alice.j@company.com");
        
        manager.addContact(contact1);
        manager.addContact(contact2);
        manager.addContact(contact3);
        
        // Test 2: Display all contacts
        System.out.println("\n2. Displaying all contacts:");
        System.out.println("-".repeat(40));
        manager.displayAllContacts();
        
        // Test 3: Testing duplicate validation
        System.out.println("\n3. Testing duplicate validation:");
        System.out.println("-".repeat(40));
        Contact duplicate = new Contact("John Smith", "1234567890", "different@email.com");
        manager.addContact(duplicate); // Should fail - same phone
        
        // Test 4: Search functionality
        System.out.println("\n4. Testing search functionality:");
        System.out.println("-".repeat(40));
        manager.displaySearchResults("john");
        manager.displaySearchResults("@gmail.com");
        manager.displaySearchResults("555");
        
        // Test 5: Sorting
        System.out.println("\n5. Testing sort functionality:");
        System.out.println("-".repeat(40));
        manager.sortContactsByName();
        manager.displayAllContacts();
        
        // Test 6: Update contact
        System.out.println("\n6. Testing update functionality:");
        System.out.println("-".repeat(40));
        Contact updatedContact = new Contact("John Doe Jr.", "1234567890", "john.doe.jr@email.com");
        manager.updateContact(0, updatedContact);
        manager.displayAllContacts();
        
        // Test 7: Export to file
        System.out.println("\n7. Testing export functionality:");
        System.out.println("-".repeat(40));
        manager.exportToFile("test_export.csv");
        
        // Test 8: Statistics
        System.out.println("\n8. Contact Statistics:");
        System.out.println("-".repeat(40));
        System.out.println("Total contacts: " + manager.getContactCount());
        System.out.println("Is empty: " + manager.isEmpty());
        
        // Test 9: Delete contact
        System.out.println("\n9. Testing delete functionality:");
        System.out.println("-".repeat(40));
        System.out.println("Before delete:");
        manager.displayAllContacts();
        
        manager.deleteContact(1);
        
        System.out.println("\nAfter delete:");
        manager.displayAllContacts();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           ALL TESTS COMPLETED!");
        System.out.println("=".repeat(60));
    }
}
