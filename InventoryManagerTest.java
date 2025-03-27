import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class InventoryManagerTest {
    private InventoryManager inventory;

    @BeforeEach
    void setUp() {
        inventory = new InventoryManager();
    }

    @Test
    void testAddAndSearchProducts() {
        Map<String, Integer> sizes = new HashMap<>();
        sizes.put("M", 5);
        sizes.put("L", 3);
        
        Product product = new Product("123", "Camisa", "Camisa de algodón", sizes);
        inventory.skuTree.insert(product.getSku(), product);
        inventory.nameTree.insert(product.getName(), product);
        
        assertEquals(product, inventory.searchBySku("123"));
        assertEquals(product, inventory.searchByName("Camisa"));
        assertNull(inventory.searchBySku("999"));
    }
}
