import java.io.IOException;
import java.util.Scanner;

public class Principal2 {

        public static void main(String[] args) {
            Scanner lectura = new Scanner(System.in);

            while (true) {
                // Pantalla Principal
                mostrarPantallaPrincipal(lectura);
            }
        }

        // Método para mostrar el menú principal
        public static void mostrarPantallaPrincipal(Scanner lectura) {
            while (true) {
                System.out.println("********************************************************");
                System.out.println("Bienvenido a la plataforma de conversión de divisas");
                System.out.println("********************************************************");
                System.out.println("1. Convertir divisas");
                System.out.println("2. Ver historial de conversiones");
                System.out.println("3. Salir");
                System.out.println("********************************************************");
                System.out.print("Elija una opción: ");

                String opcion = lectura.nextLine().trim();

                switch (opcion) {
                    case "1":
                        mostrarPantallaConsultaDivisas(lectura);
                        break;

                    case "2":
                        System.out.println("¡Gracias por usar la plataforma! Saliendo...");
                        return;  // Sale de la aplicación
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            }
        }

        // Método para mostrar la pantalla de consulta de divisas
        public static void mostrarPantallaConsultaDivisas(Scanner lectura) {
            ConsultaMoneda consulta = new ConsultaMoneda();

            while (true) {
                System.out.println("********************************************************");
                System.out.println("Consultas de divisas");
                System.out.println("********************************************************");
                System.out.println("Ingrese la sigla de la moneda base (por ejemplo: USD) o 'volver' para regresar:");
                System.out.print("Moneda base: ");
                String monedaBase = lectura.nextLine().trim();

                if (monedaBase.equalsIgnoreCase("volver")) {
                    return;
                }

                System.out.print("Ingrese la sigla de la moneda destino (por ejemplo: EUR): ");
                String monedaDestino = lectura.nextLine().trim();

                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad;
                try {
                    cantidad = Double.parseDouble(lectura.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad no válida. Intente de nuevo.");
                    continue;
                }

                try {
                    Moneda moneda = consulta.buscarMoneda(monedaBase);
                    Double tasa = moneda.conversion_rates().get(monedaDestino.toUpperCase());

                    if (tasa == null) {
                        System.out.println("No se encontró la moneda destino.");
                        continue;
                    }

                    double resultado = cantidad * tasa;
                    System.out.printf("%.2f %s equivale a %.2f %s%n", cantidad, monedaBase.toUpperCase(), resultado, monedaDestino.toUpperCase());


                    HistorialConversiones generador = new HistorialConversiones();
                    generador.guardarConversiones(moneda);
                } catch (IOException e) {
                    System.out.println("Error al buscar la moneda: " + e.getMessage());
                }
            }
        }
    }


