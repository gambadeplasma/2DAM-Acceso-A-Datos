import java.io.IOException;
import java.util.List;

public interface GestorArchivos {

    List<Cliente> leerClientes() throws IOException;

    boolean guardarClientes();

    List<Pago> leerPagos() throws IOException;

    boolean guardarPagos();

    void crearArchYDir() throws IOException;
}
