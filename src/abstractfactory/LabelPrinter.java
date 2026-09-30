package abstractfactory;
public interface LabelPrinter {
    String model();
    String printLabel(String parcelId, String destination);
}
