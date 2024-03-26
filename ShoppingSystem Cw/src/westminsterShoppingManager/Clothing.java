package westminsterShoppingManager;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Clothing extends Product implements Serializable{


    private String size;
    private String color;


    public Clothing(String productId, String productName, int noOfAvailableItems, double price, String size, String color) {
        super(productId, productName, noOfAvailableItems, price);
        this.setSize(size);
        this.setColor(color);
    }


    public String getSize() {
        return size;
    }


    public void setSize(String size) {
        this.size = size;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }


}
