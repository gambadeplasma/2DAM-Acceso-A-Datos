import java.util.Locale;
import java.util.Scanner;

public class Menu {

    public void displayMenu() {

        Scanner sc = new Scanner(System.in);
        int op;
        boolean salir = false;

        do {
            System.out.println("Por favor, introduzca el número de la acción que desee realizar: ");
            System.out.println("1. Dar de alta un cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar cliente");
            System.out.println("4. Procesar un pago de repostaje");
            System.out.println("5. Consultar pagos");
            System.out.println("0. Salir");

            System.out.print("Opción:");
            op = sc.nextInt();

            switch(op) {
                case 1: {crearCliente();
                    salir = true;};
                case 2: {listarClientes();
                    salir = true;};
                case 3: {buscarCliente();
                    salir = true;};
                case 4: {crearPago();
                    salir = true;};
                case 5: {listarPagos();
                    salir = true;};
                case 0: salir = true;
                default:
                    System.out.println("Opción inválida, por favor introduzca una acción válida."); ;
            }
        } while(!salir);
    }

    public void crearCliente () {

    }

    public void listarClientes() {

    }

    public void buscarCliente() {

    }

    public void crearPago() {

    }

    public void listarPagos() {

    }
}
