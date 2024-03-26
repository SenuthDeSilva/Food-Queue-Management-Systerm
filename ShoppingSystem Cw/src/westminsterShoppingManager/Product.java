package westminsterShoppingManager;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Product implements Serializable{

    private String productId;
    private String productName;
    private int noOfAvailableItems;
    private double price;


    public Product(String productId, String productName, int noOfAvailableItems, double price) {
        this.setProductId(productId);
        this.setProductName(productName);
        this.setNoOfAvailableItems(noOfAvailableItems);
        this.setPrice(price);

    }

    public Product(String productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }

    public void setNoOfAvailableItems(int noOfAvailableItems) {
        this.noOfAvailableItems = noOfAvailableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }



}
