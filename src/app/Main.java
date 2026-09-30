package app;
import abstractfactory.WarehouseFactory;
import factorymethod.*;

public final class Main {
    public static void main(String[] args) {
        String selectedFamily = args.length > 0 ? args[0] : System.getenv().getOrDefault("WAREHOUSE_FAMILY", "ecommerce");
        WarehouseFactory factory = FactorySelector.from(selectedFamily);
        WarehouseSystem system = new WarehouseSystem(factory);

        System.out.println("Selected family: " + system.family());
        System.out.println("Devices: " + system.deviceSummary());
        System.out.println(system.processEndToEnd("PKG-2048", "Astana", "Lane-7"));

        PackagingCreator creator = new FragilePackagingCreator();
        System.out.println("Factory Method quote: " + creator.prepare(8.5));
    }
}
