package com.mycompany.pharmacymanagment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    private static JFrame frame;
    private static DefaultTableModel cartTableModel;
    private static DefaultTableModel inventoryTableModel;
    private static JLabel totalLabel;
    private static ArrayList<PharmacyItems> pharmacyItems;
    private static ArrayList<PharmacyItems> cartItems;
 
    private static ArrayList<Member> memberList;
    private static Member currentMember;
    private static double total = 0.0;
    private static JTabbedPane tabbedPane;
    private static JTextField prescriptionField;
    private static double discountPercentage = 0.0;
    private static String appliedPromoCode = "";
    private static ArrayList<Equipment> rentalItems;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        pharmacyItems = new ArrayList<>();
        cartItems = new ArrayList<>();
        memberList = new ArrayList<>();
        initializeSampleData();
        createAndShowGUI();
    }

    private static void initializeSampleData() {
        try {
            // Drugs
            pharmacyItems.add(new Drugs(10, "Aspirin", 100, LocalDate.of(2026, 5, 12), "Acetylsalicylic Acid", "D001", false, 50, 4));
            pharmacyItems.add(new Drugs(11, "Ibuprofen", 200, LocalDate.of(2026, 8, 20), "Ibuprofen", "D002", true, 30, 5));
            pharmacyItems.add(new Drugs(12, "Paracetamol", 75, LocalDate.of(2026, 7, 15), "Acetaminophen", "D003", false, 40, 4));
            pharmacyItems.add(new Drugs(13, "Amoxicillin", 150, LocalDate.of(2026, 6, 30), "Amoxicillin", "D004", true, 25, 3));
            pharmacyItems.add(new Drugs(14, "Omeprazole", 180, LocalDate.of(2026, 9, 10), "Omeprazole", "D005", true, 20, 4));
            pharmacyItems.add(new Drugs(15, "Cetirizine", 90, LocalDate.of(2026, 8, 5), "Cetirizine", "D006", false, 35, 3));
            pharmacyItems.add(new Drugs(16, "Metformin", 160, LocalDate.of(2026, 7, 25), "Metformin HCl", "D007", true, 30, 4));
            pharmacyItems.add(new Drugs(17, "Lisinopril", 170, LocalDate.of(2026, 6, 15), "Lisinopril", "D008", true, 25, 3));
            
            // Health Care Products
            pharmacyItems.add(new HealthCareProduct(3, "Vitamin C", 15.0, LocalDate.of(2026, 12, 31), 30, "Nature's Way", "Supplement", 10));
            pharmacyItems.add(new HealthCareProduct(18, "Vitamin D3", 20.0, LocalDate.of(2026, 11, 30), 25, "Nature's Bounty", "Supplement", 8));
            pharmacyItems.add(new HealthCareProduct(19, "Calcium + D3", 25.0, LocalDate.of(2026, 10, 31), 20, "GNC", "Supplement", 12));
            pharmacyItems.add(new HealthCareProduct(20, "Omega-3 Fish Oil", 30.0, LocalDate.of(2026, 9, 30), 40, "Nordic Naturals", "Supplement", 15));
            pharmacyItems.add(new HealthCareProduct(21, "Multivitamin", 35.0, LocalDate.of(2026, 8, 31), 35, "Centrum", "Supplement", 20));
            pharmacyItems.add(new HealthCareProduct(22, "First Aid Kit", 45.0, LocalDate.of(2026, 12, 31), 15, "Johnson & Johnson", "Medical Supply", 10));
            pharmacyItems.add(new HealthCareProduct(23, "Hand Sanitizer", 8.0, LocalDate.of(2026, 11, 30), 50, "Purell", "Hygiene", 30));
            pharmacyItems.add(new HealthCareProduct(24, "Face Masks (50pc)", 25.0, LocalDate.of(2026, 12, 31), 40, "3M", "Medical Supply", 25));
            pharmacyItems.add(new HealthCareProduct(25, "Digital Thermometer", 40.0, LocalDate.of(2026, 12, 31), 20, "Omron", "Medical Device", 15));
            pharmacyItems.add(new HealthCareProduct(26, "Blood Pressure Monitor", 85.0, LocalDate.of(2026, 12, 31), 10, "Omron", "Medical Device", 8));
            pharmacyItems.add(new HealthCareProduct(27, "Glucose Test Strips", 30.0, LocalDate.of(2026, 8, 31), 30, "OneTouch", "Medical Supply", 20));
            pharmacyItems.add(new HealthCareProduct(28, "Compression Socks", 15.0, LocalDate.of(2026, 12, 31), 25, "Futuro", "Medical Supply", 15));
            
            // Equipment for Rental
            rentalItems = new ArrayList<>();
            rentalItems.add(new Equipment(4, "Wheelchair", 5, "EQ001", "Standard Manual", 20.0, "Good", 10));
            rentalItems.add(new Equipment(5, "Stethoscope", 8, "EQ002", "Professional", 15.0, "Excellent", 12));
            rentalItems.add(new Equipment(29, "Hospital Bed", 3, "EQ003", "Semi-Electric", 50.0, "Good", 5));
            rentalItems.add(new Equipment(30, "Oxygen Concentrator", 4, "EQ004", "5L Portable", 40.0, "Excellent", 6));
            rentalItems.add(new Equipment(31, "CPAP Machine", 5, "EQ005", "Auto-Adjusting", 35.0, "Good", 8));
            rentalItems.add(new Equipment(32, "Walker", 10, "EQ006", "Folding", 15.0, "Good", 15));
            rentalItems.add(new Equipment(33, "Crutches", 15, "EQ007", "Adjustable", 10.0, "Good", 20));
            rentalItems.add(new Equipment(34, "Nebulizer", 6, "EQ008", "Compressor", 25.0, "Excellent", 10));
            rentalItems.add(new Equipment(35, "Patient Lift", 3, "EQ009", "Hydraulic", 45.0, "Good", 4));
            rentalItems.add(new Equipment(36, "Commode Chair", 8, "EQ010", "Portable", 18.0, "Good", 12));
            
            // Initialize sample members
            memberList.add(new Member(1, "John Doe", "555-1234"));
            memberList.add(new Member(2, "Jane Smith", "555-5678"));
            memberList.add(new Member(3, "Bob Johnson", "555-9012"));
            memberList.add(new Member(4, "Sarah Wilson", "555-3456"));
            memberList.add(new Member(5, "Michael Brown", "555-7890"));
        } catch (Exception e) {
            System.out.println("Error initializing data: " + e.getMessage());
        }
    }

    private static void createAndShowGUI() {
        frame = new JFrame("ASU Pharmacy Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout(10, 10));

        // Header
        frame.add(createHeaderPanel(), BorderLayout.NORTH);

        // Tabbed Pane for different views
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sales", createSalesPanel());
        tabbedPane.addTab("Inventory", createInventoryPanel());
        tabbedPane.addTab("Equipment Rental", createRentalPanel());
        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("ASU Pharmacy Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel dateLabel = new JLabel("Date: " + LocalDate.now().toString());
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(dateLabel);
        
        return headerPanel;
    }

    private static JPanel createSalesPanel() {
        JPanel salesPanel = new JPanel(new BorderLayout(10, 10));
        salesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top controls
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton addToCartButton = new JButton("Add to Cart");
        
        controlsPanel.add(new JLabel("Search: "));
        controlsPanel.add(searchField);
        controlsPanel.add(searchButton);
        controlsPanel.add(new JLabel("Quantity: "));
        controlsPanel.add(quantitySpinner);
        controlsPanel.add(addToCartButton);

        // Cart Table
        cartTableModel = new DefaultTableModel(
            new String[]{"Item", "Type", "Quantity", "Price", "Expiry Date"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable cartTable = new JTable(cartTableModel);
        JScrollPane cartScroll = new JScrollPane(cartTable);

        // Bottom panel with promo code
        JPanel bottomPanel = new JPanel(new BorderLayout());
        
        // Totals panel
        JPanel totalsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("Total: $0.00");
        totalsPanel.add(totalLabel);

        // Payment panel with promo code
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField promoField = new JTextField(10);
        promoField.setToolTipText("Enter promo code");
        JButton applyPromoButton = new JButton("Apply Promo");
        String[] paymentMethods = {"Cash", "Credit Card", "Debit Card"};
        JComboBox<String> paymentCombo = new JComboBox<>(paymentMethods);
        JButton processButton = new JButton("Process Payment");
        JButton clearButton = new JButton("Clear Cart");
        
        paymentPanel.add(new JLabel("Promo Code:"));
        paymentPanel.add(promoField);
        paymentPanel.add(applyPromoButton);
        paymentPanel.add(new JLabel("Payment:"));
        paymentPanel.add(paymentCombo);
        paymentPanel.add(processButton);
        paymentPanel.add(clearButton);

        bottomPanel.add(totalsPanel, BorderLayout.NORTH);
        bottomPanel.add(paymentPanel, BorderLayout.CENTER);

        salesPanel.add(controlsPanel, BorderLayout.NORTH);
        salesPanel.add(cartScroll, BorderLayout.CENTER);
        salesPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add event handlers
        searchButton.addActionListener(e -> handleSearch(searchField.getText()));
        addToCartButton.addActionListener(e -> handleAddToCart(searchField.getText(), (Integer)quantitySpinner.getValue()));
        processButton.addActionListener(e -> handleProcessPayment(paymentCombo.getSelectedItem().toString()));
        clearButton.addActionListener(e -> handleClearCart());
        applyPromoButton.addActionListener(e -> applyPromoCode(promoField.getText()));

        return salesPanel;
    }

    private static JPanel createInventoryPanel() {
        JPanel inventoryPanel = new JPanel(new BorderLayout(10, 10));
        inventoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inventory Table
        inventoryTableModel = new DefaultTableModel(
            new String[]{"ID", "Name", "Type", "Quantity", "Price", "Expiry Date", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable inventoryTable = new JTable(inventoryTableModel);
        
        // Custom cell renderer to highlight out of stock items
        inventoryTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Get the quantity and status values
                int quantity = Integer.parseInt(table.getModel().getValueAt(row, 3).toString());
                String status = table.getModel().getValueAt(row, 6).toString();
                
                // Set background color based on quantity
                if (quantity == 0 || status.equals("Out of Stock")) {
                    c.setBackground(new Color(255, 200, 200)); // Light red for out of stock
                    c.setForeground(Color.RED);
                } else if (quantity < 5) {
                    c.setBackground(new Color(255, 255, 200)); // Light yellow for low stock
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                    c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
                }
                
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        // Add quantity spinner and add to cart button
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton addToCartButton = new JButton("Add to Cart");
        
        controlPanel.add(new JLabel("Quantity: "));
        controlPanel.add(quantitySpinner);
        controlPanel.add(addToCartButton);

        // Add button click listener
        addToCartButton.addActionListener(e -> {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow >= 0) {
                int quantity = Integer.parseInt(inventoryTable.getValueAt(selectedRow, 3).toString());
                if (quantity == 0) {
                    JOptionPane.showMessageDialog(frame, "This item is out of stock!", "Out of Stock", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String itemName = inventoryTable.getValueAt(selectedRow, 1).toString();
                int spinnerQuantity = (Integer) quantitySpinner.getValue();
                handleAddToCart(itemName, spinnerQuantity);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an item from the inventory");
            }
        });

        // Update inventory table
        updateInventoryTable();

        inventoryPanel.add(scrollPane, BorderLayout.CENTER);
        inventoryPanel.add(controlPanel, BorderLayout.SOUTH);
        return inventoryPanel;
    }

    private static JPanel createRentalPanel() {
        JPanel rentalPanel = new JPanel(new BorderLayout(10, 10));
        rentalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel rentalTableModel = new DefaultTableModel(
            new String[]{"Equipment ID", "Name", "Model", "Condition", "Rental Price/Day", "Available Quantity"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Add equipment to table
        for (Equipment equipment : rentalItems) {
            rentalTableModel.addRow(new Object[]{
                equipment.getEquipmentID(),
                equipment.getName(),
                equipment.getModel(),
                equipment.getCondition(),
                String.format("$%.2f", equipment.getRentalPrice()),
                equipment.getQuantity()
            });
        }

        JTable rentalTable = new JTable(rentalTableModel);
        JScrollPane scrollPane = new JScrollPane(rentalTable);
        rentalPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JSpinner daysSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton rentButton = new JButton("Rent Selected");
        JButton returnButton = new JButton("Return Equipment");

        controlPanel.add(new JLabel("Days: "));
        controlPanel.add(daysSpinner);
        controlPanel.add(new JLabel("Quantity: "));
        controlPanel.add(quantitySpinner);
        controlPanel.add(rentButton);
        controlPanel.add(returnButton);
        rentalPanel.add(controlPanel, BorderLayout.SOUTH);

        // Add event handlers
        rentButton.addActionListener(e -> {
            int selectedRow = rentalTable.getSelectedRow();
            if (selectedRow >= 0) {
                String equipmentId = rentalTable.getValueAt(selectedRow, 0).toString();
                Equipment selectedEquipment = null;
                
                // Find the selected equipment
                for (Equipment equipment : rentalItems) {
                    if (equipment.getEquipmentID().equals(equipmentId)) {
                        selectedEquipment = equipment;
                        break;
                    }
                }
                
                if (selectedEquipment == null) {
                    JOptionPane.showMessageDialog(frame, "Equipment not found.");
                    return;
                }

                int availableQuantity = selectedEquipment.getQuantity();
                if (availableQuantity <= 0) {
                    JOptionPane.showMessageDialog(frame, "This equipment is not available for rent.");
                    return;
                }

                // Show member selection dialog first
                showMemberDialog();
                if (currentMember == null) {
                    JOptionPane.showMessageDialog(frame, "Please select a member to proceed with rental");
                    return;
                }

                int days = (Integer) daysSpinner.getValue();
                int quantity = (Integer) quantitySpinner.getValue();
                
                if (quantity > availableQuantity) {
                    JOptionPane.showMessageDialog(frame, 
                        "Cannot rent " + quantity + " items. Only " + availableQuantity + " available.", 
                        "Insufficient Stock", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double pricePerDay = selectedEquipment.getRentalPrice();
                double totalCost = pricePerDay * days * quantity;

                // Create rental receipt
                StringBuilder receipt = new StringBuilder();
                receipt.append("=== RENTAL RECEIPT ===\n\n");
                receipt.append("Store ASU Pharmacy\n");
                receipt.append("ABDU BASHA\n");
                receipt.append("Cairo, Egypt\n\n");
                receipt.append("Date: ").append(LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy"))).append("\n");
                receipt.append("Member: ").append(currentMember.getName()).append("\n");
                receipt.append("-".repeat(40)).append("\n\n");
                receipt.append("Equipment ID: ").append(equipmentId).append("\n");
                receipt.append("Equipment: ").append(selectedEquipment.getName()).append("\n");
                receipt.append("Model: ").append(selectedEquipment.getModel()).append("\n");
                receipt.append("Quantity: ").append(quantity).append("\n");
                receipt.append("Rental Period: ").append(days).append(" days\n");
                receipt.append("Price per Day: $").append(String.format("%.2f", pricePerDay)).append("\n");
                receipt.append("-".repeat(40)).append("\n");
                receipt.append("Total Cost: $").append(String.format("%.2f", totalCost)).append("\n");
                receipt.append("-".repeat(40)).append("\n\n");
                receipt.append("Return Due: ").append(LocalDate.now().plusDays(days)
                        .format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy"))).append("\n\n");
                receipt.append("THANK YOU FOR RENTING FROM ASU PHARMACY\n");
                receipt.append("-".repeat(40));

                // Show confirmation dialog with receipt
                JTextArea textArea = new JTextArea(receipt.toString());
                textArea.setEditable(false);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                JScrollPane receiptPane = new JScrollPane(textArea);
                receiptPane.setPreferredSize(new Dimension(400, 500));

                int option = JOptionPane.showConfirmDialog(frame, receiptPane, 
                    "Confirm Rental", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    // Update equipment quantity
                    selectedEquipment.setQuantity(availableQuantity - quantity);
                    
                    // Update table
                    rentalTableModel.setValueAt(availableQuantity - quantity, selectedRow, 5);
                    
                    JOptionPane.showMessageDialog(frame, "Rental processed successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select equipment to rent");
            }
        });

        returnButton.addActionListener(e -> {
            String equipmentId = JOptionPane.showInputDialog(frame, "Enter Equipment ID:");
            if (equipmentId == null || equipmentId.trim().isEmpty()) {
                return;
            }

            for (Equipment equipment : rentalItems) {
                if (equipment.getEquipmentID().equals(equipmentId)) {
                    int returnQuantity = Integer.parseInt(JOptionPane.showInputDialog(frame, 
                        "Enter quantity to return:", "1"));
                    if (returnQuantity > 0) {
                        equipment.setQuantity(equipment.getQuantity() + returnQuantity);
                        // Update table
                        for (int i = 0; i < rentalTableModel.getRowCount(); i++) {
                            if (rentalTableModel.getValueAt(i, 0).equals(equipmentId)) {
                                rentalTableModel.setValueAt(equipment.getQuantity(), i, 5);
                                break;
                            }
                        }
                        JOptionPane.showMessageDialog(frame, "Equipment returned successfully!");
                    }
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame, "Equipment not found!");
        });

        return rentalPanel;
    }

    private static void handleSearch(String searchText) {
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a search term");
            return;
        }

        StringBuilder results = new StringBuilder("Search Results:\n\n");
        boolean found = false;

        for (PharmacyItems item : pharmacyItems) {
            if (item.getName().toLowerCase().contains(searchText.toLowerCase())) {
                results.append(item.printDetails()).append("\n");
                found = true;
            }
        }

        if (!found) {
            results.append("No items found matching: ").append(searchText);
        }

        JTextArea textArea = new JTextArea(results.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(frame, scrollPane, "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void handleAddToCart(String itemName, int quantity) {
        if (itemName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter an item name");
            return;
        }

        for (PharmacyItems item : pharmacyItems) {
            if (item.getName().toLowerCase().contains(itemName.toLowerCase())) {
                try {
                    if (item instanceof Drugs) {
                        Drugs drug = (Drugs) item;
                        if (drug.getExpiryDate().isBefore(LocalDate.now())) {
                            JOptionPane.showMessageDialog(frame, "This drug has expired!");
                            return;
                        }
                    }
                    
                    // Check if item already exists in cart
                    boolean itemFound = false;
                    for (PharmacyItems cartItem : cartItems) {
                        if (cartItem.getName().equals(item.getName())) {
                            // Check if enough stock available for additional quantity
                            int currentCartQuantity = ((Sellable)cartItem).getquantitysold();
                            int requestedTotal = currentCartQuantity + quantity;
                            
                            if (item.getQuantity() < quantity) {
                                JOptionPane.showMessageDialog(frame, "Not enough stock! Available: " + item.getQuantity());
                                return;
                            }
                            
                            // Update quantity of existing cart item
                            if (cartItem instanceof Drugs) {
                                Drugs drug = (Drugs) cartItem;
                                drug.setQuantity(requestedTotal);
                            } else if (cartItem instanceof HealthCareProduct) {
                                HealthCareProduct product = (HealthCareProduct) cartItem;
                                product.setQuantity(requestedTotal);
                            }
                            // Decrease inventory quantity
                            item.setQuantity(item.getQuantity() - quantity);
                            itemFound = true;
                            break;
                        }
                    }

                    if (!itemFound) {
                        // Check if enough stock available for new item
                        if (item.getQuantity() < quantity) {
                            JOptionPane.showMessageDialog(frame, "Not enough stock! Available: " + item.getQuantity());
                            return;
                        }
                        
                        // Create a new cart item
                        PharmacyItems cartItem;
                        if (item instanceof Drugs) {
                            Drugs drug = (Drugs) item;
                            cartItem = new Drugs(drug.getItemId(), drug.getName(), drug.getPrice(), 
                                              drug.getExpiryDate(), drug.getGenericname(), drug.getDrugID(), 
                                              drug.RequiresPrescription(), quantity, quantity);
                        } else if (item instanceof HealthCareProduct) {
                            HealthCareProduct product = (HealthCareProduct) item;
                            cartItem = new HealthCareProduct(product.getItemId(), product.getName(), 
                                                          product.getPrice(), product.getExpiryDate(), 
                                                          quantity, product.getBrand(), product.getType(), quantity);
                        } else {
                            cartItem = item;
                        }
                        cartItems.add(cartItem);
                        // Decrease inventory quantity
                        item.setQuantity(item.getQuantity() - quantity);
                    }

                    updateCartTable();
                    updateInventoryTable();
                    return;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Error adding item: " + e.getMessage());
                }
            }
        }
        
        JOptionPane.showMessageDialog(frame, "Item not found: " + itemName);
    }

    private static void updateCartTable() {
        cartTableModel.setRowCount(0);
        total = 0.0;

        for (PharmacyItems item : cartItems) {
            if (item instanceof Sellable) {
                Sellable sellable = (Sellable) item;
                String expiryDate = "";
                
                if (item instanceof Drugs) {
                    Drugs drug = (Drugs) item;
                    expiryDate = drug.getExpiryDate().toString();
                } else if (item instanceof HealthCareProduct) {
                    HealthCareProduct product = (HealthCareProduct) item;
                    expiryDate = product.getExpiryDate().toString();
                }

                cartTableModel.addRow(new Object[]{
                    item.getName(),
                    item.getClass().getSimpleName(),
                    sellable.getquantitysold(),
                    sellable.getPrice(),
                    expiryDate
                });
                total += sellable.getPrice() * sellable.getquantitysold();
            }
        }

        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    private static void updateInventoryTable() {
        inventoryTableModel.setRowCount(0);

        for (PharmacyItems item : pharmacyItems) {
            String type = item.getClass().getSimpleName();
            String expiryDate = "N/A";
            String status = "In Stock";

            if (item instanceof Drugs) {
                Drugs drug = (Drugs) item;
                expiryDate = drug.getExpiryDate().toString();
                if (drug.getExpiryDate().isBefore(LocalDate.now())) {
                    status = "Expired";
                }
            }

            // Check quantity and update status
            if (item.getQuantity() == 0) {
                status = "Out of Stock";
            } else if (item.getQuantity() < 5) {
                status = "Low Stock";
            }

            double price = 0.0;
            if (item instanceof Sellable) {
                price = ((Sellable) item).getPrice();
            } else if (item instanceof Rentable) {
                price = ((Rentable) item).getRentalPrice();
            }

            inventoryTableModel.addRow(new Object[]{
                item.getItemId(),
                item.getName(),
                type,
                item.getQuantity(),
                price,
                expiryDate,
                status
            });
        }
    }

    private static void handleRentEquipment(int selectedRow) {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(frame, "Please select equipment to rent");
            return;
        }

        String equipmentId = (String) inventoryTableModel.getValueAt(selectedRow, 0);
        for (PharmacyItems item : pharmacyItems) {
            if (item instanceof Equipment && ((Equipment) item).getEquipmentID().equals(equipmentId)) {
                try {
                    ((Equipment) item).rent();
                    updateInventoryTable();
                    JOptionPane.showMessageDialog(frame, "Equipment rented successfully!");
                    return;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Error renting equipment: " + e.getMessage());
                    return;
                }
            }
        }
    }

    private static void handleReturnEquipment() {
        String equipmentId = JOptionPane.showInputDialog(frame, "Enter Equipment ID:");
        if (equipmentId == null || equipmentId.trim().isEmpty()) {
            return;
        }

        for (PharmacyItems item : pharmacyItems) {
            if (item instanceof Equipment && ((Equipment) item).getEquipmentID().equals(equipmentId)) {
                ((Equipment) item).returnEquipment(1);
                updateInventoryTable();
                JOptionPane.showMessageDialog(frame, "Equipment returned successfully!");
                return;
            }
        }

        JOptionPane.showMessageDialog(frame, "Equipment not found!");
    }

    private static void showMemberDialog() {
        JDialog dialog = new JDialog(frame, "Select Member", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 300);

        // Create member list panel
        JPanel memberPanel = new JPanel(new BorderLayout(5, 5));
        DefaultListModel<String> memberListModel = new DefaultListModel<>();
        for (Member member : memberList) {
            memberListModel.addElement(member.getName() + " (" + member.getPhone() + ")");
        }
        JList<String> memberJList = new JList<>(memberListModel);
        memberJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(memberJList);
        memberPanel.add(scrollPane, BorderLayout.CENTER);

        // Create "Add New Member" panel
        JPanel newMemberPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        newMemberPanel.add(new JLabel("Name:"));
        newMemberPanel.add(nameField);
        newMemberPanel.add(new JLabel("Phone:"));
        newMemberPanel.add(phoneField);
        JButton addButton = new JButton("Add New Member");
        newMemberPanel.add(addButton);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton selectButton = new JButton("Select Member");
        buttonPanel.add(selectButton);

        // Add panels to dialog
        dialog.add(new JLabel("Select Existing Member:", SwingConstants.LEFT), BorderLayout.NORTH);
        dialog.add(memberPanel, BorderLayout.CENTER);
        dialog.add(newMemberPanel, BorderLayout.SOUTH);
        dialog.add(buttonPanel, BorderLayout.EAST);

        // Add button listeners
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter both name and phone number");
                return;
            }
            Member newMember = new Member(memberList.size() + 1, name, phone);
            memberList.add(newMember);
            memberListModel.addElement(newMember.getName() + " (" + newMember.getPhone() + ")");
            nameField.setText("");
            phoneField.setText("");
            JOptionPane.showMessageDialog(dialog, "New member added successfully!");
        });

        selectButton.addActionListener(e -> {
            int selectedIndex = memberJList.getSelectedIndex();
            if (selectedIndex >= 0) {
                currentMember = memberList.get(selectedIndex);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Please select a member or add a new one");
            }
        });

        // Center dialog on screen
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private static void applyPromoCode(String code) {
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a promo code");
            return;
        }
        
        // Simple promo code logic
        String message = "";
        
        switch (code.toUpperCase()) {
            case "ENG.ABRAR":
                discountPercentage = 0.50;
                message = "50% discount applied!";
                break;
            case "ENG.SAWSAN":
                discountPercentage = 0.50;
                message = "50% discount applied!";
                break;
            case "ENG.YUSRA":
                discountPercentage = 0.50;
                message = "50% discount applied!";
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid promo code");
                return;
        }
        
        appliedPromoCode = code.toUpperCase();
        // Apply discount
        updateCartTotal();
        JOptionPane.showMessageDialog(frame, message);
    }

    private static void updateCartTotal() {
        double subtotal = 0.0;
        for (PharmacyItems item : cartItems) {
            if (item instanceof Sellable) {
                Sellable sellable = (Sellable) item;
                subtotal += sellable.getPrice() * sellable.getquantitysold();
            }
        }
        
        // Apply discount if any
        if (discountPercentage > 0) {
            double discount = subtotal * discountPercentage;
            subtotal -= discount;
        }
        
        total = subtotal;
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    private static void handleProcessPayment(String paymentMethod) {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Cart is empty!");
            return;
        }

        // Show member selection dialog
        showMemberDialog();
        
        // Check if a member was selected
        if (currentMember == null) {
            JOptionPane.showMessageDialog(frame, "Please select or add a member to proceed with payment");
            return;
        }

        try {
            // Calculate totals
            double subtotal = 0.0;
            for (PharmacyItems item : cartItems) {
                if (item instanceof Sellable) {
                    Sellable sellable = (Sellable) item;
                    subtotal += sellable.getPrice() * sellable.getquantitysold();
                }
            }
            
            double discountAmount = 0.0;
            if (discountPercentage > 0) {
                discountAmount = subtotal * discountPercentage;
                subtotal -= discountAmount;
            }
            
            double tax = subtotal * 0.01; // 1% tax rate
            double finalTotal = subtotal + tax;

            // Create receipt
            StringBuilder receipt = new StringBuilder();
            
            // Store Information
            receipt.append(String.format("Store ASU Pharmacy\n"));
            receipt.append(String.format("ABDU BASHA\n"));
            receipt.append(String.format("Cairo, Egypt\n\n"));
            
            // Register and Cashier Info
            receipt.append(String.format("Register #6\n"));
            receipt.append(String.format("Cashier #%d     %s\n", 
                currentMember.getMemberId(),
                LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
            receipt.append("-".repeat(40)).append("\n\n");

            // Items
            for (PharmacyItems item : cartItems) {
                if (item instanceof Sellable) {
                    Sellable sellable = (Sellable) item;
                    int quantity = sellable.getquantitysold();
                    double price = sellable.getPrice() * quantity;
                    receipt.append(String.format("%-2d  %-20s  $%8.2f\n", 
                        quantity, item.getName(), price));
                }
            }

            // Separator
            receipt.append("-".repeat(40)).append("\n");

            // Totals section
            receipt.append(String.format("%-2d Items\n", cartItems.size()));
            receipt.append(String.format("Subtotal%29s%.2f\n", "$", subtotal + discountAmount));
            
            // Show discount if applied
            if (discountPercentage > 0) {
                receipt.append(String.format("Discount (%s)%22s%.2f\n", 
                    appliedPromoCode, "-$", discountAmount));
            }
            
            receipt.append(String.format("Tax (1%%)%30s%.2f\n", "$", tax));
            receipt.append(String.format("Total%32s%.2f\n", "$", finalTotal));
            receipt.append(String.format("%s%31s%.2f\n", paymentMethod, "$", finalTotal));
            receipt.append("-".repeat(40)).append("\n");

            // Payment section
            receipt.append(String.format("Tendered%29s%.2f\n", "$", finalTotal));
            receipt.append(String.format("Cash Change%26s%.2f\n", "$", 0.00));

            // Footer
            receipt.append("\n").append("-".repeat(40)).append("\n");
            receipt.append("THANK YOU FOR SHOPPING AT ASU PHARMACY\n");
            receipt.append("-".repeat(40)).append("\n");
            receipt.append("|".repeat(40)); // Barcode representation

            // Display receipt
            JTextArea textArea = new JTextArea(receipt.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 500));
            
            int option = JOptionPane.showConfirmDialog(frame, scrollPane, 
                "Confirm Payment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            
            if (option == JOptionPane.OK_OPTION) {
                // Clear cart and update displays
                cartItems.clear();
                updateCartTable();
                updateInventoryTable();
                currentMember = null; // Reset current member after successful payment
                discountPercentage = 0.0; // Reset discount
                appliedPromoCode = ""; // Reset promo code
                
                JOptionPane.showMessageDialog(frame, "Payment processed successfully!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error processing payment: " + e.getMessage());
        }
    }

    private static void handleClearCart() {
        cartItems.clear();
        updateCartTable();
        updateInventoryTable();
        discountPercentage = 0.0; // Reset discount when clearing cart
        appliedPromoCode = ""; // Reset promo code
        updateCartTotal(); // Update the total display
    }
}
