package net.earthcoder.jmlm.domain;

import java.util.Date;

public interface Fee {

    long sum();
    void add(Date date, Human people);
    void add(Date date, Human people, long fee);
    long defaultValue();
}
