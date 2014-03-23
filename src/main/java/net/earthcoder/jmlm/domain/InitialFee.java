package net.earthcoder.jmlm.domain;

import java.util.Date;

public final class InitialFee extends AbstractFee {

    private static final int DEFAULT_FEE = 2200;

    public InitialFee() {
        super(NoCapStrategy.getInstance());
    }
    
    @Override
    public long defaultValue() {
        return DEFAULT_FEE;
    }

    @Override
    public void add(Date date, Human people) {
        add(date, people, DEFAULT_FEE);
    }
}
