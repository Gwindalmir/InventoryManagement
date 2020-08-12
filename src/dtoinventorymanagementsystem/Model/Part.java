/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Model;

/**
 *
 * @author Daniel
 */
// Abstract class
abstract public class Part {
    private static int _nextPartID = 1;
    private int partID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    protected Part()
    {
        this.name = "";
        this.partID = _nextPartID++;
    }
    
    protected Part(String name, double price, int inStock, int min, int max) {
        this();
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    
    /*
    * Copy constructor, to preserve partID and not increment
    */
    public Part(Part part)
    {
        this.partID = part.getPartID();
        this.name = part.getName();
        this.price = part.getPrice();
        this.inStock = part.getInStock();
        this.min = part.getMin();
        this.max = part.getMax();
    }
    
    /**
     * @return the partID
     */
    public int getPartID() {
        return partID;
    }

    /**
     * @param partID the partID to set
     */
    public void setPartID(int partID) {
        this.partID = partID;
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
        if(name == null)
            name = "";
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
}
