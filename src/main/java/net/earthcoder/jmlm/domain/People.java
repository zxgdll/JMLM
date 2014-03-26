package net.earthcoder.jmlm.domain;

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

    @Override
    public Integer nodeID() {
        return null;
    }

    @Override
    public Integer referNodeID() {
        return null;
    }

    @Override
    public Integer ownerUserID() {
        return null;
    }

    @Override
    public Integer loadNodeID() {
        return null;
    }
}
