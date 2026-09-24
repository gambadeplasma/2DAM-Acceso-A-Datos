import java.io.IOException;
import java.util.List;

public interface GestorArchivos {

    List<Cliente> leerClientes();

    void guardarClientes(Cliente cliente);

    List<Pago> leerPagos();

    void guardarPagos(Pago pago);

    void crearArchYDir() throws IOException;
}
