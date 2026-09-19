import java.time.LocalDate;

public class Pago implements Comparable<Pago>{

    private final int ID;
    private int IDcliente;
    private LocalDate fecha;
    private int importe;
    private int litros;
    private String combustible;

    public Pago(int ID, int IDcliente, LocalDate fecha, int importe, int litros, String combustible) {
        this.ID = ID;
        this.IDcliente = IDcliente;
        this.fecha = fecha;
        this.importe = importe;
        this.litros = litros;
        this.combustible = combustible;
    }

    public int getID() {
        return ID;
    }

    public int getIDcliente() {
        return IDcliente;
    }

    public void setIDcliente(int IDcliente) {
        this.IDcliente = IDcliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "ID=" + ID +
                ", IDcliente=" + IDcliente +
                ", fecha=" + fecha +
                ", importe=" + importe +
                ", litros=" + litros +
                ", combustible='" + combustible + '\'' +
                '}';
    }

    @Override
    public int compareTo(Pago p) {
        return p.getFecha().compareTo(fecha);
    }
}
