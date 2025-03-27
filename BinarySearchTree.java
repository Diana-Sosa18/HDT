/**
 * Implementación de un árbol binario de búsqueda genérico.
 * 
 * @param <K> Tipo de clave, debe ser comparable.
 * @param <V> Tipo de valor asociado a la clave.
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node root;

    /**
     * Clase interna que representa un nodo del árbol.
     */
    private class Node {
        K key;
        V value;
        Node left, right;

        /**
         * Constructor del nodo.
         * 
         * @param key   Clave del nodo.
         * @param value Valor asociado a la clave.
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    /**
     * Inserta un nuevo nodo en el árbol.
     * Si la clave ya existe, no se inserta un nuevo nodo.
     * 
     * @param key   Clave del nuevo nodo.
     * @param value Valor asociado a la clave.
     */
    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }

    /**
     * Método auxiliar recursivo para insertar un nodo.
     * 
     * @param root  Raíz del árbol o subárbol.
     * @param key   Clave a insertar.
     * @param value Valor asociado a la clave.
     * @return Nodo actualizado después de la inserción.
     */
    private Node insertRec(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }

        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key, value);
        }

        return root;
    }

    /**
     * Busca un nodo en el árbol por su clave.
     * 
     * @param key Clave a buscar.
     * @return Valor asociado a la clave, o null si la clave no existe.
     */
    public V search(K key) {
        return searchRec(root, key);
    }

    /**
     * Método auxiliar recursivo para buscar un nodo.
     * 
     * @param root Raíz del árbol o subárbol.
     * @param key  Clave a buscar.
     * @return Valor asociado a la clave, o null si no se encuentra.
     */
    private V searchRec(Node root, K key) {
        if (root == null || root.key.equals(key)) {
            return root != null ? root.value : null;
        }

        if (key.compareTo(root.key) < 0) {
            return searchRec(root.left, key);
        }

        return searchRec(root.right, key);
    }

    /**
     * Realiza un recorrido in-order del árbol e imprime los valores.
     */
    public void inOrderTraversal() {
        inOrderRec(root);
    }

    /**
     * Método auxiliar recursivo para el recorrido in-order.
     * 
     * @param root Raíz del árbol o subárbol.
     */
    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.value);
            inOrderRec(root.right);
        }
    }
}
