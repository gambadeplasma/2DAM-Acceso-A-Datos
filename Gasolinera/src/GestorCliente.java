import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GestorCliente {

    private final List<Cliente> listaClientes;

    public GestorCliente() {
        this.listaClientes = new ArrayList<Cliente>();
    }

    public void crearCliente(String nombre, String telefono, String matricula) {

        if(existeCliente(matricula)) {
            System.out.println("Esta matrícula ya está registrada.");
        } else {
            listaClientes.add(new Cliente(listaClientes.size() + 1, nombre, telefono, matricula));
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

    private boolean existeCliente (int id) {

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
