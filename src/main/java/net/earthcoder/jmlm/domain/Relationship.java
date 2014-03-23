package net.earthcoder.jmlm.domain;

public final class Relationship {

    private Integer id;
    private String flag;
    private Boolean flashed;

    public Relationship(Integer id, String flag) {
        this.setId(id);
        this.setFlag(flag);
        this.setFlashed(false);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
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
        return this.id.equals(otherRelationship.id);
    }

    protected void setId(Integer id) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }
}
