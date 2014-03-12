package net.earthcoder.ganoderma;

import java.util.Date;

public class BillItem<E> {

    private Date date;
    private long fee;
    private E people;
    
    public BillItem(Date date, long fee, E people) {
        this.date = date;
        this.fee = fee;
        this.people = people;
    }
    
    public E getPeople() {
        return people;
    }
    
    public Date getDate() {
        return date;
    }

    public long getFee() {
        return fee;
    }
}
