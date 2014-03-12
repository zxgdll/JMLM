package net.earthcoder.ganoderma.fee;

import java.util.*;

import net.earthcoder.ganoderma.man.Human;



public interface Fee<E extends Human> {

    long sum();
    void add(Date date, E people);
    void add(Date date, E people, long fee);
    long defaultValue();
}
