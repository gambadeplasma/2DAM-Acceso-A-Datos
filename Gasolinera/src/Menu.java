import java.util.Locale;
import java.util.Scanner;

public class Menu {

    public void altaCliente () {

        Scanner sc = new Scanner(System.in);
        String nombre, telefono, matricula;

        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();
        } while (nombre.isEmpty());

        do {
            System.out.println("Introduzca el teléfono: ");
            telefono = sc.nextLine();
        } while (telefono.isEmpty());

        do {
            System.out.println("Introduzca la matrícula: ");
            matricula = sc.nextLine();
        } while (matricula.isEmpty());

        //meter para comprobar que la matrícula no esté registrada vale?? venga

        matricula = matricula.toUpperCase();


    }
}
