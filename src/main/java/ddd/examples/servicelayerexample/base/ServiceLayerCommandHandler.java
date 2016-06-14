package ddd.examples.servicelayerexample.base;

public abstract class ServiceLayerCommandHandler<T extends ServiceLayerCommand,R extends ServiceLayerCommandResponse> {
    public abstract R execute(T command);
}
