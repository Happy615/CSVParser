/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvparser;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author happy
 */
public class CSVLineTest {
    
    public CSVLineTest() {
    }

    /**
     * Test of get and set method, of class CSVLine.
     */
    @Test
    public void testGetAndSet() {
        System.out.println("testing CSVLine.get and CSVLine.set");
        String string = "hello,world,,,,";
        CSVLine csv = new CSVLine(string, ",");
        assertEquals("hello", csv.get(0));
        assertEquals("world", csv.get(1));
        assertEquals("", csv.get(2));
        assertEquals("", csv.get(3));
        assertEquals("", csv.get(10));
        
        String s1 = "first";
        csv.set(1, s1);
        assertEquals("first", csv.get(1));
        
        // ignore set if array out of bound without throwing exception
        String s9 = "ninth";
        csv.set(9, s9);
        assertEquals("", csv.get(9));
        
    }

    /**
     * Test of toString method, of class CSVLine.
     */
    @Test
    public void testToString() {
        System.out.println("testing CSVLine.toString");
        String string = "hello,world,,,,";
        CSVLine csv = new CSVLine(string, ",");
        
        assertEquals(string, csv.toString());
    }
    

    /**
     * Test of size method, of class CSVLine.
     */
    @Test
    public void testSize() {
        System.out.println("testing CSVLine.size");
        String string = "hello,world,,,,";
        CSVLine csv = new CSVLine(string, ",");
    
        assertEquals(6, csv.size());
    }

}
