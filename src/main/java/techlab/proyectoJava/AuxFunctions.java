package techlab.proyectoJava;

import java.util.Scanner;

public class AuxFunctions {
    public static int leerEntero(Scanner sc, String entrada) {
        int num = 0;
        boolean valido = false;

        while (!valido) {
            try {
                num = Integer.parseInt(entrada.trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese nuevamente el número: ");
                entrada = sc.nextLine();
            }
        }
        return num;
    }

    public static double leerDecimal(Scanner sc, String entrada) {
        double num = 0.0;
        boolean valido = false;

        while (!valido) {
            if (entrada.matches("-?\\d+(\\.\\d+)?")) {
                num = Double.parseDouble(entrada.trim());
                valido = true;
            } else  {
            System.out.println("Entrada inválida. Por favor, ingrese nuevamente el número: ");
            entrada = sc.nextLine();
            }
        }
        return num;
    }
}

