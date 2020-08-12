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
public class InhousePartTest {
    
    public InhousePartTest() {
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
     * Test of getMachineID method, of class InhousePart.
     */
    @Test
    public void testGetMachineID() {
        System.out.println("getMachineID");
        InhousePart instance = new InhousePart();
        int expResult = 0;
        int result = instance.getMachineID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMachineID method, of class InhousePart.
     */
    @Test
    public void testSetMachineID() {
        System.out.println("setMachineID");
        int machineID = 1;
        InhousePart instance = new InhousePart();
        instance.setMachineID(machineID);
        
        assertEquals(machineID, instance.getMachineID());
    }
    
}
