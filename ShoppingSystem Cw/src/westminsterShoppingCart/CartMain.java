package westminsterShoppingCart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import westminsterShoppingManager.Clothing;
import westminsterShoppingManager.Electronics;
import westminsterShoppingManager.Product;
import westminsterShoppingManager.WestminsterShoppingManager;

public class CartMain extends JFrame implements ActionListener {


    static String productIDValue;
    static String productCategory;
    static String productnameValue;
    static String productAvailability;
    static String price;
    static JTable table = new JTable();

    // static ArrayList<ShoppingCart> cart = new ArrayList<>();
    static Electronics electro;
    static WestminsterShoppingManager manager = new WestminsterShoppingManager();
    static List<Product> loadedList = manager.loadProductsFromFile("productsFile.txt");
    static List<Electronics> loadedList2 = manager.loadElectronicsFromFile("electronicList.txt.txt");
    static List<Clothing> loadedList3 = manager.loadClothingFromFile("Clothing.txt.txt");
    static JComboBox<?> comboBox;

    @SuppressWarnings("serial")
    public static void main(String[] args) {

//		SwingUtilities.invokeLater(() -> {
//			createAndShowGUI();
//		});


        File file = new File("productsFile.txt");

        String option = JOptionPane.showInputDialog("Are you a new user? (yes/no)");

        if (option != null && option.equalsIgnoreCase("yes")) {
            // New User
            String newName = JOptionPane.showInputDialog("Enter New User name : ");
            String newPassword = JOptionPane.showInputDialog("Enter New Password : ");
            User newUser = new User(newName, newPassword);
            // Save new user data as needed
            System.out.println("New User created: " + newUser);
        } else if (option != null && option.equalsIgnoreCase("no")) {
            // Existing User
            String existingName = JOptionPane.showInputDialog("Enter User name : ");
            String existingPassword = JOptionPane.showInputDialog("Enter Password : ");
            // Validate existing user credentials as needed
            System.out.println("Existing User logged in: " + existingName);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid option. Please enter 'yes' or 'no'.");
        }




        // Table set up

        Object[] columns = { "Product ID", "Name", "Category", "price", "info(Available items)" };
        DefaultTableModel model = new DefaultTableModel();


        // Labels
        JLabel label = new JLabel();
        label.setText("Select Product Category");
        label.setBounds(120, 9, 150, 40);
        // JPanel for table
        JPanel tbl = new JPanel();
        tbl.setBackground(Color.white);
        tbl.setBounds(100, 100, 757, 610);
        tbl.setLayout(new BorderLayout());
        tbl.add(new JScrollPane(table), BorderLayout.CENTER);
        model.setColumnIdentifiers(columns);
        table.setBounds(100, 100, 757, 610);
        table.setModel(model);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);

