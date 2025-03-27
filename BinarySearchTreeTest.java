import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {
    private BinarySearchTree<String, Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
    }

    @Test
    void testInsertAndSearch() {
        tree.insert("A", 10);
        tree.insert("B", 20);
        tree.insert("C", 30);
        
        assertEquals(10, tree.search("A"));
        assertEquals(20, tree.search("B"));
        assertEquals(30, tree.search("C"));
        assertNull(tree.search("D"));
    }

    @Test
    void testInOrderTraversal() {
        tree.insert("B", 20);
        tree.insert("A", 10);
        tree.insert("C", 30);
        
        // Capturar la salida estándar
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        tree.inOrderTraversal();
        
        String expectedOutput = "10\n20\n30\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
