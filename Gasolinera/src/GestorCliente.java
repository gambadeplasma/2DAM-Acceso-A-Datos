import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GestorCliente {

    private List<Cliente> listaClientes;
    private GestorArchivoCSV gArchivo;

    public GestorCliente(GestorArchivoCSV gArchivo) {
        this.listaClientes = new ArrayList<Cliente>();
        this.gArchivo = gArchivo;
    }

    public void leerClientes() {
        this.listaClientes = gArchivo.leerClientes();
    }

    public void crearCliente(String nombre, String telefono, String matricula) {

        if(existeCliente(matricula)) {
            System.out.println("Esta matrícula ya está registrada.");
        } else {
            Cliente c = new Cliente(listaClientes.size() + 1, nombre, telefono, matricula);
            listaClientes.add(c);
            gArchivo.guardarClientes(c);
        }
    }

    public boolean existeCliente (String matricula) {

        for(Cliente c : listaClientes) {

            if (c.getMatricula().equals(matricula)) {
                return true;
            }
        }

        return false;
    }

    public boolean existeCliente (int id) {

        for(Cliente c : listaClientes) {

            if (c.getID() == id) {
                return true;
            }
        }

        return false;
    }

    public void listarClientes() {

        if (listaClientes.isEmpty()) {

            System.out.println("No existen clientes registrados.");

        } else {

            listaClientes.sort(new ComparadorIDCliente());

            for(Cliente c : listaClientes) {
                System.out.println(c);
            }
        }
    }

    public void buscarCliente(String in) {

        List<Cliente> busqueda = new ArrayList<>();

        in = in.toLowerCase();

        for (Cliente c : listaClientes) {

            if (c.getMatricula().toLowerCase().contains(in) || c.getNombre().toLowerCase().contains(in) || c.getTlf().contains(in) || c.getID() == Integer.parseInt(in)) {
                busqueda.add(c);
            }
        }

        if (busqueda.isEmpty()) {

            System.out.println("No se han encontrado clientes con esos datos.");

        } else {

            for (Cliente c : busqueda) {
                String str = c.toString();
                System.out.print(str + "\n");
            }
        }
    }

    public boolean existenClientes() {
        return !listaClientes.isEmpty();
    }
}
