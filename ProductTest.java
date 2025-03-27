import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * Pruebas unitarias para la clase Product.
 */
class ProductTest {
    private Product product;
    private Map<String, Integer> sizes;

    /**
     * Configuración inicial antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        sizes = new HashMap<>();
        sizes.put("S", 10);
        sizes.put("M", 15);
        product = new Product("SKU123", "Camiseta", "Camiseta de algodón", sizes);
    }

    /**
     * Prueba de obtención del SKU.
     */
    @Test
    void testGetSku() {
        assertEquals("SKU123", product.getSku());
    }

    /**
     * Prueba de obtención del nombre.
     */
    @Test
    void testGetName() {
        assertEquals("Camiseta", product.getName());
    }

    /**
     * Prueba de obtención de la descripción.
     */
    @Test
    void testGetDescription() {
        assertEquals("Camiseta de algodón", product.getDescription());
    }

    /**
     * Prueba de obtención de las tallas.
     */
    @Test
    void testGetSizes() {
        assertEquals(sizes, product.getSizes());
    }

    /**
     * Prueba de modificación de la descripción.
     */
    @Test
    void testSetDescription() {
        product.setDescription("Nueva descripción");
        assertEquals("Nueva descripción", product.getDescription());
    }

    /**
     * Prueba de modificación de las tallas.
     */
    @Test
    void testSetSizes() {
        Map<String, Integer> newSizes = new HashMap<>();
        newSizes.put("L", 5);
        product.setSizes(newSizes);
        assertEquals(newSizes, product.getSizes());
    }

    /**
     * Prueba del método toString.
     */
    @Test
    void testToString() {
        String expected = "SKU: SKU123, Nombre: Camiseta, Descripción: Camiseta de algodón, Tallas: {S=10, M=15}";
        assertEquals(expected, product.toString());
    }
}

