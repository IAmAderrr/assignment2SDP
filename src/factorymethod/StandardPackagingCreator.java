package factorymethod;
public final class StandardPackagingCreator extends PackagingCreator {
    protected PackagingPlan createPackaging() { return new StandardPackaging(); }
}
