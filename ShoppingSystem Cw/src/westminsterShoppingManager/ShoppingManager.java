package westminsterShoppingManager;

import java.io.IOException;

public interface ShoppingManager {

    void addProductsToSystem();

    void deleteProductFromCurrentSystem();

    void printListOfProducts();

    void saveProductsToFile() throws IOException;

}
