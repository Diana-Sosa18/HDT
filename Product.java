import java.util.Map;

/**
 * Representa un producto con SKU, nombre, descripción y tallas disponibles.
 */
public class Product {
    private String sku;
    private String name;
    private String description;
    private Map<String, Integer> sizes;

    /**
     * Constructor de la clase Product.
     * 
     * @param sku         Código único del producto.
     * @param name        Nombre del producto.
     * @param description Descripción del producto.
     * @param sizes       Mapa con las tallas disponibles y su cantidad.
     */
    public Product(String sku, String name, String description, Map<String, Integer> sizes) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.sizes = sizes;
    }

    /**
     * Obtiene el SKU del producto.
     * 
     * @return El código único del producto.
     */
    public String getSku() { 
        return sku; 
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return Nombre del producto.
     */
    public String getName() { 
        return name; 
    }

    /**
     * Obtiene la descripción del producto.
     * 
     * @return Descripción del producto.
     */
    public String getDescription() { 
        return description; 
    }

    /**
     * Obtiene las tallas disponibles del producto y sus cantidades.
     * 
     * @return Mapa con tallas y su cantidad disponible.
     */
    public Map<String, Integer> getSizes() { 
        return sizes; 
    }

    /**
     * Modifica la descripción del producto.
     * 
     * @param description Nueva descripción del producto.
     */
    public void setDescription(String description) { 
        this.description = description; 
    }

    /**
     * Modifica las tallas disponibles del producto.
     * 
     * @param sizes Nuevo mapa de tallas con sus cantidades.
     */
    public void setSizes(Map<String, Integer> sizes) { 
        this.sizes = sizes; 
    }

    /**
     * Representación en cadena del producto.
     * 
     * @return Cadena con la información del producto.
     */
    @Override
    public String toString() {
        return "SKU: " + sku + 
               ", Nombre: " + name + 
               ", Descripción: " + description + 
               ", Tallas: " + sizes;
    }
}
