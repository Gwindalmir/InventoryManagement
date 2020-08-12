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
public class OutsourcedPartTest {
    
    public OutsourcedPartTest() {
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
     * Test of getCompanyName method, of class OutsourcedPart.
     */
    @Test
    public void testGetCompanyName() {
        System.out.println("getCompanyName");
        OutsourcedPart instance = new OutsourcedPart();
        String expResult = "";
        String result = instance.getCompanyName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompanyName method, of class OutsourcedPart.
     */
    @Test
    public void testSetCompanyName() {
        System.out.println("setCompanyName");
        String companyName = "foo bar";
        OutsourcedPart instance = new OutsourcedPart();
        instance.setCompanyName(companyName);
        
        assertEquals(companyName, instance.getCompanyName());
    }
    
}
