public class Cliente implements Comparable<Cliente>{
    private int ID;
    private String nombre;
    private String tlf;
    private String matricula;

    public Cliente(int ID, String nombre, String tlf, String matricula) {
        this.ID = ID;
        this.nombre = nombre;
        this.tlf = tlf;
        this.matricula = matricula;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", tlf='" + tlf + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }

    @Override
    public int compareTo(Cliente c) {
        return nombre.compareTo(c.getNombre());
    }
}
