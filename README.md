# Pharmacy-Managment-System

A comprehensive Java-based Pharmacy Management System developed for ASU Pharmacy. This system provides a user-friendly interface for managing pharmacy operations including sales, inventory, and equipment rental.

## Features

### Sales Management
- Search and add items to cart
- Apply promotional codes
- Multiple payment methods support
- Detailed receipt generation
- Member management system

### Inventory Management
- Real-time stock tracking
- Low stock alerts
- Expiry date monitoring
- Item categorization (Drugs, Healthcare Products)
- Stock status indicators

### Equipment Rental
- Equipment availability tracking
- Rental period management
- Return processing
- Rental receipt generation
- Equipment condition monitoring

## Technical Details

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Java Swing library (included in JDK)

### Dependencies
- No external dependencies required
- Uses standard Java libraries

### Project Structure
```
src/main/java/com/mycompany/pharmacymanagment/
├── App.java                 # Main application class
├── PharmacyItems.java       # Base class for pharmacy items
├── Drugs.java              # Drugs class implementation
├── HealthCareProduct.java   # Healthcare products class
├── Equipment.java          # Equipment class for rentals
├── Member.java             # Member management class
├── Sellable.java           # Interface for sellable items
└── Rentable.java           # Interface for rentable items
```

## Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/pharmacy-management.git
```

2. Navigate to the project directory:
```bash
cd pharmacy-management
```

3. Compile the Java files:
```bash
javac src/main/java/com/mycompany/pharmacymanagment/*.java
```

4. Run the application:
```bash
java -cp src/main/java com.mycompany.pharmacymanagment.App
```

## Usage

### Sales Process
1. Search for items using the search bar
2. Select quantity and add to cart
3. Apply promotional code if available
4. Select payment method
5. Process payment and receive receipt

### Inventory Management
1. View current stock levels
2. Monitor low stock items
3. Track expiry dates
4. Update inventory as needed

### Equipment Rental
1. Select equipment to rent
2. Choose rental period
3. Process rental
4. Generate rental receipt
5. Handle returns

## Promotional Codes
- ENG.ABRAR: 50% discount
- ENG.SAWSAN: 50% discount
- ENG.YUSRA: 50% discount

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



## Acknowledgments

- Developed for ASU Pharmacy
- Special thanks to the development team
- Inspired by real-world pharmacy management needs


