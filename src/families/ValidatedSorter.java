package families;
import abstractfactory.Sorter;
public final class ValidatedSorter implements Sorter {
 public String model(){ return "ValidatedSorter"; }
 public String route(String id,String lane){ return "PHARMA routed " + id + " to validated " + lane; }
}
