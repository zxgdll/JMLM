package net.earthcoder.jmlm.domain;

import java.util.Calendar;
import java.util.Date;

public final class People extends AbstractHuman {

    private Date date;

    public People(Integer serialNumber) {
        super(serialNumber);
        date = Calendar.getInstance().getTime();
    }

    public People(Integer serialNumber, Date date) {
        this(serialNumber);
        this.date = date;
    }

    @Override
    public String name() {
        return getID().toString();
    }

    @Override
    public Integer nodeID() {
        return getID();
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

    @Override
    public Date initDateTime() {
        return date;
    }
}
