package techlab.proyectoJava;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CrudProduct {
    private final ArrayList<Product> productos;

    Scanner sc = new Scanner(System.in);

    public CrudProduct(ArrayList<Product> productos) {
        this.productos = productos;
    }

    public void listProducts() {
        System.out.print("""
                    ============================
                    === Listado de Productos ===
                    ============================
                    
                    """);
        if (!productos.isEmpty()) {
            for (Product producto : productos) {
                producto.info();
            }
        } else {
            System.out.println("No hay productos");
        }
    }

    public void addProduct() {
        System.out.print("""
                    ========================
                    === Agregar producto ===
                    ========================
                    
                    """);
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese el precio: $ ");
        double precio = AuxFunctions.leerDecimal(sc,sc.nextLine());
        System.out.print("Ingrese el stock: ");
        int stock = AuxFunctions.leerEntero(sc,sc.nextLine());
        Product newProduct = new Product(nombre, stock, precio);
        productos.add(newProduct);
        System.out.print("""
                Producto cargado exitosamente!
                
                """);
    }

    public void updateProduct() {
        System.out.print("""
                    ===========================
                    === Actualizar producto ===
                    ===========================
                   
                    """);
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = AuxFunctions.leerEntero(sc, sc.nextLine());
        Product prod = searchById(id);
        if ( prod != null) {
            System.out.printf("Producto ID: %d %n", prod.getId());
            System.out.printf("Nombre: %s. %n Ingrese un nuevo nombre o presione Enter para conservarlo:%n", prod.getName());
            String newName = sc.nextLine().trim();
            if (!newName.isEmpty()) {
                prod.setName(newName);
                System.out.println("Nombre actualizado con éxito.");
            }
            System.out.printf("Precio actual: %.2f %n Ingrese un nuevo precio o presione Enter para conservarlo:%n", prod.getPrice());
            String newPrice = sc.nextLine().trim();

            if (!newPrice.isEmpty()) {
                double precioActualizado = AuxFunctions.leerDecimal(sc, newPrice);
                prod.setPrice(precioActualizado);
                System.out.println("Precio actualizado con éxito.");
            } else {
                System.out.println("El precio no fue cambiado.");
            }

            System.out.printf("Stock actual: %d %n Ingrese nuevo stock o presione Enter para conservarlo:%n", prod.getStock());
            String newStock = sc.nextLine().trim();

            if (!newStock.isEmpty()) {
                int stockActualizado = AuxFunctions.leerEntero(sc, newStock);
                prod.setStock(stockActualizado);
                System.out.println("Stock actualizado con éxito.");
            } else {
                System.out.println("El stock no fue cambiado.");
            }
        } else {
            System.out.print("""
            Producto No Encontrado
            
            """);
        }
    }

    public Product searchById(int id) {
        for (Product producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public void searchByName() {
        System.out.print("""
                    =======================
                    === Buscar producto ===
                    =======================
                    
                    """);
        System.out.print("Ingrese el nombre del producto: ");
        String name = sc.nextLine();
        boolean found = false;

        for (Product producto : productos) {
            if (Objects.equals(producto.getName(), name)) {
                producto.info();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Producto no encontrado.");
        }
    }

    public void deleteProduct() {
        System.out.print("""
                    =========================
                    === Eliminar producto ===
                    =========================
                    
                    """);
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = AuxFunctions.leerEntero(sc, sc.nextLine());
        Product prod = searchById(id);
        if (prod != null) { // Si el producto existe
            System.out.printf("""
                            ¿Está seguro que quiere eliminar este producto? %s
                            Presione [ENTER] para confirmar o cualquier otra tecla para cancelar.
                            """, prod.getName());

            String confirmacion = sc.nextLine().trim();

            if (confirmacion.isEmpty()) {
                productos.remove(prod);
                System.out.println("Producto eliminado con éxito.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("Error al eliminar producto!");
        }
    }

    public void agregarProductosDeEjemplo() {
        Product producto1 = new Product("Café Cappuccino", 100, 1200);
        Product producto2 = new Product("Café Latte", 120, 1500);
        Product producto3 = new Product("Mocha Frappuccino", 200, 1800);
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
    }

    public Product search(String name) {
        for (Product producto : productos) {
            if (producto.getName().toLowerCase().contains(name.toLowerCase())) {
                return producto; // Retorna el producto si hay coincidencia
            }
        }
        System.out.println("No se encontró ningún producto con el nombre especificado.");
        return null;
    }

}
