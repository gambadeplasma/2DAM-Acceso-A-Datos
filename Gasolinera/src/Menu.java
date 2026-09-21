import java.util.Date;
import java.util.Scanner;

public class Menu {

    final private GestorCliente gCliente;
    final private GestorPago gPagos;
    final private GestorTexto gTexto;

    public Menu() {
        this.gCliente = new GestorCliente();
        this.gPagos = new GestorPago();
        this.gTexto = new GestorTexto();
    }

    public void displayMenu() {

        Scanner sc = new Scanner(System.in);
        int op;
        boolean salir = false;

        do {

            System.out.println("Por favor, introduzca el número de la acción que desee realizar: ");
            System.out.println("1. Dar de alta un cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar un cliente");
            System.out.println("4. Procesar un pago de repostaje");
            System.out.println("5. Consultar pagos");
            System.out.println("0. Salir");

            System.out.print("Opción:");
            op = gTexto.inOpcion();

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

                default: System.out.println("Opción inválida, por favor introduzca una acción válida."); ;

            }

        } while(!salir);
    }

    public void crearCliente () {

        System.out.println("Introduzca el nombre: ");
        String nombre = gTexto.inTexto();
        System.out.println("Introduzca el teléfono: ");
        String telefono = gTexto.inTexto();
        System.out.println("Introduca la matrícula: ");
        String matricula = gTexto.inMatricula();

        gCliente.crearCliente(nombre, telefono, matricula);
    }

    public void listarClientes() {
        gCliente.listarClientes();
    }

    public void buscarCliente() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduca un nombre, telefono, matrícula o ID para buscar: ");
        String in = gTexto.inTexto();

        gCliente.buscarCliente(in);
    }

    public void crearPago() {

        if (!gCliente.existenClientes()) {

            System.out.println("No es posible añadir un pago: no existen clientes registrados.");

        } else {

            System.out.println("Introduzca el ID del cliente: ");
            int IDcliente = gTexto.inOpcion();

            if (gCliente.existeCliente(Integer.toString(IDcliente))) {

                Date fecha = gTexto.inFecha();

                System.out.println("Introduzca el importe: ");
                double importe = gTexto.inDecimal();

                System.out.println("Introduzca los litros: ");
                double litros = gTexto.inDecimal();

                Combustible combustible = gTexto.inCombustible();

                gPagos.crearPago(IDcliente, fecha, importe, litros, combustible);

            } else {

                System.out.println("No se ha podido encontrar a ningún cliente con el identificador " + IDcliente);
            }
        }
    }

    public void listarPagos() {

        System.out.println("Listado de pagos: ");
        System.out.print("\n");

        gPagos.listarPagos();
    }
}
