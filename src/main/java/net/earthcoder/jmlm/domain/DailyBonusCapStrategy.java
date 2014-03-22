package net.earthcoder.jmlm.domain;

import java.util.Date;

/**
 * Created by Wei on 2014/3/22.
 */
public final class DailyBonusCapStrategy extends CapStrategy {

    private static CapStrategy instance;
    private long dailyCap;

    public static CapStrategy getInstance(long dailyCap) {
        if (null == instance) {
            instance = new DailyBonusCapStrategy(dailyCap);
        }
        return instance;
    }

    private DailyBonusCapStrategy(long dailyCap) {
        this.dailyCap = dailyCap;
    }

    @Override
    public void getFee(DailyBook dailyBook, Date date, Human people, long fee) {
        long dailySum = dailyBook.getDailySum();
        if (dailySum > dailyCap) {
            try {
                throw new OverDailyCapException();
            } catch (OverDailyCapException e) {
                e.printStackTrace();
            }
        } else if (dailySum == dailyCap) {
            return;
        } else {
            dailyBook.addFee(date, people, dailySum + fee > dailyCap ? dailyCap - dailySum : fee);
        }
    }
}
