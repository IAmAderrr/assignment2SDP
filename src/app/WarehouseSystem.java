package app;
import abstractfactory.*;

/**
 * Client accepts only one family factory and constructs the complete compatible set internally.
 * It exposes business operations, not setters for individual devices; therefore callers cannot
 * accidentally inject a scanner from one family and a printer/sorter from another.
 */
public final class WarehouseSystem {
    private final String family;
    private final Scanner scanner;
    private final LabelPrinter printer;
    private final Sorter sorter;

    public WarehouseSystem(WarehouseFactory factory) {
        this.family = factory.familyName();
        this.scanner = factory.createScanner();
        this.printer = factory.createLabelPrinter();
        this.sorter = factory.createSorter();
    }

    public ProcessingResult receiveAndLabel(String parcelId, String destination) {
        requireText(parcelId, "parcelId"); requireText(destination, "destination");
        return new ProcessingResult(scanner.scan(parcelId), printer.printLabel(parcelId, destination));
    }

    public String sortForDispatch(String parcelId, String lane) {
        requireText(parcelId, "parcelId"); requireText(lane, "lane");
        return sorter.route(parcelId, lane);
    }

    public DispatchResult processEndToEnd(String parcelId, String destination, String lane) {
        ProcessingResult first = receiveAndLabel(parcelId, destination);
        String route = sortForDispatch(parcelId, lane);
        return new DispatchResult(family, first.scanMessage(), first.labelMessage(), route);
    }

    public String family(){ return family; }
    public String deviceSummary(){ return scanner.model()+" | "+printer.model()+" | "+sorter.model(); }

    private static void requireText(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " must not be blank");
    }
}
