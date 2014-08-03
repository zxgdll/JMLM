package net.earthcoder.jmlm.domain;

import java.util.Date;

public final class BillItem {

    private Date date;
    private long fee;
    private Human people;
    
    public BillItem(Date date, long fee, Human people) {
        this.date = date;
        this.fee = fee;
        this.people = people;
    }

    @Override
    public String toString() {
        return "[" + date + ", " + fee + ", " + people + "]";
    }
    
    public Human getPeople() {
        return people;
    }
    
    public Date getDate() {
        return date;
    }

    public long getFee() {
        return fee;
    }
}
