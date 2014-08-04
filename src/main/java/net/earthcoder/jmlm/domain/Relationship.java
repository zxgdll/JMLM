package net.earthcoder.jmlm.domain;

public final class Relationship {

    private BinaryNode binaryNode;
    private String flag;
    private Boolean flashed;

    public Relationship(BinaryNode binaryNode, String flag) {
        this.binaryNode = binaryNode;
        this.setFlag(flag);
        this.setFlashed(false);
    }

    @Override
    public String toString() {
       return binaryNode.toString();
    }

    @Override
    public int hashCode() {
        return binaryNode.hashCode();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (null == otherObject) {
            return false;
        }
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }
        Relationship otherRelationship = (Relationship) otherObject;
        return this.binaryNode.equals(otherRelationship.binaryNode);
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean getFlashed() {
        return flashed;
    }

    public void setFlashed(Boolean flashed) {
        this.flashed = flashed;
    }

    public BinaryNode getBinaryNode() {
        return binaryNode;
    }
}
