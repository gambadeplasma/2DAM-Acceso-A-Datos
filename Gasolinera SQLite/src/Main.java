import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Path archivo = args.length == 0 ? Path.of("gasolinera.sqlite") : Path.of(args[0]);

        GestorConexion gConexion = new GestorConexion(archivo);

        if (gConexion.verificarConexionSQLite()) {
            Menu menu = new Menu();
            menu.displayMenu();
        }
    }
}