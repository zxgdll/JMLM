package net.earthcoder.jmlm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class InitialFee<E extends Human> extends AbstractFee<E> {

    private static final int DEFAULT_FEE = 2200;

    public InitialFee() {
        super(NoCapStrategy.getInstance());
    }
    
    @Override
    public long defaultValue() {
        return DEFAULT_FEE;
    }

    @Override
    public void add(Date date, E people) {
        add(date, people, DEFAULT_FEE);
    }
}
