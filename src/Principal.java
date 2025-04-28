import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        while (true){
            System.out.println("********************************************************");
            System.out.println("Bienvenido a la plataforma de conversion de divisas");
            System.out.println("********************************************************");

            System.out.println("Ingrese las siglas de las divisas que quiere convertir");
            System.out.println("********************************************************");

            System.out.println("Si no sabes como es la sigla de la divisa que estas buscado escribe -Consulta-");

            System.out.println("Si quieres dejar de recibir conversiones indica -Salir- ");

            try {
                var siglaMoneda =String.valueOf(lectura.nextLine());

                // Si el usuario escribe "Salir", terminamos el programa
                if (siglaMoneda.equalsIgnoreCase("Salir")) {
                    System.out.println("Programa finalizado.!");
                    break; // Salimos del while
                }
                Moneda moneda = consulta.buscarMoneda(siglaMoneda);
                System.out.println(moneda);
                HistorialConversiones generador = new HistorialConversiones();
                generador.guardarConversiones(moneda);
            }catch (NumberFormatException e){
                System.out.println("Moneda no encontrada "+e.getMessage());
            } //catch (IOException e) {
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

