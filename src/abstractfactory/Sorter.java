package abstractfactory;
public interface Sorter {
    String model();
    String route(String parcelId, String lane);
}
