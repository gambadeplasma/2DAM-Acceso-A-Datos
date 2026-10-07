import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        MigraCSVToJson migracion = new MigraCSVToJson();

        migracion.transmutacionDeArchivos();
        menu.displayMenu();
    }
}