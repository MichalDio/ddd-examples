package ddd.examples.domain.base;

public class AggregateRoot extends Entity {
    private int version;

    public int getVersion() {
        return version;
    }
}
