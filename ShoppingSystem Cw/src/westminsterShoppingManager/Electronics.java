package westminsterShoppingManager;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Electronics extends Product implements Serializable{

    private String brand;
    private int warrantyPeriod;

    public Electronics(String productId, String productName, int noOfAvailableItems, double price, String brand, int warrantyPeriod) {
        super(productId, productName, noOfAvailableItems, price);
        this.setBrand(brand);
        this.setWarrantyPeriod(warrantyPeriod);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }


}
