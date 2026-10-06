import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MigraCSVToJson {

    private int clientesMigrados = 0;
    private int pagosMigrados = 0;

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
    }

    public void hayArchivos(boolean pregunta, Menu menu) {
        if (pregunta) {
            transmutacionDeArchivos();
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
            gArchivosJSON.guardarClientes(c);
        }
    }

    private void migrarPagos() {
        List<Pago> pagos;
        pagos = gArchivosCSV.leerPagos();

        for (Pago p : pagos) {
            gArchivosJSON.guardarPagos(p);
        }
    }
}
