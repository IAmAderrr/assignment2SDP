package families;
import abstractfactory.Sorter;
public final class HighSpeedSorter implements Sorter {
 public String model(){ return "HighSpeedSorter"; }
 public String route(String id,String lane){ return "ECOM routed " + id + " to " + lane + " at high speed"; }
}
