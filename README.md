# Contact Vault - Simple Contact Management System

A comprehensive Java application for managing personal contacts with full CRUD operations, search functionality, and data export capabilities.

## Features

### Core CRUD Operations
- **Create**: Add new contacts with validation
- **Read**: View all contacts in a formatted display
- **Update**: Modify existing contact information
- **Delete**: Remove contacts with confirmation

### Advanced Features
- **Duplicate Validation**: Prevents duplicate contacts based on phone and email
- **Keyword Search**: Search contacts by name, phone, or email
- **Sorting**: Sort contacts by name or phone number
- **Data Export**: Export contacts to CSV file
- **Data Import**: Import contacts from CSV file
- **Statistics**: View contact statistics and validation info

### Input Validation
- Email format validation (contains @ and .)
- Phone number validation (10-15 digits)
- Empty field validation
- Duplicate contact prevention

## Project Structure

```
ContactVault/
├── Contact.java           # Contact model class with encapsulation
├── ContactManager.java    # Business logic and CRUD operations
├── ContactVault.java      # Main application with user interface
└── README.md             # Project documentation
```

## Classes Overview

### Contact.java
- **Purpose**: Model class representing a single contact
- **Features**: 
  - Encapsulation with private fields and public getters/setters
  - Input validation methods
  - Search matching functionality
  - Equals/hashCode for duplicate detection
  - CSV export format

### ContactManager.java
- **Purpose**: Manages collection of contacts using ArrayList
- **Features**:
  - Complete CRUD operations
  - Search and sort functionality
  - File import/export operations
  - Duplicate validation
  - Data persistence

### ContactVault.java
- **Purpose**: Main application class with console UI
- **Features**:
  - Interactive menu system
  - User input handling
  - Error handling and validation
  - Formatted output display

## How to Run

1. **Compile the Java files:**
   ```bash
   javac *.java
   ```

2. **Run the application:**
   ```bash
   java ContactVault
   ```

3. **Follow the interactive menu** to perform various operations

## Usage Examples

### Adding a Contact
```
Enter name: John Doe
Enter phone: 1234567890
Enter email: john.doe@email.com
```

### Searching Contacts
```
Enter search keyword: john
# Will find contacts with "john" in name, phone, or email
```

### Exporting Data
```
Enter filename: my_contacts
# Creates my_contacts.csv with all contact data
```

## Skills Demonstrated

### Object-Oriented Programming (OOP)
- **Encapsulation**: Private fields with public getter/setter methods
- **Abstraction**: Clear separation of concerns between classes
- **Modularity**: Well-organized class structure

### Data Structures
- **ArrayList**: Dynamic array for flexible contact storage
- **Collections**: Sorting and searching operations

### Input/Output Operations
- **File I/O**: CSV export and import functionality
- **Console I/O**: Interactive user interface

### Error Handling
- **Input Validation**: Comprehensive validation for all user inputs
- **Exception Handling**: Proper handling of file operations and user input

### Search and Sort Algorithms
- **Linear Search**: Keyword-based contact searching
- **Sorting**: Name and phone-based sorting using Comparator

## CSV File Format

The application exports/imports data in the following CSV format:
```csv
Name,Phone,Email
"John Doe","1234567890","john.doe@email.com"
"Jane Smith","0987654321","jane.smith@email.com"
```

## Future Enhancements

- Add contact groups/categories
- Implement contact photos
- Add birthday reminders
- Database integration
- GUI interface using JavaFX or Swing
- REST API for web integration

## Learning Outcomes

This project covers essential Java programming concepts:
- Object-oriented design principles
- ArrayList operations and manipulation
- File I/O operations
- String manipulation and validation
- User interface design
- Error handling and validation
- Search and sort algorithms

Perfect for beginners learning Java fundamentals and intermediate programmers practicing OOP concepts!
