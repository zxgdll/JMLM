package net.earthcoder.jmlm.domain;

import java.util.Date;

public class BillItem {

    private Date date;
    private long fee;
    private Human people;
    
    public BillItem(Date date, long fee, Human people) {
        this.date = date;
        this.fee = fee;
        this.people = people;
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