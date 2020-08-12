/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Daniel
 */
public class Product {
    private static int _nextProductID = 1;
    private ObservableList<Part> associatedParts;
    private int productID;
    private String name = "";
    private double price;
    private int inStock;
    private int min;
    private int max;

    public Product() {
        associatedParts = FXCollections.observableArrayList();
        this.productID = _nextProductID++;
    }
    
    public Product(String name, double price, int inStock, int min, int max, ObservableList<Part> parts) {
        this();
        
        this.name = name;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        this.price = price;
        associatedParts = parts;
    }

    public Product(Product product) {
        this.productID = product.getProductID();
        this.name = product.getName();
        this.inStock = product.getInStock();
        this.min = product.getMin();
        this.max = product.getMax();;
        this.price = product.getPrice();;
        associatedParts = product.getAssociatedParts();
    }
    
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the inStock
     */
    public int getInStock() {
        return inStock;
    }

    /**
     * @param inStock the inStock to set
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    public boolean removeAssociatedPart(int id) {
        for(int x = 0; x < associatedParts.size(); x++){
            if(associatedParts.get(x).getPartID() == id)
            {
                associatedParts.remove(x);
                return true;
            }
        }
        return false;
    }
    
    public Part lookupAssociatedPart(int id) {
        for(int x = 0; x < associatedParts.size(); x++){
            Part part = associatedParts.get(x);
            if(part.getPartID() == id)
            {
                return part;
            }
        }
        return null;
    }
}
