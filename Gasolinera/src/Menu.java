import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    private GestorArchivoCSV gArchivo;
    private GestorCliente gCliente;
    private GestorPago gPagos;
    private GestorTexto gTexto;

    public Menu() {
        this.gArchivo = null;

        try {
            gArchivo = new GestorArchivoCSV();
        } catch (IOException e) {
            System.out.println("ERROR FATAL.");
        }

        this.gTexto = new GestorTexto();
        this.gCliente = new GestorCliente(gArchivo);
        this.gPagos = new GestorPago(gCliente, gArchivo);
    }

    public void leerArchivos() {

        try {
            gArchivo.crearArchYDir();
        } catch (IOException e) {
            System.out.println("No se han podido crear el directorio / archivo.");
        }

        gCliente.leerClientes();
        gPagos.leerPagos();
    }

    public void displayMenu() {

        leerArchivos();
        Scanner sc = new Scanner(System.in);
        int op;
        boolean salir = false;

        do {
            System.out.print("\n");
            System.out.println("Por favor, introduzca el número de la acción que desee realizar: ");
            System.out.println("1. Dar de alta un cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar un cliente");
            System.out.println("4. Procesar un pago de repostaje");
            System.out.println("5. Consultar pagos");
            System.out.println("0. Salir");
            System.out.print("\n");
            System.out.print("Opción: ");
            op = gTexto.inOpcion();

            switch(op) {
                case 1: crearCliente();
                        break;

                case 2: listarClientes();
                        break;

                case 3: buscarCliente();
                        break;

                case 4: crearPago();
                        break;

                case 5: listarPagos();
                        break;

                case 0: salir = true;
                    System.out.println("Saliendo del programa...");
                    break;

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

            if (gCliente.existeCliente(IDcliente)) {

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
