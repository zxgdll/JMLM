package net.earthcoder.jmlm.domain;

import java.util.Date;

/**
 * Created by Wei on 2014/3/22.
 */
public interface CapStrategy {

    void getFee(DailyBook dailyBook, Date date, Human people, long fee);
}
