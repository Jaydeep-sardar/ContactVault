import java.util.*;
import java.io.*;

/**
 * ContactManager class that handles CRUD operations for contacts
 * Uses ArrayList for storage and provides search, sort, and export functionality
 */
public class ContactManager {
    private ArrayList<Contact> contacts;
    
    public ContactManager() {
        this.contacts = new ArrayList<>();
    }
    
    // CREATE - Add a new contact
    public boolean addContact(Contact contact) {
        if (contact == null || !contact.isValid()) {
            System.out.println("Error: Invalid contact data!");
            return false;
        }
        
        if (!contact.isValidEmail()) {
            System.out.println("Warning: Email format may be invalid!");
        }
        
        if (!contact.isValidPhone()) {
            System.out.println("Warning: Phone format may be invalid!");
        }
        
        // Check for duplicates
        if (isDuplicate(contact)) {
            System.out.println("Error: Contact with same phone or email already exists!");
            return false;
        }
        
        contacts.add(contact);
        System.out.println("Contact added successfully!");
        return true;
    }
    
    // READ - Display all contacts
    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    ALL CONTACTS");
        System.out.println("=".repeat(70));
        
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, contacts.get(i));
        }
        System.out.println("=".repeat(70));
        System.out.println("Total contacts: " + contacts.size());
    }
    
    // READ - Find contact by index
    public Contact getContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            return contacts.get(index);
        }
        return null;
    }
    
    // UPDATE - Update an existing contact
    public boolean updateContact(int index, Contact updatedContact) {
        if (index < 0 || index >= contacts.size()) {
            System.out.println("Error: Invalid contact index!");
            return false;
        }
        
        if (updatedContact == null || !updatedContact.isValid()) {
            System.out.println("Error: Invalid contact data!");
            return false;
        }
        
        // Check for duplicates (excluding current contact)
        Contact originalContact = contacts.get(index);
        contacts.remove(index);
        
        if (isDuplicate(updatedContact)) {
            contacts.add(index, originalContact); // Restore original
            System.out.println("Error: Contact with same phone or email already exists!");
            return false;
        }
        
        contacts.add(index, updatedContact);
        System.out.println("Contact updated successfully!");
        return true;
    }
    
    // DELETE - Remove a contact
    public boolean deleteContact(int index) {
        if (index < 0 || index >= contacts.size()) {
            System.out.println("Error: Invalid contact index!");
            return false;
        }
        
        Contact removedContact = contacts.remove(index);
        System.out.println("Contact deleted: " + removedContact.getName());
        return true;
    }
    
    // SEARCH - Find contacts by keyword
    public ArrayList<Contact> searchContacts(String keyword) {
        ArrayList<Contact> results = new ArrayList<>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        
        for (Contact contact : contacts) {
            if (contact.matches(keyword)) {
                results.add(contact);
            }
        }
        
        return results;
    }
    
    // Display search results
    public void displaySearchResults(String keyword) {
        ArrayList<Contact> results = searchContacts(keyword);
        
        if (results.isEmpty()) {
            System.out.println("No contacts found matching: " + keyword);
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("           SEARCH RESULTS FOR: " + keyword.toUpperCase());
        System.out.println("=".repeat(70));
        
        for (int i = 0; i < results.size(); i++) {
            int originalIndex = contacts.indexOf(results.get(i));
            System.out.printf("[%d] %s%n", originalIndex + 1, results.get(i));
        }
        System.out.println("=".repeat(70));
        System.out.println("Found " + results.size() + " contact(s)");
    }
    
    // SORT - Sort contacts by name
    public void sortContactsByName() {
        contacts.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        System.out.println("Contacts sorted by name!");
    }
    
    // SORT - Sort contacts by phone
    public void sortContactsByPhone() {
        contacts.sort((c1, c2) -> c1.getPhone().compareTo(c2.getPhone()));
        System.out.println("Contacts sorted by phone!");
    }
    
    // UTILITY - Check for duplicates
    private boolean isDuplicate(Contact contact) {
        for (Contact existing : contacts) {
            if (existing.equals(contact)) {
                return true;
            }
        }
        return false;
    }
    
    // EXPORT - Save contacts to CSV file
    public boolean exportToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write header
            writer.println("Name,Phone,Email");
            
            // Write contacts
            for (Contact contact : contacts) {
                writer.println(contact.toCSV());
            }
            
            System.out.println("Contacts exported to " + filename + " successfully!");
            return true;
            
        } catch (IOException e) {
            System.out.println("Error exporting contacts: " + e.getMessage());
            return false;
        }
    }
    
    // IMPORT - Load contacts from CSV file
    public boolean importFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;
            int importedCount = 0;
            
            while ((line = reader.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    // Remove quotes if present
                    String name = parts[0].replaceAll("\"", "").trim();
                    String phone = parts[1].replaceAll("\"", "").trim();
                    String email = parts[2].replaceAll("\"", "").trim();
                    
                    Contact contact = new Contact(name, phone, email);
                    if (addContact(contact)) {
                        importedCount++;
                    }
                }
            }
            
            System.out.println("Imported " + importedCount + " contacts from " + filename);
            return true;
            
        } catch (IOException e) {
            System.out.println("Error importing contacts: " + e.getMessage());
            return false;
        }
    }
    
    // Get total number of contacts
    public int getContactCount() {
        return contacts.size();
    }
    
    // Check if contact list is empty
    public boolean isEmpty() {
        return contacts.isEmpty();
    }
}
