package westminsterShoppingManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * WestminsterShoppingManager is a class that implements the ShoppingManager interface
 * and provides functionality for managing a list of electronic and clothing products.
 */
public class WestminsterShoppingManager implements ShoppingManager {

    // Lists to store products, electronics, and clothing
    private List<Product> productList;
    private List<Electronics> electronicList;
    private List<Clothing> clothingList;

    // Maximum number of products allowed
    private static final int MaxProducts = 50;

    // Files to store product information
    private File file = new File("productsFile.txt");
    private File fileE = new File("electronicList.txt.txt");
    private File fileC = new File("Clothing.txt.txt");

    // Object output stream for saving products to files
    ObjectOutputStream oos = null;

    // Object input stream for loading products from files
    ObjectInputStream ois = null;

    /**
     * Constructor to initialize lists and set up the file paths.
     */
    public WestminsterShoppingManager() {
        this.setProductList(new ArrayList<Product>());
        this.setElectronicList(new ArrayList<Electronics>());
        this.setClothingList(new ArrayList<Clothing>());
    }

    // Getter and setter methods for the lists
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public static int getMaxproducts() {
        return MaxProducts;
    }

    public List<Clothing> getClothingList() {
        return clothingList;
    }

    public void setClothingList(List<Clothing> clothingList) {
        this.clothingList = clothingList;
    }

    public List<Electronics> getElectronicList() {
        return electronicList;
    }

    public void setElectronicList(List<Electronics> electronicList) {
        this.electronicList = electronicList;
    }

