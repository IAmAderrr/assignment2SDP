package app;
import abstractfactory.WarehouseFactory;
import families.ColdStorageWarehouseFactory;
import families.EcommerceWarehouseFactory;
import families.HeavyCargoWarehouseFactory;

public final class FactorySelector {
    private FactorySelector() {}
    public static WarehouseFactory from(String raw) {
        if (raw == null || raw.isBlank()) throw new IllegalArgumentException("Family is required");
        return switch (raw.trim().toLowerCase()) {
            case "ecommerce", "ecom" -> new EcommerceWarehouseFactory();
            case "cold", "coldstorage" -> new ColdStorageWarehouseFactory();
            case "heavy", "heavycargo" -> new HeavyCargoWarehouseFactory();
            default -> throw new IllegalArgumentException("Unknown family: " + raw);
        };
    }
}
