import java.io.IOException;
import java.nio.file.Files;
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
            System.out.println("Error al crear el gestor de archivos." + e);
        }

        try {
            this.gArchivosCSV = new GestorArchivoCSV();
        } catch (IOException e) {
            System.out.println("Error al crear el gestor de archivos." + e);
        }

        this.clientesMigrados = 0;
        this.pagosMigrados = 0;

        this.gCliente = new GestorCliente(gArchivosJSON);
        this.gPagos = new GestorPago(gCliente, gArchivosJSON);

        gPagos.leerPagos();
        gCliente.leerClientes();
    }

    public void transmutacionDeArchivos() {
        Scanner sc = new Scanner(System.in);

        if (Files.notExists(gArchivosJSON.getClientes()) && Files.notExists(gArchivosJSON.getPagos())) {
            System.out.println("¿Desea migrar sus datos .csv a .json? (Y/n)");
            String in;

            do {
                in = sc.nextLine();
            } while(!in.equalsIgnoreCase("y") && !in.equalsIgnoreCase("n"));

            if (in.equalsIgnoreCase("y")) {
                System.out.println("Introduzca el directorio en el que se encuentran sus .csv: ");

                in = sc.nextLine();
                gArchivosCSV.setDirectorio(Path.of(in));

                if (Files.notExists(gArchivosCSV.getClientes()) || Files.notExists(gArchivosCSV.getPagos())) {
                    System.out.println("No se han podido localizar los archivos para la migración.");
                } else {
                    try {
                        gArchivosJSON.crearArchYDir();
                    } catch (IOException e) {
                        System.out.println("No se han podido crear los archivos a los que migrar.");
                    }

                    migrarClientes();
                    migrarPagos();
                }
            } else {
                try {
                    gArchivosJSON.crearArchYDir();
                } catch (IOException e) {
                    System.out.println("No se han podido crear los archivos .json.");
                }
            }
        }
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
