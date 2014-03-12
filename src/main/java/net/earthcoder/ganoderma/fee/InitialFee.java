package net.earthcoder.ganoderma.fee;

import java.util.*;

import net.earthcoder.ganoderma.*;
import net.earthcoder.ganoderma.man.*;

public final class InitialFee<E extends Human> extends AbstractFee<E> {
    
    private static final long DEFAULT_FEE = 2200;
    
    private List<BillItem<E>> initialFeeList = new ArrayList<BillItem<E>>();
    
    public long rows() {
        return initialFeeList.size();
    }
    
    @Override
    public long defaultValue() {
        return DEFAULT_FEE;
    }
    
    @Override
    public void add(Date date, E people) {
        add(date, people, DEFAULT_FEE);
    }

    @Override
    public void add(Date date, E people, long fee) {
        BillItem<E> item = new BillItem<E>(date, fee, people);
        initialFeeList.add(item);
        sum += item.getFee();
    }
}
