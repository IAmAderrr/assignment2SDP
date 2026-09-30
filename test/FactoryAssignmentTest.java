import abstractfactory.WarehouseFactory;
import app.*;
import factorymethod.*;
import families.ColdStorageWarehouseFactory;
import families.EcommerceWarehouseFactory;
import families.HeavyCargoWarehouseFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryAssignmentTest {
    @Test void ecommerceFactoryCreatesCorrectProducts() {
        WarehouseSystem s = new WarehouseSystem(new EcommerceWarehouseFactory());
        assertEquals("FastBarcodeScanner | ThermalLabelPrinter | HighSpeedSorter", s.deviceSummary());
    }
    @Test void coldFactoryCreatesCorrectProducts() {
        WarehouseSystem s = new WarehouseSystem(new ColdStorageWarehouseFactory());
        assertEquals("ColdResistantScanner | FreezerLabelPrinter | ColdStorageSorter", s.deviceSummary());
    }
    @Test void heavyFactoryCreatesCorrectProducts() {
        WarehouseSystem s = new WarehouseSystem(new HeavyCargoWarehouseFactory());
        assertEquals("RuggedScanner | IndustrialLabelPrinter | HeavyDutySorter", s.deviceSummary());
    }
    @Test void eachOriginalFamilyHasThreeDevices() {
        for (WarehouseFactory f : new WarehouseFactory[]{new EcommerceWarehouseFactory(), new ColdStorageWarehouseFactory(), new HeavyCargoWarehouseFactory()}) {
            assertNotNull(f.createScanner()); assertNotNull(f.createLabelPrinter()); assertNotNull(f.createSorter());
        }
    }
    @Test void productsFromSingleFactoryStayFamilyCompatible() {
        DispatchResult r = new WarehouseSystem(new ColdStorageWarehouseFactory()).processEndToEnd("P1","Almaty","L1");
        assertTrue(r.scanMessage().startsWith("COLD"));
        assertTrue(r.labelMessage().startsWith("COLD"));
        assertTrue(r.routeMessage().startsWith("COLD"));
    }
    @Test void ecommerceRuntimeSelection() { assertEquals("Ecommerce", FactorySelector.from("ecom").familyName()); }
    @Test void coldRuntimeSelection() { assertEquals("ColdStorage", FactorySelector.from("cold").familyName()); }
    @Test void heavyRuntimeSelection() { assertEquals("HeavyCargo", FactorySelector.from("heavy").familyName()); }
    @Test void receiveAndLabelUsesTwoProducts() {
        ProcessingResult r = new WarehouseSystem(FactorySelector.from("ecommerce")).receiveAndLabel("A7","Astana");
        assertTrue(r.scanMessage().contains("A7")); assertTrue(r.labelMessage().contains("Astana"));
    }
    @Test void sortForDispatchUsesSorterBehavior() {
        assertTrue(new WarehouseSystem(FactorySelector.from("heavy")).sortForDispatch("H9","Lane-2").contains("reinforced"));
    }
    @Test void standardFactoryMethodQuote() {
        PackagingQuote q = new StandardPackagingCreator().prepare(10);
        assertEquals("Standard", q.plan()); assertEquals(5.75, q.totalCost(), 0.001);
    }
    @Test void negativeUnknownFamily() { assertThrows(IllegalArgumentException.class, () -> FactorySelector.from("space")); }
}
