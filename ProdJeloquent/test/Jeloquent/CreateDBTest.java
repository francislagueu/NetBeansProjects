/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeloquent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edwige
 */
public class CreateDBTest {
    
    public CreateDBTest() {
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
     * Test of getDatabase method, of class CreateDB.
     */
    @Test
    public void testGetDatabase() {
        System.out.println("getDatabase");
        String expResult = "";
        String result = CreateDB.getDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDatabase method, of class CreateDB.
     */
    @Test
    public void testSetDatabase() {
        System.out.println("setDatabase");
        String database = "";
        CreateDB.setDatabase(database);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class CreateDB.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String db = "";
        CreateDB instance = null;
        instance.create(db);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
