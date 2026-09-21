import java.util.Date;


public class Pago implements Comparable<Pago>{

    private final int ID;
    private int IDcliente;
    private Date fecha;
    private double importe;
    private double litros;
    private Combustible combustible;

    public Pago(int ID, int IDcliente, Date fecha, double importe, double litros, Combustible combustible) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustible combustible) {
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
