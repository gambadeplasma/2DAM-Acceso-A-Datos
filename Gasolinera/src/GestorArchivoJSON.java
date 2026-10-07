import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class GestorArchivoJSON implements GestorArchivos {

    private Path directorio = Path.of("datos");
    private final Path jsonClientes = directorio.resolve("clientes.json");
    private final Path jsonPagos = directorio.resolve("pagos.json");

    private String openerJSON = "{\n\t[\n";
    private String closerJSON = "\n\t]\n}";

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

                int ID = Integer.parseInt(datosCliente[0].substring(datosCliente[0].indexOf(":") + 1).trim());
                String nombre = datosCliente[1].substring(datosCliente[1].indexOf(":") + 1).trim().replace("\"", "");
                String tlf = datosCliente[2].substring(datosCliente[2].indexOf(":") + 1).trim().replace("\"", "");
                String matricula = datosCliente[3].substring(datosCliente[3].indexOf(":") + 1).trim().replace("\"", "");

                Cliente cliente = new Cliente(ID, nombre, tlf, matricula);
                leidos.add(cliente);
            }

        } catch (IOException e) {
            System.out.println("No se ha podido acceder a los clientes.");
        }

        return leidos;
    }

    @Override
    public void guardarClientes(Cliente cliente) {

        List<Cliente> listaClientes = leerClientes();
        listaClientes.add(cliente);

        //Programación funcional chulísima
        String linea = listaClientes.stream().map(
                this::clienteAStringJSON
        ).reduce(
                (String s1, String s2) -> {return s1 + ",\n" + s2;}
        ).orElse("");

        try {

            if (!Files.readString(jsonClientes).isEmpty()) {
                linea = "," + System.lineSeparator() + linea;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            Files.writeString(jsonClientes, openerJSON, TRUNCATE_EXISTING);
            Files.writeString(jsonClientes, linea, APPEND);
            Files.writeString(jsonClientes, closerJSON, APPEND);

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

                    int ID = Integer.parseInt(datosPago[0].substring(datosPago[0].indexOf(":") + 1).trim());
                    int IDCliente = Integer.parseInt(datosPago[1].substring(datosPago[1].indexOf(":") + 1).trim());;
                    Date fecha = formateador.parse(datosPago[2].substring(datosPago[2].indexOf(":") + 1).replace("\"", "").trim());
                    Double importe = Double.parseDouble(datosPago[3].substring(datosPago[3].indexOf(":") + 1).replace("\"", "").trim());
                    Double litros = Double.parseDouble(datosPago[4].substring(datosPago[4].indexOf(":") + 1).replace("\"", "").trim());
                    Combustible combustible = Combustible.valueOf(datosPago[5].substring(datosPago[5].indexOf(":") + 1).replace("\"", "").replace("}", "").toUpperCase().trim());

                    Pago pago = new Pago(ID, IDCliente, fecha, importe, litros, combustible);

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

    public void setDirectorio(Path directorio) {
        this.directorio = directorio;
    }

    @Override
    public void guardarPagos(Pago pago) {

        List<Pago> listaPagos = leerPagos();
        listaPagos.add(pago);

        String linea = listaPagos.stream().map(
                this::pagoAStringJSON
        ).reduce(
                (String s1, String s2) -> s1 + ",\n" + s2
        ).orElse("");

        try {

            if (!Files.readString(jsonPagos).isEmpty()) {
                linea = "," + System.lineSeparator() + linea;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            Files.writeString(jsonPagos, openerJSON, TRUNCATE_EXISTING);
            Files.writeString(jsonPagos, linea, APPEND);
            Files.writeString(jsonPagos, closerJSON, APPEND);
        } catch (IOException e) {

            System.out.println("No se ha podido guardar el pago.");
        }
    }

    private String clienteAStringJSON(Cliente cliente) {
        return "{\"id\": " + cliente.getID() + ",\"nombre\": \"" +
                cliente.getNombre() + "\",\"telefono\": \"" +
                cliente.getTlf() + "\",\"matricula\": \"" +
                cliente.getMatricula() + "\"}";
    }

    private String pagoAStringJSON(Pago pago) {

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

        return "{\"id\": " + pago.getID() + ",\"clienteId\": " +
                pago.getIDcliente() + ",\"fecha\": \"" +
                formateador.format(pago.getFecha()) + "\",\"importe\": " +
                pago.getImporte() + ",\"litros\": " +
                pago.getLitros() + ",\"combustible\": \"" +
                pago.getCombustible() + "\"}";
    }
}