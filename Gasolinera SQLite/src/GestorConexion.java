import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorConexion {

    private Path archivo;
    private String url;

    public GestorConexion(Path archivo) {
        this.archivo = archivo;
        this.url = "jdbc:sqlite:" + archivo.toAbsolutePath();
    }

    public boolean verificarConexionSQLite() {

        if (!Files.isRegularFile(archivo)) {
            System.out.println("No existe el archivo " + archivo.toAbsolutePath());
            return false;
        }

        try (Connection conexion = DriverManager.getConnection(url)) {

            try (Statement config = conexion.createStatement()) {
                config.execute("PRAGMA foreign_keys = ON");
            }

            System.out.println("Conexión SQLite abierta: " + archivo.toAbsolutePath());
            System.out.println("Conexión válida: " + conexion.isValid(2));

            return true;

        } catch (SQLException e) {
            System.out.println("No se puede conectar al archivo SQLite " + e.getMessage());
            return false;
        }
    }

    public Connection conectarSQLite() throws SQLException {
        return DriverManager.getConnection(url);

    }
}
