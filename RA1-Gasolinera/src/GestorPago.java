import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorPago {

    private List<Pago> listaPagos;
    private GestorCliente gCliente;
    private GestorArchivoJSON gArchivo;

    public GestorPago(GestorCliente gCliente, GestorArchivoJSON gArchivo) {
        this.listaPagos = new ArrayList<Pago>();
        this.gCliente = gCliente;
        this.gArchivo = gArchivo;
    }

    public void leerPagos() {
        this.listaPagos = gArchivo.leerPagos();
    }

    public void crearPago(int IDcliente, Date fecha, double importe, double litros, Combustible combustible) {

        Pago p = new Pago(listaPagos.size() + 1, IDcliente, fecha, importe, litros, combustible);
        listaPagos.add(p);
        gArchivo.guardarPagos(p);
    }

    public void listarPagos() {

        if (listaPagos.isEmpty()) {

            System.out.println("No existen pagos registrados.");

        } else {
            //programación funcional; ya no se necesita la clase del comparador IDPagos
            listaPagos.sort(
                    ((p1, p2) -> {
                        int result = p2.compareTo(p1);
                        return p1.getID() - p2.getID();
                        //return result != 0 ? result : Integer.compare(p2.getID(), p1.getID());
                    })
            );

            for(Pago p : listaPagos) {
                System.out.println(p);
            }
        }
    }

    public boolean existenPagos() {
        return !listaPagos.isEmpty();
    }

    public boolean existePago (int ID) {

        for(Pago p : listaPagos) {

            if (p.getID() == ID) {
                return true;
            }
        }

        return false;
    }
}
