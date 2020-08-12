/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Model;

import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class InventoryTest {
    
    public InventoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getParts method, of class Inventory.
     */
    @Test
    public void testGetParts() {
        System.out.println("getParts");
        Inventory instance = new Inventory();
        ObservableList<Part> result = instance.getParts();
        assertNotNull(result);
    }

    /**
     * Test of addProduct method, of class Inventory.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        Product product = new Product();
        Inventory instance = new Inventory();
        instance.addProduct(product);
        
        assertEquals(product, instance.lookupProduct(product.getProductID()));
    }

    /**
     * Test of removeProduct method, of class Inventory.
     */
    @Test
    public void testRemoveProduct() {
        System.out.println("removeProduct");
        int index = 0;
        Inventory instance = new Inventory();
        boolean expResult = false;
        boolean result = instance.removeProduct(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateProduct method, of class Inventory.
     */
    @Test
    public void testUpdateProduct() {
        System.out.println("updateProduct");
        int index = 0;
        Inventory instance = new Inventory();
        instance.updateProduct(index);
    }

    /**
     * Test of addPart method, of class Inventory.
     */
    @Test
    public void testAddPart() {
        System.out.println("addPart");
        Part part = new InhousePart();
        Inventory instance = new Inventory();
        instance.addPart(part);
        
        assertSame(part, instance.lookupPart(part.getPartID()));
    }

    /**
     * Test of deletePart method, of class Inventory.
     */
    @Test
    public void testDeletePart() {
        System.out.println("deletePart");
        Part part = new InhousePart();
        Inventory instance = new Inventory();
        instance.addPart(part);
        boolean expResult = true;
        boolean result = instance.deletePart(part.getPartID());
        assertEquals(expResult, result);
    }

    /**
     * Test of lookupPart method, of class Inventory.
     */
    @Test
    public void testLookupPart() {
        System.out.println("lookupPart");
        Inventory instance = new Inventory();
        Part expResult = new InhousePart();
        instance.addPart(expResult);
        Part result = instance.lookupPart(expResult.getPartID());
        assertEquals(expResult, result);
    }

    /**
     * Test of getProducts method, of class Inventory.
     */
    @Test
    public void testGetProducts() {
        System.out.println("getProducts");
        Inventory instance = new Inventory();
        ObservableList<Product> result = instance.getProducts();
        assertNotNull(result);
    }

    /**
     * Test of lookupProduct method, of class Inventory.
     */
    @Test
    public void testLookupProduct() {
        System.out.println("lookupProduct");
        int id = 0;
        Inventory instance = new Inventory();
        Product expResult = null;
        Product result = instance.lookupProduct(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateProduct method, of class Inventory.
     */
    @Test
    public void testUpdateProduct_int() {
        System.out.println("updateProduct");
        int id = 0;
        Inventory instance = new Inventory();
        instance.updateProduct(id);
    }

    /**
     * Test of updateProduct method, of class Inventory.
     */
    @Test
    public void testUpdateProduct_int_Product() {
        System.out.println("updateProduct");
        String expValue = "new part";
        Product newProduct = new Product();
        Inventory instance = new Inventory();
        instance.addProduct(newProduct);
        newProduct.setName(expValue);
        instance.updateProduct(newProduct.getProductID(), newProduct);
        assertEquals(expValue, instance.lookupProduct(newProduct.getProductID()).getName());
    }

    /**
     * Test of updatePart method, of class Inventory.
     */
    @Test
    public void testUpdatePart_int() {
        System.out.println("updatePart");
        int id = 0;
        Inventory instance = new Inventory();
        instance.updatePart(id);
    }

    /**
     * Test of updatePart method, of class Inventory.
     */
    @Test
    public void testUpdatePart_int_Part() {
        System.out.println("updatePart");
        Part newPart = new InhousePart();
        String expValue = "new part";
        Inventory instance = new Inventory();
        instance.addPart(newPart);
        newPart.setName(expValue);
        instance.updatePart(newPart.getPartID(), newPart);
        assertEquals(expValue, instance.lookupPart(newPart.getPartID()).getName());
    }
    
}
