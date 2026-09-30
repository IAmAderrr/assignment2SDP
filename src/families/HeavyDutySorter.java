package families;
import abstractfactory.Sorter;
public final class HeavyDutySorter implements Sorter {
 public String model(){ return "HeavyDutySorter"; }
 public String route(String id,String lane){ return "HEAVY routed " + id + " to reinforced " + lane; }
}
