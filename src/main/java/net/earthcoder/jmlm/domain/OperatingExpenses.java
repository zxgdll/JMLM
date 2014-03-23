package net.earthcoder.jmlm.domain;

import java.util.Date;

public final class OperatingExpenses extends AbstractFee {
    
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
    public void add(Date date, Human people) {
        add(date, people, DEFAULT_FEE);
    }
}
