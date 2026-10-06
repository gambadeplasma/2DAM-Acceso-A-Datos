import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        MigraCSVToJson migracion = new MigraCSVToJson();

        migracion.hayArchivos(preguntaMigracion(), menu);
    }

    private static boolean preguntaMigracion() {
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.println("¿Existen archivos para migrar? (Y/n): ");
        str = sc.nextLine();

        while(!str.equals("Y") && !str.equals("y") && !str.equals("N") && !str.equals("n")) {
            System.out.println("Opción incorrecta. Por favor introduzca \"Y\" para sí o \"n\" para no: ");
            str = sc.nextLine();
        }

        return str.equals("Y") || str.equals("y");
    }
}