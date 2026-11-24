import java.util.Comparator;

public class ComparadorIDPagos implements Comparator<Pago> {


    @Override
    public int compare(Pago p1, Pago p2) {

        int result = p2.compareTo(p1);

        if (result == 0) {
            result = Integer.compare(p2.getID(), p1.getID());
        }

        return result;
    }
}
