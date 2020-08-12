/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Model;

import org.hamcrest.core.Is;
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
public class PartTest {
    
    public PartTest() {
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
     * Test of getPartID method, of class Part.
     */
    @Test
    public void testGetPartID() {
        System.out.println("getPartID");
        Part instance = new PartImpl();
        int expResult = 0;
        int result = instance.getPartID();
        assertTrue(result > expResult);
    }

    /**
     * Test of setPartID method, of class Part.
     */
    @Test
    public void testSetPartID() {
        System.out.println("setPartID");
        int partID = 0;
        Part instance = new PartImpl();
        instance.setPartID(partID);
        assertEquals(partID, instance.getPartID());
    }

    /**
     * Test of getName method, of class Part.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Part instance = new PartImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Part.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Part instance = new PartImpl();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getPrice method, of class Part.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Part instance = new PartImpl();
        double expResult = 0.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class Part.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 0.0;
        Part instance = new PartImpl();
        instance.setPrice(price);
        assertEquals(price, instance.getPrice(), 0.0001);
    }

    /**
     * Test of getInStock method, of class Part.
     */
    @Test
    public void testGetInStock() {
        System.out.println("getInStock");
        Part instance = new PartImpl();
        int expResult = 0;
        int result = instance.getInStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInStock method, of class Part.
     */
    @Test
    public void testSetInStock() {
        System.out.println("setInStock");
        int inStock = 0;
        Part instance = new PartImpl();
        instance.setInStock(inStock);
        assertEquals(inStock, instance.getInStock());
    }

    /**
     * Test of getMin method, of class Part.
     */
    @Test
    public void testGetMin() {
        System.out.println("getMin");
        Part instance = new PartImpl();
        int expResult = 0;
        int result = instance.getMin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMin method, of class Part.
     */
    @Test
    public void testSetMin() {
        System.out.println("setMin");
        int min = 0;
        Part instance = new PartImpl();
        instance.setMin(min);
        assertEquals(min, instance.getMin());
    }

    /**
     * Test of getMax method, of class Part.
     */
    @Test
    public void testGetMax() {
        System.out.println("getMax");
        Part instance = new PartImpl();
        int expResult = 0;
        int result = instance.getMax();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMax method, of class Part.
     */
    @Test
    public void testSetMax() {
        System.out.println("setMax");
        int max = 0;
        Part instance = new PartImpl();
        instance.setMax(max);
        assertEquals(max, instance.getMax());
    }

    public class PartImpl extends Part {
    }
    
}