        // Combo BOX
        String[] category = { "All", "Electronics", "Clothing" };
        comboBox = new JComboBox(category);
        comboBox.setBounds(270, 20, 90, 40);
        comboBox.add(tbl);
        comboBox.addActionListener(comboBox);
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == comboBox) {
                    model.setRowCount(0);

                    String selectedCategory = comboBox.getSelectedItem().toString();

                    for (Product p : loadedList) {
                        String type;
                        if (selectedCategory.equals("All")) {
                            // Display all products
                            if (p instanceof Electronics) {
                                type = "Electronics";
                            } else if (p instanceof Clothing) {
                                type = "Clothing";
                            } else {
                                type = "unknown";
                            }
                        } else if (selectedCategory.equals("Electronics") && p instanceof Electronics) {
                            // display only electronics
                            type = "Electronics";
                        } else if (selectedCategory.equals("Clothing") && p instanceof Clothing) {
                            // display only Clothing
                            type = "Clothing";
                        } else {
                            // Unkown
                            continue;
                        }

                        Object[] rowData = { p.getProductId(), p.getProductName(), type, p.getPrice(),
                                p.getNoOfAvailableItems() };
                        // Check if available items is less than 3, and set the row color to red
                        if (p.getNoOfAvailableItems() < 3) {
                            // Using a custom renderer to set the row color to red
                            model.addRow(rowData);
                        } else {
                            model.addRow(rowData);
                        }
                        // Set a custom renderer for the last column (NoOfAvailableItems)
                        TableColumnModel columnModel = table.getColumnModel();
                        columnModel.getColumn(4).setCellRenderer(new CustomCellRenderer());

                    }
                }
            }
        });
        // Set default selection to "All"
        comboBox.setSelectedItem("All");

        // Shopping Cart Window
        JButton btn = new JButton("Shopping Cart");
        btn.setBounds(560, 6, 120, 30);
        ShoppingCart shop = new ShoppingCart();
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                shop.createAndShow();
            }

        });
        // Add to cart button
        JButton addToCart = new JButton("Add To Cart");
        addToCart.setBounds(270, 210, 130, 30);
        addToCart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub


            }

        });

        // Setting Panel
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 100));
        header.setLayout(null);

        // Details of product
        JLabel Details = new JLabel("Product Details");
        Details.setBounds(45, 10, 400, 40);

        JLabel ProductID = new JLabel("Product ID :");
        ProductID.setBounds(60, 40, 400, 40);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        Object value = table.getValueAt(selectedRow, 0);
                        if (value != null) {
                            productIDValue = value.toString();
                            ProductID.setText("Product ID: " + productIDValue);
                        } else {
                            ProductID.setText("Product ID: N/A");
                        }
                    } else {
                        // Handle the case when no row is selected or the selectedRow index is invalid
                        ProductID.setText("Product ID: N/A");
                    }
                }
            }

        });

        JLabel cat = new JLabel("Product category: ");
        cat.setBounds(60, 70, 400, 40);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        Object value = table.getValueAt(selectedRow, 2);
                        if (value != null) {
                            productCategory = value.toString();
                            cat.setText("Product category: " + productCategory);
                        } else {
                            cat.setText("Product category: N/A");
                        }
                    } else {
                        // Handle the case when no row is selected or the selectedRow index is invalid
                        cat.setText("Product category: N/A");
                    }
                }
            }

        });

        JLabel ProductName = new JLabel("Product name :");
        ProductName.setBounds(60, 100, 400, 40);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        Object value = table.getValueAt(selectedRow, 1);
                        if (value != null) {
                            productnameValue = value.toString();
                            ProductName.setText("Product name: " + productnameValue);
                        } else {
                            ProductName.setText("Product Name: N/A");
                        }
                    } else {
                        // Handle the case when no row is selected or the selectedRow index is invalid
                        ProductName.setText("Product Name: N/A");
                    }
                }
            }

        });

        JLabel availability = new JLabel("No of product avilable: ");
        availability.setBounds(60, 130, 400, 40);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        Object value = table.getValueAt(selectedRow, 4);
                        if (value != null) {
                            productAvailability = value.toString();
                            availability.setText("No of product avilable: " + productAvailability);
                        } else {
                            availability.setText("No of product avilable: N/A");
                        }
                    } else {
                        // Handle the case when no row is selected or the selectedRow index is invalid
                        availability.setText("No of product avilable: N/A");
                    }
                }
            }

        });


        JLabel price2 = new JLabel("Price ");
        price2.setBounds(60, 160, 400, 40);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        Object value = table.getValueAt(selectedRow, 3);
                        if (value != null) {
                            price = value.toString();
                            price2.setText("Price: " + price2);
                        } else {
                            price2.setText("Price: N/A");
                        }
                    } else {
                        // Handle the case when no row is selected or the selectedRow index is invalid
                        price2.setText("Price: N/A");
                    }
                }
            }

        });

        // JPanel for Selected details section
        JPanel selected = new JPanel();
        selected.setBackground(Color.white);
        selected.setPreferredSize(new Dimension(100, 250));
        selected.setLayout(null);

        // Setting up JFrame
        JFrame frame = new JFrame("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 700);
        header.add(label);
        header.add(comboBox);
        header.add(btn);
        frame.add(header, BorderLayout.NORTH);
        frame.add(selected, BorderLayout.SOUTH);
        frame.add(tbl, BorderLayout.CENTER);
        selected.add(Details);
        selected.add(ProductID);
        selected.add(ProductName);
        selected.add(cat);
        selected.add(availability);
        selected.add(addToCart);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    static void jTableMouseClicked(java.awt.event.MouseEvent evt) {
        boolean ifClicked = table.isEditing();
        if (ifClicked == false) {
            JOptionPane.showMessageDialog(table, "You can't edit");
        }
    }

}


class CustomCellRenderer extends DefaultTableCellRenderer {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Check the condition for the last column (NoOfAvailableItems)
        if ((int) value < 3) {
            component.setForeground(Color.RED);
        } else {
            component.setForeground(table.getForeground());
        }

        return component;
    }
}

