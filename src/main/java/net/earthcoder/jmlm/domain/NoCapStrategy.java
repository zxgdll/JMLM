package net.earthcoder.jmlm.domain;

import java.util.Date;

/**
 * Created by Wei on 2014/3/22.
 */
public final class NoCapStrategy extends CapStrategy {

    private static CapStrategy instance;

    public static CapStrategy getInstance() {
        if (null == instance) {
            instance = new NoCapStrategy();
        }
        return instance;
    }

    private NoCapStrategy() {

    }

    @Override
    public void getFee(DailyBook dailyBook, Date date, Human people, long fee) {
        dailyBook.addFee(date, people, fee);
    }
}
