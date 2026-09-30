# Assignment 2 — Factory Method & Abstract Factory

## Domain
**Multi-family warehouse automation system.** A warehouse installation needs a compatible scanner, label printer, and sorter. Different environments need different implementations:

| Family | Scanner | Label Printer | Sorter |
|---|---|---|---|
| Ecommerce | FastBarcodeScanner | ThermalLabelPrinter | HighSpeedSorter |
| ColdStorage | ColdResistantScanner | FreezerLabelPrinter | ColdStorageSorter |
| HeavyCargo | RuggedScanner | IndustrialLabelPrinter | HeavyDutySorter |
| Pharma (added later) | TraceableScanner | SecureLabelPrinter | ValidatedSorter |

This gives **12 concrete Abstract Factory products** (4 families × 3 product types).

## Part A — before factories
`legacy/LegacyDemo.java` intentionally shows the initial approach. It has three clear problems:
1. the client decides concrete classes;
2. family selection grows into `if/else` logic;
3. every new family requires changing creation code and can lead to inconsistent combinations.

The initial version exists in Git history as the first commit.

## Part B — Factory Method
`PackagingCreator` is the Creator. Its subclasses override `createPackaging()` and choose `StandardPackaging`, `FragilePackaging`, or `ExpressPackaging`.

This is not a static factory because creation is deferred to subclasses through an overridable factory method. More importantly, the base Creator contains real business logic in `prepare(weightKg)`: it validates input, obtains the Product, calculates labor + material cost, and returns a quote.

## Part C — Abstract Factory
`WarehouseFactory` creates three related product types:
- `Scanner`
- `LabelPrinter`
- `Sorter`

Concrete factories produce complete families: Ecommerce, ColdStorage, HeavyCargo, and Pharma.

## Part D — Compatibility guarantee
`WarehouseSystem` accepts **one `WarehouseFactory`**, then creates all three devices internally. It does not expose public setters/constructors for passing arbitrary individual products. Therefore a normal client cannot build `Ecommerce Scanner + Cold Printer + Heavy Sorter` through the system API. The architecture preserves family consistency by construction rather than throwing a runtime "wrong family" exception.

## Part E — Runtime selection
`FactorySelector.from(...)` selects a factory from a command-line argument. `Main` also supports environment variable `WAREHOUSE_FAMILY`.

Examples:
```bash
mvn test
mvn -q exec:java -Dexec.mainClass=app.Main -Dexec.args="cold"
```
If the Maven exec plugin is unavailable, run `Main` directly from IntelliJ IDEA.

Accepted values: `ecommerce`, `cold`, `heavy`, `pharma`.

## Part F — Business operations
`WarehouseSystem` includes three realistic operations:
1. `receiveAndLabel()` — scanner + label printer collaborate;
2. `sortForDispatch()` — sorter routes a parcel;
3. `processEndToEnd()` — scanner + printer + sorter form one complete dispatch workflow.

## Part G — fourth family
The fourth family is **Pharma**. Files added:
- `TraceableScanner.java`
- `SecureLabelPrinter.java`
- `ValidatedSorter.java`
- `PharmaWarehouseFactory.java`

Only `FactorySelector.java` needed a small registration change. `WarehouseSystem` business logic did not change.

## Tests
There are **20 JUnit tests**, covering original families, concrete products, compatibility, runtime selection, Factory Method behavior, 3 business behaviors, negative scenarios, fourth family, and abstraction-based client use.

## Run
Requirements: Java 17+, Maven 3.9+.
```bash
mvn clean test
```

## Git history
The repository contains 8+ meaningful commits. Check with:
```bash
git log --oneline --reverse
```
