package techlab.proyectoJava;

import java.util.ArrayList;
import java.util.Scanner;

public class Orden {
    private static int NEXT_ID = 0;
    private final int id;
    private final ArrayList<Pedido> pedidos;

    Scanner sc = new Scanner(System.in);

    public Orden() {
        this.pedidos = new ArrayList<>();
        this.id = NEXT_ID++;
    }

    public void listPedidos(ArrayList<Pedido> nuevaOrden) {
        if (!nuevaOrden.isEmpty()) {
            double total = 0.0;
            System.out.printf("""
                    ======================
                    ===   Orden # %d   ===
                    ======================
                    
                    """, this.id);
            for (Pedido pedido : nuevaOrden) {
                pedido.info();
                total += pedido.getMontoTotal();
            }
            System.out.printf("""
                    ======================
                      TOTAL : $ %.2f
                    ======================
                    
                    """, total);
        } else {
            System.out.println("No hay pedidos");
        }
    }


    public void listOrdenes(ArrayList<ArrayList<Pedido>> ordenes) {
       if (!ordenes.isEmpty()) {
           int count = 1;

           for (ArrayList<Pedido> orden : ordenes) {
               double total = 0.0;
               System.out.printf("""
                    ======================
                    ===   Orden #%d   ===
                    ======================
                    
                    """, count++);
               for (Pedido pedido : orden) {
                   pedido.info();
                   total += pedido.getMontoTotal();
               }
               System.out.printf("""
                    ======================
                      TOTAL : $ %.2f
                    ======================
                    
                    """, total);
           }
       } else {
           System.out.println("No hay órdenes registradas.");
       }
   }

    public ArrayList<Pedido> createPedidos(ArrayList<Product> productos) {
        boolean continuar = true;

        System.out.print("""
                ====================
                === Crear Pedido ===
                ====================
                
                """);

        while (continuar) {
            Product foundProd = null;
            System.out.print("Ingrese el ID del producto al pedido: ");
            int idProd = AuxFunctions.leerEntero(sc, sc.nextLine());

            // Buscar producto por ID
            while (foundProd == null) {
                for (Product producto : productos) {
                    if (producto.getId() == idProd) {
                        foundProd = producto;
                        break;
                    }
                }
                if (foundProd == null) {
                    System.out.print("ID no encontrado. Ingréselo nuevamente: ");
                    idProd = AuxFunctions.leerEntero(sc, sc.nextLine());
                }
            }

            System.out.print("Ingrese la cantidad: ");
            int cant = AuxFunctions.leerEntero(sc, sc.nextLine());

            Pedido nuevoPedido = new Pedido(foundProd, cant);
            pedidos.add(nuevoPedido);
            System.out.println(" Pedido agregado exitosamente!");

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();
            continuar = respuesta.equals("s");
        }

        return pedidos;
    }

}