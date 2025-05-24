package techlab.proyectoJava;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> productos = new ArrayList<>();

        CrudProduct crudProduct = new CrudProduct(productos);
        crudProduct.agregarProductosDeEjemplo();

        Orden orden = new Orden();
        ArrayList<ArrayList<Pedido>> ordenes = new ArrayList<>();
        ArrayList<Pedido> nuevaOrden = new ArrayList<>();

        int optionUser;
        boolean exit = false;

        while (!exit) {
            System.out.print("""
                    ================================
                    === CRUD Productos & Pedidos ===
                    ================================
                    
                    1. Agregar Producto
                    2. Listar Productos
                    3. Actualizar Producto
                    4. Eliminar Producto
                    5. Buscar Producto
                    6. Crear Pedido
                    7. Ver Orden
                    8. Listar Ordenes
                    9. Salir
                    
                    Seleccione una opción:
                    """);

            optionUser = AuxFunctions.leerEntero(sc, sc.nextLine());

            switch (optionUser) {
                case 1 -> crudProduct.addProduct();
                case 2 -> crudProduct.listProducts();
                case 3 -> crudProduct.updateProduct();
                case 4 -> crudProduct.deleteProduct();
                case 5 -> crudProduct.searchByName();
                case 6 -> {
                    nuevaOrden = orden.createPedidos(productos);
                    ordenes.add(nuevaOrden);
                    System.out.println("Orden creada exitosamente.");
                    orden = new Orden();
                }
                case 7 -> orden.listPedidos(nuevaOrden);
                case 8 -> orden.listOrdenes(ordenes);
                case 9-> {
                    System.out.println("Gracias por usar la app!");
                    exit = true;
                }
                default -> System.out.println("Opción no válida.");
            }
        }

    }
}
