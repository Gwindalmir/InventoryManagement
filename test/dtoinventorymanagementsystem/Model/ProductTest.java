/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Model;

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
public class ProductTest {
    
    public ProductTest() {
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
     * Test of getProductID method, of class Product.
     */
    @Test
    public void testGetProductID() {
        System.out.println("getProductID");
        Product instance = new Product();
        int expResult = 0;
        int result = instance.getProductID();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setProductID method, of class Product.
     */
    @Test
    public void testSetProductID() {
        System.out.println("setProductID");
        int productID = 0;
        Product instance = new Product();
        instance.setProductID(productID);
        assertEquals(productID, instance.getProductID());
    }

    /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "foo product";
        Product instance = new Product();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Product instance = new Product();
        double expResult = 0.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class Product.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 10.0;
        Product instance = new Product();
        instance.setPrice(price);
        assertEquals(price, instance.getPrice(), 0.001);
    }

    /**
     * Test of getInStock method, of class Product.
     */
    @Test
    public void testGetInStock() {
        System.out.println("getInStock");
        Product instance = new Product();
        int expResult = 0;
        int result = instance.getInStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInStock method, of class Product.
     */
    @Test
    public void testSetInStock() {
        System.out.println("setInStock");
        int inStock = 1;
        Product instance = new Product();
        instance.setInStock(inStock);
        assertEquals(inStock, instance.getInStock());
    }

    /**
     * Test of getMin method, of class Product.
     */
    @Test
    public void testGetMin() {
        System.out.println("getMin");
        Product instance = new Product();
        int expResult = 0;
        int result = instance.getMin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMin method, of class Product.
     */
    @Test
    public void testSetMin() {
        System.out.println("setMin");
        int min = 1;
        Product instance = new Product();
        instance.setMin(min);
        assertEquals(min, instance.getMin());
    }

    /**
     * Test of getMax method, of class Product.
     */
    @Test
    public void testGetMax() {
        System.out.println("getMax");
        Product instance = new Product();
        int expResult = 0;
        int result = instance.getMax();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMax method, of class Product.
     */
    @Test
    public void testSetMax() {
        System.out.println("setMax");
        int max = 1;
        Product instance = new Product();
        instance.setMax(max);
        assertEquals(max, instance.getMax());
    }

    /**
     * Test of addAssociatedPart method, of class Product.
     */
    @Test
    public void testAddAssociatedPart() {
        System.out.println("addAssociatedPart");
        Part part = null;
        Product instance = new Product();
        instance.addAssociatedPart(part);
    }

    /**
     * Test of removeAssociatedPart method, of class Product.
     */
    @Test
    public void testRemoveAssociatedPart() {
        System.out.println("removeAssociatedPart");
        Product instance = new Product();
        boolean expResult = false;
        boolean result = instance.removeAssociatedPart(instance.getProductID());
        assertEquals(expResult, result);
    }

    /**
     * Test of lookupAssociatedPart method, of class Product.
     */
    @Test
    public void testLookupAssociatedPart() {
        System.out.println("lookupAssociatedPart");
        int index = 0;
        Product instance = new Product();
        Part expResult = null;
        Part result = instance.lookupAssociatedPart(index);
        assertEquals(expResult, result);
    }
}
