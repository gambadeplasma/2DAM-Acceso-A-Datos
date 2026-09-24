import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorPago {

    private List<Pago> listaPagos;
    private GestorCliente gCliente;
    private GestorArchivoCSV gArchivo;

    public GestorPago(GestorCliente gCliente, GestorArchivoCSV gArchivo) {
        this.listaPagos = new ArrayList<Pago>();
        this.gCliente = gCliente;
        this.gArchivo = gArchivo;
    }

    public void leerPagos() {
        this.listaPagos = gArchivo.leerPagos();
    }

    public void crearPago(int IDcliente, Date fecha, double importe, double litros, Combustible combustible) {

        listaPagos.add(new Pago(listaPagos.size() + 1, IDcliente, fecha, importe, litros, combustible));

    }

    public void listarPagos() {

        if (listaPagos.isEmpty()) {

            System.out.println("No existen pagos registrados.");

        } else {

            listaPagos.sort(new ComparadorIDPagos());

            for(Pago p : listaPagos) {
                System.out.println(p);
            }
        }
    }

    public boolean existenPagos() {
        return !listaPagos.isEmpty();
    }
}
