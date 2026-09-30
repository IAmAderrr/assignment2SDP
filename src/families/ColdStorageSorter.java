package families;
import abstractfactory.Sorter;
public final class ColdStorageSorter implements Sorter {
 public String model(){ return "ColdStorageSorter"; }
 public String route(String id,String lane){ return "COLD routed " + id + " to insulated " + lane; }
}
