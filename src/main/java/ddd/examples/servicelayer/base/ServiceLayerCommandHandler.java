package ddd.examples.servicelayer.base;

public abstract class ServiceLayerCommandHandler<T extends ServiceLayerCommand,R extends ServiceLayerCommandResponse> {
    public abstract R execute(T command);
}
