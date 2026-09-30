import abstractfactory.WarehouseFactory;
import app.*;
import factorymethod.*;
import families.ColdStorageWarehouseFactory;
import families.EcommerceWarehouseFactory;
import families.HeavyCargoWarehouseFactory;
import families.PharmaWarehouseFactory;
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
    @Test void pharmaFactoryCreatesCorrectProducts() {
        WarehouseSystem s = new WarehouseSystem(new PharmaWarehouseFactory());
        assertEquals("TraceableScanner | SecureLabelPrinter | ValidatedSorter", s.deviceSummary());
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
    @Test void pharmaRuntimeSelection() { assertEquals("Pharma", FactorySelector.from("pharma").familyName()); }
    @Test void receiveAndLabelUsesTwoProducts() {
        ProcessingResult r = new WarehouseSystem(FactorySelector.from("ecommerce")).receiveAndLabel("A7","Astana");
        assertTrue(r.scanMessage().contains("A7")); assertTrue(r.labelMessage().contains("Astana"));
    }
    @Test void sortForDispatchUsesSorterBehavior() {
        assertTrue(new WarehouseSystem(FactorySelector.from("heavy")).sortForDispatch("H9","Lane-2").contains("reinforced"));
    }
    @Test void endToEndUsesAllThreeProducts() {
        DispatchResult r = new WarehouseSystem(FactorySelector.from("pharma")).processEndToEnd("RX1","Clinic","Lane-4");
        assertTrue(r.scanMessage().contains("traceability"));
        assertTrue(r.labelMessage().contains("tamper-evident"));
        assertTrue(r.routeMessage().contains("validated"));
    }
    @Test void standardFactoryMethodQuote() {
        PackagingQuote q = new StandardPackagingCreator().prepare(10);
        assertEquals("Standard", q.plan()); assertEquals(5.75, q.totalCost(), 0.001);
    }
    @Test void fragileFactoryMethodQuote() {
        PackagingQuote q = new FragilePackagingCreator().prepare(10);
        assertEquals("Fragile", q.plan()); assertEquals(11.75, q.totalCost(), 0.001);
    }
    @Test void expressFactoryMethodQuote() {
        PackagingQuote q = new ExpressPackagingCreator().prepare(10);
        assertEquals("Express", q.plan()); assertEquals(8.55, q.totalCost(), 0.001);
    }
    @Test void negativeUnknownFamily() { assertThrows(IllegalArgumentException.class, () -> FactorySelector.from("space")); }
    @Test void negativeBlankParcelId() { assertThrows(IllegalArgumentException.class, () -> new WarehouseSystem(FactorySelector.from("cold")).receiveAndLabel(" ","Astana")); }
    @Test void negativePackagingWeight() { assertThrows(IllegalArgumentException.class, () -> new ExpressPackagingCreator().prepare(0)); }
    @Test void clientWorksThroughFactoryAbstraction() {
        WarehouseFactory abstraction = FactorySelector.from("pharma");
        WarehouseSystem client = new WarehouseSystem(abstraction);
        assertEquals("Pharma", client.family());
    }
}
