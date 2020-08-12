/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Daniel
 */
public class Inventory {
    private ObservableList<Product> allProducts;
    private ObservableList<Part> allParts;
    
    public Inventory()
    {
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }
    
    public ObservableList<Part> getParts()
    {
        return allParts;
    }

    public ObservableList<Product> getProducts()
    {
        return allProducts;
    }
    
    public void addProduct(Product product) {
        allProducts.add(product);
    }
    
    public boolean removeProduct(int id) {
        for(int x = 0; x < allProducts.size(); x++)
        {
            if(allProducts.get(x).getProductID() == id)
            {
                allProducts.remove(x);
                return true;
            }
        }
        return false;
    }
    
    public Product lookupProduct(int id) {
        for(int x = 0; x < allProducts.size(); x++)
        {
            if(allProducts.get(x).getProductID() == id)
                return allProducts.get(x);
        }
        return null;
    }
    
    public void updateProduct(int id) {
        /* What am I supposed to do here, with no information to update? */
    }
    
    public void updateProduct(int id, Product newProduct) {
        for(int x = 0; x < allProducts.size(); x++)
        {
            if(allProducts.get(x).getProductID() == id)
                allProducts.set(x, newProduct);
        }
    }
    
    public void addPart(Part part) {
        allParts.add(part);
    }
    
    public boolean deletePart(int id) {
        for(int x = 0; x < allParts.size(); x++)
        {
            if(allParts.get(x).getPartID() == id)
            {
                // Lookup part in products, make sure it's not assigned
                for(int y = 0; y < allProducts.size(); y++)
                {
                    if(allProducts.get(y).lookupAssociatedPart(id) != null)
                    {
                        Alert error = new Alert(Alert.AlertType.ERROR, "Cannot delete a part associated with a product.", ButtonType.OK);
                        error.showAndWait();
                        return false;
                    }
                }
                allParts.remove(x);
                return true;
            }
        }
        return false;
    }
    
    public Part lookupPart(int id) {
        for(int x = 0; x < allParts.size(); x++)
        {
            if(allParts.get(x).getPartID() == id)
                return allParts.get(x);
        }
        return null;
    }
    
    public void updatePart(int id) {
        /* What am I supposed to do here, with no information to update? */
    }
    
    public void updatePart(int id, Part newPart) {
        for(int x = 0; x < allParts.size(); x++)
        {
            if(allParts.get(x).getPartID() == id)
                allParts.set(x, newPart);
        }
    }
}
