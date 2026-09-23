import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivoCSV implements GestorArchivos {

    final private Path directorio = Path.of("datos");
    final private Path csvClientes = directorio.resolve("clientes.csv");
    final private Path csvPagos = directorio.resolve("pagos.csv");

    @Override
    public void crearArchYDir() throws IOException {

        try {
            Files.createDirectories(directorio);

            if (Files.notExists(csvClientes)) {
                Files.createFile(csvClientes);
            }

            if (Files.notExists(csvPagos)) {
                Files.createFile(csvPagos);
            }

        } catch (IOException e) {
            throw new IOException("No se ha podido crear el archivo / directorio.");
        }
    }

    @Override
    public List<Cliente> leerClientes() throws IOException {

        List<Cliente> leidos = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(csvClientes, StandardCharsets.UTF_8)) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                String[] datosCliente = linea.split(",");

                Cliente cliente = new Cliente(Integer.parseInt(datosCliente[0]), datosCliente[1], datosCliente[2],datosCliente[3].replace(";", ""));
                leidos.add(cliente);
            }

        } catch (IOException e) {
            throw new IOException("No se ha podido leer el contenido del archivo.");
        }

        return leidos;
    }

    @Override
    public boolean guardarClientes() {
        return false;
    }

    @Override
    public List<Pago> leerPagos() throws IOException {

        List<Pago> leidos = new ArrayList<>();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader lector = Files.newBufferedReader(csvPagos, StandardCharsets.UTF_8)) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                String[] datosPago = linea.split(",");

                try {

                    Pago pago = new Pago(
                            Integer.parseInt(datosPago[0]),
                            Integer.parseInt(datosPago[1]),
                            formateador.parse(datosPago[2]),
                            Double.parseDouble(datosPago[3]),
                            Double.parseDouble(datosPago[4]),
                            Combustible.valueOf(datosPago[5].replace(";", "").toUpperCase().trim())
                    );

                    leidos.add(pago);

                } catch (ParseException e) {
                    System.out.println("No se ha podido leer la fecha.");
                }

            }

        } catch (IOException e) {
            throw new IOException("No se ha podido leer el contenido del archivo.");
        }

        return leidos;
    }

    @Override
    public boolean guardarPagos() {
        return false;
    }

    public Path getDirectorio() {
        return directorio;
    }

    public Path getCsvClientes() {
        return csvClientes;
    }

    public Path getCsvPagos() {
        return csvPagos;
    }
}
