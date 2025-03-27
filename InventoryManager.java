import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona el inventario de productos de ropa deportiva.
 * Permite cargar productos desde un archivo CSV, buscarlos por SKU o nombre,
 * y listarlos en orden.
 */
public class InventoryManager {
    /**
     * Árbol binario de búsqueda que almacena los productos ordenados por SKU.
     */
    public BinarySearchTree<String, Product> skuTree;

    /**
     * Árbol binario de búsqueda que almacena los productos ordenados por nombre.
     */
    public BinarySearchTree<String, Product> nameTree;

    /**
     * Constructor que inicializa los árboles binarios de búsqueda.
     */
    public InventoryManager() {
        skuTree = new BinarySearchTree<>();
        nameTree = new BinarySearchTree<>();
    }

    /**
     * Carga los productos desde un archivo CSV y los inserta en los árboles de búsqueda.
     * 
     * @param filename Nombre del archivo CSV a cargar.
     */
    public void loadFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Ignorar la primera línea de encabezado
            br.readLine();
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                
                // Parsear las tallas
                Map<String, Integer> sizes = parseSizes(data[3]);
                
                Product product = new Product(data[0], data[1], data[2], sizes);
                
                // Insertar en ambos árboles
                skuTree.insert(product.getSku(), product);
                nameTree.insert(product.getName(), product);
            }
            System.out.println("Archivo cargado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    /**
     * Convierte una cadena de tallas en un mapa de tallas y cantidades.
     * 
     * @param sizesStr Cadena de tallas en formato "S:10|M:15|L:8".
     * @return Mapa con tallas como claves y cantidades como valores.
     */
    private Map<String, Integer> parseSizes(String sizesStr) {
        Map<String, Integer> sizes = new HashMap<>();
        String[] sizeEntries = sizesStr.split("\\|");
        
        for (String entry : sizeEntries) {
            String[] parts = entry.split(":");
            sizes.put(parts[0], Integer.parseInt(parts[1]));
        }
        
        return sizes;
    }

    /**
     * Busca un producto por su SKU.
     * 
     * @param sku SKU del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    public Product searchBySku(String sku) {
        return skuTree.search(sku);
    }

    /**
     * Busca un producto por su nombre.
     * 
     * @param name Nombre del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    public Product searchByName(String name) {
        return nameTree.search(name);
    }

    /**
     * Muestra los productos en orden ascendente por SKU.
     */
    public void listProductsBySku() {
        System.out.println("Productos ordenados por SKU:");
        skuTree.inOrderTraversal();
    }

    /**
     * Muestra los productos en orden ascendente por nombre.
     */
    public void listProductsByName() {
        System.out.println("Productos ordenados por Nombre:");
        nameTree.inOrderTraversal();
    }
}
