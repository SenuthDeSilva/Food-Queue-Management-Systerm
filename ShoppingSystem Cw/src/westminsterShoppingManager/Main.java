package westminsterShoppingManager;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import westminsterShoppingCart.CartMain;

@SuppressWarnings("unused")
public class Main {

    static CartMain cart = new CartMain();
    static WestminsterShoppingManager manager = new WestminsterShoppingManager();
    static List<Product> loadedList = manager.loadProductsFromFile("productsFile.txt");
    static List<Electronics> loadedList2 = manager.loadElectronicsFromFile("electronicList.txt.txt");
    static List<Clothing> loadedList3 = manager.loadClothingFromFile("Clothing.txt.txt");

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        // Accessing WestminsterShoppingManager

        File file = new File("productsFile.txt");


        System.out.println(" --------------------------------------------------");
        System.out.println("|     Welcome to Westminster Shopping Manager      |");
        System.out.println(" --------------------------------------------------");

        @SuppressWarnings("resource")
        Scanner enter = new Scanner(System.in);
        boolean go = true; // Initialize stop variable

        while (go) {

            // Reading the file to see saved products

            manager.displayMenu();
            System.out.print("Enter the number: ");
            String choice = enter.next();

            switch (choice) {
                case "1":
                    manager.addProductsToSystem();
                    break;
                case "2":
                    manager.deleteProductFromCurrentSystem();
                    break;
                case "3":
                    manager.printListOfProducts();
                    break;
                case "4":
                    manager.saveProductsToFile();
                    break;
                case "5":
                    deleteProductFromFile();
                    break;
                case "6":
                    CartMain.main(args);
                    break;
                case "7":
                    System.out.println();
                    System.out.println("Save the changes you made to a file before proceeding to prevent complications");
                    System.out.println();
                    System.out.print("Do you want to terminate the system? ('Y' for yes, Any key for no) : ");
                    String term = enter.next();
                    String upper = term.toUpperCase();
                    if (upper.equals("Y")) {
                        System.out.println(
                                "---------------------------------------------------------------------------------------");
                        System.out.println(
                                "-----------------------------------System terminated-----------------------------------");
                        System.out.println(
                                "---------------------------------------------------------------------------------------");
                        go = false; // Exit the loop
                    } else if (upper == "N") {
                        go = true;
                    } else {
                        System.out.println();
                        System.out.println("continuing");
                        go = true;
                    }
                    break;
                default:
                    System.out.println("Invalid input try again");
            }
        }
    }
    public static void deleteProductFromFile() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Product ID : ");
        String productID = in.next();
        String productid = productID.toUpperCase();
        try {
            // Read from the file
            BufferedReader reader = new BufferedReader(new FileReader("Productfile.txt"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        }catch (Exception e) {
            System.out.println("invalid input");
        }
        boolean found = false;
        for (Product product : loadedList) {
            if (product.getProductId().equals(productid)) {
                loadedList.remove(product);
                found = true;
                break;
            }else {
                System.out.println("Product not found");
            }
        }
    }
}
