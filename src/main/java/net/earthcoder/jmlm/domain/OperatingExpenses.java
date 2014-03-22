package net.earthcoder.jmlm.domain;

import java.util.Date;

public final class OperatingExpenses<E extends Human> extends AbstractFee<E> {
    
    private static final int DEFAULT_FEE = 220;
    private static final int DAILY_CAP = 2000;

    public OperatingExpenses() {
        super(DailyBonusCapStrategy.getInstance(OperatingExpenses.DAILY_CAP));
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
