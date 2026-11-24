import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GestorSQLite implements GestorArchivos {

    Connection conexion;

    GestorSQLite(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Cliente> leerClientes() {
        return List.of();
    }

    @Override
    public void guardarClientes(Cliente cliente) {

    }

    @Override
    public List<Pago> leerPagos() {
        return List.of();
    }

    @Override
    public void guardarPagos(Pago pago) {

    }

    @Override
    public void crearArchYDir() throws IOException {

    }

    @Override
    public Path getClientes() {
        return null;
    }

    @Override
    public Path getPagos() {
        return null;
    }
}
