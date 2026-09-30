# Assignment 2 Report

## 1. Problem and design goal
The application models warehouse automation. A working installation requires three coordinated product types: scanner, label printer, and sorter. Ecommerce, cold-storage, heavy-cargo, and pharmaceutical warehouses require different implementations, so direct construction would tightly couple business logic to concrete classes.

## 2. Part A: problems without factories
The first Git commit contains `LegacyDemo`. It demonstrates direct creation logic. The main design problems are: concrete-class dependency; growing conditional logic; creation logic that must be edited for each new family; and no structural guarantee that products remain from one compatible family.

## 3. Factory Method
`PackagingCreator` defines the factory method `createPackaging()`. Three subclasses choose three `PackagingPlan` implementations. The creator's `prepare()` method contains business logic: input validation, product creation, material cost calculation, labor cost addition, rounding, and quote creation. Therefore the pattern is not a static factory or a wrapper around `new`.

## 4. Abstract Factory
`WarehouseFactory` declares creation methods for `Scanner`, `LabelPrinter`, and `Sorter`. Each concrete factory produces a complete compatible family. The original three families are Ecommerce, ColdStorage, and HeavyCargo. Pharma is the extension family.

## 5. Compatibility
Family consistency is guaranteed by the `WarehouseSystem` constructor. The client passes exactly one `WarehouseFactory`; the system immediately creates the scanner, printer, and sorter from that same factory. Individual products cannot be injected through the public API. This makes accidental cross-family assembly unavailable through normal client usage.

## 6. Runtime selection
`FactorySelector` maps an external string to a factory. `Main` gets that value from the first command-line argument or `WAREHOUSE_FAMILY`. After construction, all warehouse business logic depends only on interfaces.

## 7. Business scenarios
Three operations demonstrate collaboration: receiving/scanning and label printing, dispatch sorting, and a complete end-to-end parcel workflow using all three products.

## 8. Adding the fourth family
The Pharma family adds four new files (three concrete products and one concrete Abstract Factory). Existing `WarehouseSystem` business logic requires no modification. Only the runtime selector receives one extra registration case, which is the minimal integration point.

## 9. Testing
The project contains 20 automated JUnit 5 tests. They test all original families, correct products, compatibility, runtime selection, business behavior, negative cases, Factory Method behavior, the fourth family, and use through abstractions.

## 10. Git development history
The local repository contains meaningful development commits showing the evolution from the no-factory implementation through abstractions, patterns, runtime selection, tests, extension family, and documentation.
