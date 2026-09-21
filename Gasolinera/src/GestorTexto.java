import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GestorTexto {

    Scanner sc = new Scanner(System.in);

    public GestorTexto() {
        this.sc = sc;
    }

    public String inTexto() {

        while (true) {

            String in = sc.nextLine();

            if (in.isEmpty()) {

                System.out.println("Debe introducir, al menos, un caracter.");
            }

            return stripTexto(in);
        }
    }

    public String inMatricula() {

        String in = sc.nextLine();
        in = in.toUpperCase();

        return stripTexto(in);
    }

    public Date inFecha() {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        while(true) {
            System.out.println("Introduzca una fecha con el formato \"dd/MM/yyyy\": ");
            String in = sc.nextLine();

            if (in.isEmpty()) {
                return new Date();
            }

            try {

                Date fecha = formato.parse(in);

                if(!formato.format(fecha).equals(in)) {
                    System.out.println("Formato incorrecto.");
                }

            } catch (ParseException e) {
                System.out.println("Fecha y/o formato incorrecto/s.");
            }
        }
    }

    public int inOpcion() {

        while (true) {

            try {

                return Integer.parseInt(sc.nextLine());

            } catch(NumberFormatException e) {

                System.out.println("Introduzca un número válido.");
            }
        }
    }

    public double inDecimal() {

        while (true) {

            System.out.println("Introduzca un número mayor que 0 con máximo dos decimales: ");
            String in = sc.nextLine();

            in = stripTexto(in);
            in = in.replace(',', '.');

            if (!in.matches("\\d+(\\.\\d{1,2})?")) {

                System.out.println("Introduzca un número válido con máximo dos decimales.");
                continue;
            }

            double num = Double.parseDouble(in);

            if (num <= 0) {

                System.out.println("El número no puede ser 0 ni negativo.");
                continue;
            }

            return num;
        }
    }

    public Combustible inCombustible() {

        while (true) {

            System.out.println("Introduzca su tipo de combustible [GASOLINA / DIÉSEL]: ");
            String in = sc.nextLine().toUpperCase();

            try {

                return Combustible.valueOf(stripTexto(in));

            } catch (IllegalArgumentException e) {

                System.out.println("El combustible ha de ser GASOLINA o DIÉSEL.");
            }
        }
    }

    private String stripTexto(String in) {

        in = in.strip();
        return in;
    }
}
