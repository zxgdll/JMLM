package net.earthcoder.ganoderma.man;

public final class People extends AbstractHuman {
    
    private String name;

    public People(Integer serialNumber) {
        super(serialNumber);
    }
    
    public People(Integer serialNumber, String name) {
        this(serialNumber);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
