import java.util.Comparator;

public class ComparadorIDCliente implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {

        int result = c1.compareTo(c2);

        if (result == 0) {
            result = Integer.compare(c1.getID(), c2.getID());
        }

        return result;
    }
}
