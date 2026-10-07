import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MigraCSVToJson {

    private int clientesMigrados;
    private int pagosMigrados;
    private GestorCliente gCliente;
    private GestorPago gPagos;

    private GestorArchivoJSON gArchivosJSON;
    private GestorArchivoCSV gArchivosCSV;

    public MigraCSVToJson() {

        try {
            this.gArchivosJSON = new GestorArchivoJSON();
        } catch (IOException e) {
            System.out.println("ERROR FATALÍSIMO");
        }

        try {
            this.gArchivosCSV = new GestorArchivoCSV();
        } catch (IOException e) {
            System.out.println("ERROR FATALÍSIMO");
        }

        clientesMigrados = 0;
        pagosMigrados = 0;

        this.gCliente = new GestorCliente(gArchivosJSON);
        this.gPagos = new GestorPago(gCliente, gArchivosJSON);

        gPagos.leerPagos();
        gCliente.leerClientes();
    }

    public void hayArchivos(boolean pregunta, Menu menu) {
        if (pregunta) {
            transmutacionDeArchivos();
            System.out.println("Se han migrado " + clientesMigrados + " clientes y " + pagosMigrados + " pagos.");
            menu.displayMenu();
        } else {
            menu.displayMenu();
        }
    }

    public void transmutacionDeArchivos() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca el directorio de sus archivos clientes.json y pagos.json: ");
        Path directorio = Path.of(sc.nextLine());
        gArchivosJSON.setDirectorio(directorio);

        migrarClientes();
        migrarPagos();
    }

    private void migrarClientes() {
        List<Cliente> clientes;
        clientes = gArchivosCSV.leerClientes();

        for (Cliente c : clientes) {

            if (!gCliente.existeCliente(c.getID())) {
                gArchivosJSON.guardarClientes(c);
                clientesMigrados++;
            }
        }
    }

    private void migrarPagos() {
        List<Pago> pagos;
        pagos = gArchivosCSV.leerPagos();

        for (Pago p : pagos) {
            if (!gPagos.existePago(p.getID())) {
                gArchivosJSON.guardarPagos(p);
                pagosMigrados++;
            }
        }
    }
}
