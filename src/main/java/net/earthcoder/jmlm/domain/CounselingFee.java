package net.earthcoder.jmlm.domain;

import java.util.Date;

public final class CounselingFee<E extends Human> extends AbstractFee<E> {
    
    private static final int DEFAULT_FEE = 110;
    private static final int DAILY_CAP = 2000;

    public CounselingFee() {
        super(DailyBonusCapStrategy.getInstance(CounselingFee.DAILY_CAP));
    }

    @Override
    public void add(Date date, E people) {
        add(date, people, CounselingFee.DEFAULT_FEE);
    }

    @Override
    public long defaultValue() {
        return CounselingFee.DEFAULT_FEE;
    }
}
