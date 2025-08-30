/**
 * Contact class representing a single contact with name, phone, and email
 * Implements encapsulation and validation
 */
public class Contact {
    private String name;
    private String phone;
    private String email;
    
    // Constructor
    public Contact(String name, String phone, String email) {
        this.name = name != null ? name.trim() : "";
        this.phone = phone != null ? phone.trim() : "";
        this.email = email != null ? email.trim() : "";
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    // Setters with validation
    public void setName(String name) {
        this.name = name != null ? name.trim() : "";
    }
    
    public void setPhone(String phone) {
        this.phone = phone != null ? phone.trim() : "";
    }
    
    public void setEmail(String email) {
        this.email = email != null ? email.trim() : "";
    }
    
    // Validation methods
    public boolean isValid() {
        return !name.isEmpty() && !phone.isEmpty() && !email.isEmpty();
    }
    
    public boolean isValidEmail() {
        return email.contains("@") && email.contains(".");
    }
    
    public boolean isValidPhone() {
        return phone.matches("\\d{10}") || phone.matches("\\+?\\d{10,15}");
    }
    
    // Method to check if contact matches search keyword
    public boolean matches(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return name.toLowerCase().contains(lowerKeyword) ||
               phone.contains(keyword) ||
               email.toLowerCase().contains(lowerKeyword);
    }
    
    // Override equals for duplicate checking
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return phone.equals(contact.phone) || email.equalsIgnoreCase(contact.email);
    }
    
    @Override
    public int hashCode() {
        return phone.hashCode() + email.toLowerCase().hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("Name: %-20s | Phone: %-15s | Email: %-25s", name, phone, email);
    }
    
    // Method to get contact in CSV format for file export
    public String toCSV() {
        return String.format("\"%s\",\"%s\",\"%s\"", name, phone, email);
    }
}
