/**
 * Clase principal para la gestión del inventario de ropa deportiva.
 * Permite agregar, buscar, listar y editar productos almacenados en un inventario.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SportClothingInventory {
    private static InventoryManager manager = new InventoryManager();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que ejecuta el programa de inventario.
     * Carga los datos desde un archivo CSV y muestra un menú interactivo.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        manager.loadFromCSV("inventario_ropa_deportiva_30.csv");
        
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            
            switch (opcion) {
                case 1:
                    agregarProductoManual();
                    break;
                case 2:
                    buscarPorSKU();
                    break;
                case 3:
                    buscarPorNombre();
                    break;
                case 4:
                    listarPorSKU();
                    break;
                case 5:
                    listarPorNombre();
                    break;
                case 6:
                    editarProducto();
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
        
        scanner.close();
    }

    /**
     * Muestra el menú de opciones en la consola.
     */
    private static void mostrarMenu() {
        System.out.println("\n--- SISTEMA DE INVENTARIO DE ROPA DEPORTIVA ---");
        System.out.println("1. Agregar nuevo producto");
        System.out.println("2. Buscar producto por SKU");
        System.out.println("3. Buscar producto por nombre");
        System.out.println("4. Listar productos por SKU");
        System.out.println("5. Listar productos por nombre");
        System.out.println("6. Editar producto");
        System.out.println("0. Salir");
        System.out.print("Ingrese su opción: ");
    }

    /**
     * Permite agregar un nuevo producto ingresado manualmente por el usuario.
     */
    private static void agregarProductoManual() {
        System.out.print("Ingrese SKU: ");
        String sku = scanner.nextLine();
        
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese descripción: ");
        String descripcion = scanner.nextLine();
        
        Map<String, Integer> tallas = new HashMap<>();
        while (true) {
            System.out.print("Ingrese talla (o 'fin' para terminar): ");
            String talla = scanner.nextLine();
            
            if (talla.equalsIgnoreCase("fin")) {
                break;
            }
            
            System.out.print("Ingrese cantidad para " + talla + ": ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            
            tallas.put(talla, cantidad);
        }
        
        Product nuevoProducto = new Product(sku, nombre, descripcion, tallas);
        manager.skuTree.insert(sku, nuevoProducto);
        manager.nameTree.insert(nombre, nuevoProducto);
        
        System.out.println("Producto agregado exitosamente.");
    }

    /**
     * Busca un producto en el inventario por su SKU e imprime el resultado.
     */
    private static void buscarPorSKU() {
        System.out.print("Ingrese el SKU a buscar: ");
        String sku = scanner.nextLine();
        
        Product producto = manager.searchBySku(sku);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /**
     * Busca un producto en el inventario por su nombre e imprime el resultado.
     */
    private static void buscarPorNombre() {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = scanner.nextLine();
        
        Product producto = manager.searchByName(nombre);
        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /**
     * Lista todos los productos ordenados por SKU.
     */
    private static void listarPorSKU() {
        System.out.println("Listado de productos por SKU:");
        manager.listProductsBySku();
    }

    /**
     * Lista todos los productos ordenados por nombre.
     */
    private static void listarPorNombre() {
        System.out.println("Listado de productos por nombre:");
        manager.listProductsByName();
    }

    /**
     * Permite editar un producto existente en el inventario.
     */
    private static void editarProducto() {
        System.out.print("Ingrese el SKU del producto a editar: ");
        String sku = scanner.nextLine();
        
        Product producto = manager.searchBySku(sku);
        if (producto != null) {
            System.out.println("Producto actual:");
            System.out.println(producto);
            
            System.out.println("\nOpciones de edición:");
            System.out.println("1. Editar descripción");
            System.out.println("2. Editar tallas y cantidades");
            System.out.print("Elija una opción: ");
            
            int opcionEdicion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcionEdicion) {
                case 1:
                    System.out.print("Ingrese nueva descripción: ");
                    String nuevaDescripcion = scanner.nextLine();
                    producto.setDescription(nuevaDescripcion);
                    System.out.println("Descripción actualizada.");
                    break;
                case 2:
                    Map<String, Integer> nuevasTallas = new HashMap<>(producto.getSizes());
                    while (true) {
                        System.out.print("Ingrese talla a modificar (o 'fin' para terminar): ");
                        String talla = scanner.nextLine();
                        
                        if (talla.equalsIgnoreCase("fin")) {
                            break;
                        }
                        
                        System.out.print("Ingrese nueva cantidad para " + talla + ": ");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine();
                        
                        nuevasTallas.put(talla, cantidad);
                    }
                    
                    producto.setSizes(nuevasTallas);
                    System.out.println("Tallas y cantidades actualizadas.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}
