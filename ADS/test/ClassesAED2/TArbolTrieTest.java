package ClassesAED2;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TArbolTrieTest {
    
    public TArbolTrieTest() {
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

    @Test
    public void testInsertar() {
        System.out.println("insertar");
        String palabra = "";
        TArbolTrie instance = new TArbolTrie();
        instance.insertar(palabra);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testImprimir() {
        System.out.println("imprimir");
        TArbolTrie instance = new TArbolTrie();
        instance.imprimir();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscar() {
        System.out.println("buscar");
        String palabra = "";
        TArbolTrie instance = new TArbolTrie();
        int expResult = 0;
        int result = instance.buscar(palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPredecir() {
        System.out.println("predecir");
        String prefijo = "";
        TArbolTrie instance = new TArbolTrie();
        LinkedList<String> expResult = null;
        LinkedList<String> result = instance.predecir(prefijo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
