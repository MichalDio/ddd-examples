package ddd.examples.servicelayerexample.base;

public abstract class ServiceLayerCommandHandlerBase<T extends ServiceLayerCommand,R extends ServiceLayerCommandResponse> {
    public abstract R execute(T command);
}
