package techlab.proyectoJava;

import java.util.ArrayList;
import java.util.Scanner;

public class Orden {
    private static int NEXT_ID = 0;
    private final int id;
    private final ArrayList<Pedido> pedidos;
    private ArrayList<ArrayList<Pedido>> ordenesRealizadas;

    Scanner sc = new Scanner(System.in);

    public Orden() {
        this.pedidos = new ArrayList<>();
        this.ordenesRealizadas = new ArrayList<>();
        this.id = NEXT_ID++;
    }
    public ArrayList<Pedido> createOrden(ArrayList<Product> productos) {
        System.out.print("""
                ====================
                === Crear Pedido ===
                ====================
                
                """);
        CrudProduct crudProduct = new CrudProduct(productos);
        boolean continuar = true;
        while (continuar) {
            Product foundProd = null;
            System.out.print("Ingrese el ID del producto al pedido: ");
            int idProd = AuxFunctions.leerEntero(sc, sc.nextLine());

            // Buscar producto por ID
            while (foundProd == null) {
                foundProd = crudProduct.searchById(idProd);
                if (foundProd == null) {
                    System.out.print("ID no encontrado. Ingréselo nuevamente: ");
                    idProd = AuxFunctions.leerEntero(sc, sc.nextLine());
                }
            }
            if( foundProd.getStock() > 0) {
                System.out.print("Ingrese la cantidad: ");
                int cant = AuxFunctions.leerEntero(sc, sc.nextLine());
                boolean errorNum = true;

                while (errorNum) {
                    try {
                        if (AuxFunctions.numIntNegative(cant)) {
                            System.out.print("Ingrese una cantidad mayor a cero: ");
                            cant = AuxFunctions.leerEntero(sc, sc.nextLine());
                            continue;
                        }

                        if (foundProd.getStock() < cant) {
                            throw new AuxFunctions.StockInsuficienteException(
                                    String.format("Stock insuficiente para el producto. Stock Disponible: %d",
                                            foundProd.getStock())
                            );
                        }

                        errorNum = false;


                    } catch (AuxFunctions.StockInsuficienteException e) {
                        // Manejar el caso de stock insuficiente
                        System.out.println(e.getMessage());
                        System.out.print("Ingrese una cantidad menor: ");
                        cant = AuxFunctions.leerEntero(sc, sc.nextLine());
                    }

                }

                Pedido nuevoPedido = new Pedido(foundProd, cant);
                pedidos.add(nuevoPedido);
                System.out.println("Pedido agregado exitosamente!");
                for (Product p : productos) {
                    if (p == foundProd) {
                        p.setStock(p.getStock() - cant);
                    }
                }
            } else {
                System.out.print("""
                       Este producto no se encuentra en stock
                       
                       """);
            }
            System.out.print("¿Desea agregar otro producto? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();
            continuar = respuesta.equals("s");
        }

        return pedidos;
    }

    public void verOrden(ArrayList<Pedido> nuevaOrden) {
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
        ordenesRealizadas = ordenes;
        if (!ordenesRealizadas.isEmpty()) {
            int count = 1;

           for ( ArrayList<Pedido> orden : ordenesRealizadas) {
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



}