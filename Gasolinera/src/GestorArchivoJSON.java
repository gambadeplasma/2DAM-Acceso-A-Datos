import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.StandardOpenOption.APPEND;

public class GestorArchivoJSON implements GestorArchivos {

    final private Path directorio = Path.of("datos");
    final private Path jsonClientes = directorio.resolve("clientes.json");
    final private Path jsonPagos = directorio.resolve("pagos.json");

    public GestorArchivoJSON() throws IOException{
        crearArchYDir();
    }

    @Override
    public void crearArchYDir() throws IOException {

        Files.createDirectories(directorio);

        if (Files.notExists(jsonClientes)) {
            Files.createFile(jsonClientes);
        }

        if (Files.notExists(jsonPagos)) {
            Files.createFile(jsonPagos);
        }
    }

    @Override
    public List<Cliente> leerClientes() {

        List<Cliente> leidos = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(jsonClientes, StandardCharsets.UTF_8)) {

            String linea;
            while ((linea = lector.readLine()) != null) {

                String[] datosCliente = linea.split(",");

                Cliente cliente = new Cliente(Integer.parseInt(datosCliente[0]), datosCliente[1], datosCliente[2],datosCliente[3].replace(";", ""));
                leidos.add(cliente);
            }

        } catch (IOException e) {
            System.out.println("No se ha podido acceder a los clientes.");
        }

        return leidos;
    }

    @Override
    public void guardarClientes(Cliente cliente) {

        String linea = "{\"id\": " + cliente.getID() + ",\"nombre\": \"" +
                cliente.getNombre() + "\",\"telefono\": \"" +
                cliente.getTlf() + "\",\"matricula\": \"" +
                cliente.getMatricula() + "\"}";

        try {

            if (!Files.readString(jsonClientes).isEmpty()) {
                linea = "," + System.lineSeparator() + linea;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        try {

            Files.writeString(jsonClientes, linea, APPEND);

        } catch (IOException e) {

            System.out.println("No se ha podido guardar el cliente.");
        }
    }

    @Override
    public List<Pago> leerPagos() {

        List<Pago> leidos = new ArrayList<>();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader lector = Files.newBufferedReader(jsonPagos, StandardCharsets.UTF_8)) {

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
            System.out.println("No se ha podido acceder a los pagos.");
        }

        return leidos;
    }

    @Override
    public void guardarPagos(Pago pago) {

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

        String linea = "{\"id\": " + pago.getID() + ",\"clienteId\": " +
                pago.getIDcliente() + ",\"fecha\": \"" +
                formateador.format(pago.getFecha()) + "\",\"importe\": " +
                pago.getImporte() + ",\"litros\": " +
                pago.getLitros() + ",\"combustible\": \"" +
                pago.getCombustible() + "\"}";

        try {

            if (!Files.readString(jsonPagos).isEmpty()) {
                linea = "," + System.lineSeparator() + linea;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        try {

            Files.writeString(jsonPagos, linea, APPEND);

        } catch (IOException e) {

            System.out.println("No se ha podido guardar el pago.");
        }
    }
}