    /**
     * Load products from a file.
     *
     * @param filename The name of the file to load products from.
     * @return The list of loaded products.
     */
    @SuppressWarnings("unchecked")
    public List<Product> loadProductsFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            productList = (List<Product>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
        } catch (IOException | ClassNotFoundException e) {
            // Handle IO or class not found exceptions
        }
        return productList;
    }

    /**
     * Load electronics from a file.
     *
     * @param filename The name of the file to load electronics from.
     * @return The list of loaded electronics.
     */
    @SuppressWarnings("unchecked")
    public List<Electronics> loadElectronicsFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            electronicList = (List<Electronics>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
        } catch (IOException | ClassNotFoundException e) {
            // Handle IO or class not found exceptions
        }
        return electronicList;
    }

    /**
     * Load clothing from a file.
     *
     * @param filename The name of the file to load clothing from.
     * @return The list of loaded clothing.
     */
    @SuppressWarnings("unchecked")
    public List<Clothing> loadClothingFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            clothingList = (List<Clothing>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
        } catch (IOException | ClassNotFoundException e) {
            // Handle IO or class not found exceptions
        }
        return clothingList;
    }

    /**
     * Display the main menu options.
     */
    public void displayMenu() {
        System.out.println();
        System.out.println();
        System.out.println("     |----Main Menu----|");
        System.out.println();
        System.out.println("1. Enter 1 to Add an Item \n\n" +
                "2. Enter 2 to Remove an Item \n\n" +
                "3. Enter 3 to Print the list of products \n\n" +
                "4. Enter 4 to Save the changes into  to a file \n\n" +
                "5. Enter 5 to Delete product from the file history \n\n" +
                "6. Enter 6 to view the Shopping Cart\n\n" +
                "7. Enter 7 to exit \n");
    }

    // Scanner for user input
    Scanner in = new Scanner(System.in);

    /**
     * Add products to the system.
     */
    public void addProductsToSystem() {
        int option;
        System.out.println();
        System.out.println("The Max Products can be added is only 50\n");
        if (productList.size() <= MaxProducts) {
            int availableSpace = MaxProducts - productList.size();
            System.out.println(availableSpace + " Slots available");
            System.out.println();
            try {
                System.out.print("Enter 1 to add Electronic\nEnter 2 to add a clothing Product : ");
                option = in.nextInt();
                if (option == 1) {
                    System.out.println();
                    System.out.print("Enter ProductId : ");
                    String electronicId = in.next();
                    String electronicid = electronicId.toUpperCase();
                    System.out.print("Enter Product Name : ");
                    String electronicName = in.next();
                    System.out.print("Enter no of items available : ");
                    int noOfItem = in.nextInt();
                    System.out.print("Enter Price : ");
                    double price = in.nextDouble();
                    System.out.print("Enter brand name : ");
                    String brand = in.next();
                    System.out.print("Enter Warranty period : ");
                    int warranty = in.nextInt();

                    Electronics electro = new Electronics(electronicid, electronicName, noOfItem, price, brand,
                            warranty);
                    electronicList.add(electro);
                    productList.add(electro);

                    oos = new ObjectOutputStream(new FileOutputStream(fileE));
                    oos.writeObject(electronicList);
                    oos.close();
                    System.out.println("Saved changes to the file ");

                } else if (option == 2) {
                    System.out.println();
                    System.out.print("Enter ProductId : ");
                    String clothingId = in.next();
                    String clothingid = clothingId.toUpperCase();
                    System.out.print("Enter Product Name : ");
                    String clothingName = in.next();
                    System.out.print("Enter no of items available : ");
                    int noOfItem = in.nextInt();
                    System.out.print("Enter Price : ");
                    double price = in.nextDouble();
                    System.out.print("Enter size (S - Small, M - Medium, L - Large) : ");
                    String size = in.next();
                    System.out.print("Enter color  : ");
                    String color = in.next();

                    Clothing clothing = new Clothing(clothingid, clothingName, noOfItem, price, size, color);
                    clothingList.add(clothing);
                    productList.add(clothing);

                    oos = new ObjectOutputStream(new FileOutputStream(fileC));
                    oos.writeObject(clothingList);
                    oos.close();
                    System.out.println("Saved changes to the file ");

                } else {
                    System.out.println("Invalid input");
                    addProductsToSystem();
                }

            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Max Products reached");
        }

        System.out.print("\nDo you want to keep on adding\nIf yes type 'Y'\nEnter any key for main menu: \n");
        String cont = in.next();
        String upperCont = cont.toUpperCase();
        if (upperCont.equals("Y")) {
            addProductsToSystem();
        } else {
            System.out.println("\nRedirected to main menu\n");
        }
    }

    /**
     * Delete a product from the current system.
     */
    public void deleteProductFromCurrentSystem() {
        System.out.println("Go to option 5 If you have entered the product before\n");
        System.out.println("Enter Product ID : ");
        String productID = in.next();
        String productid = productID.toUpperCase();
        for (Product product : productList) {
            if (product.getProductId().equals(productid)) {
                productList.remove(product);
                System.out.println("Product " + productid + " removed ");
                break;
            } else {
                System.out.println("Product doesn't exist\nTry Option 5");
                break;
            }
        }
    }

    /**
     * Print the list of products.
     */
    public void printListOfProducts() {
        productList.sort(Comparator.comparing(Product::getProductId));
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%15s %20s %20s %10s %15s", "Product Id", "Product Name", "No of Available", "Price",
                "Category");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Product product : productList) {
            if (product instanceof Electronics) {
                System.out.format("%15s %20s %12s %18s %15s", product.getProductId(), product.getProductName(),
                        product.getNoOfAvailableItems(), product.getPrice(), "Electronics");
                System.out.println();
            } else if (product instanceof Clothing) {
                System.out.format("%15s %20s %12s %18s %15s", product.getProductId(), product.getProductName(),
                        product.getNoOfAvailableItems(), product.getPrice(), "Clothing");
                System.out.println();
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    /**
     * Save products to a file.
     *
     * @throws IOException If an IO exception occurs.
     */
    public void saveProductsToFile() throws IOException {
        oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(productList);
        oos.close();
        System.out.println("Saved changes to the file ");
    }
}
