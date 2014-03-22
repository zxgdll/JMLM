package net.earthcoder.jmlm.domain;

import java.util.Date;


public interface Fee<E extends Human> {

    long sum();
    void add(Date date, E people);
    void add(Date date, E people, long fee);
    long defaultValue();
}
