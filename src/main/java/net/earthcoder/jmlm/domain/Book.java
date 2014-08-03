package net.earthcoder.jmlm.domain;

import java.util.*;

/**
 * Created by Wei on 2014/3/22.
 */
public final class Book{

    private Map<Date, DailyBook> dailyBooks;
    private long sum;
    private CapStrategy capStrategy;

    public Book(CapStrategy capStrategy) {
        dailyBooks = new HashMap<Date, DailyBook>();
        this.capStrategy = capStrategy;
    }

    protected Map<Date, List<BillItem>> getBillList() {
        Map<Date, List<BillItem>> map = new HashMap<Date, List<BillItem>>();
        for (Date date : dailyBooks.keySet()) {
            map.put(date, dailyBooks.get(date).getBillItemList());
        }
        return map;
    }

    public void write(Date date, Human people, long fee) throws OverDailyCapException {
        if (hasDailyBook(date)) {
            capStrategy.getFee(getDailyBook(date), date, people, fee);
        } else {
            DailyBook dailyBook = new DailyBook();
            dailyBook.addFee(date, people, fee);
            dailyBooks.put(date, dailyBook);
        }
        flushSum();
    }

    private boolean hasDailyBook(Date date) {
        return null == getDailyBook(date) ? false : true;
    }

    private DailyBook getDailyBook(Date date) {
        for (Date dateInBook: dailyBooks.keySet()) {
            if (this.isSameDay(dateInBook, date)) {
                return dailyBooks.get(dateInBook);
            }
        }
        return null;
    }

    private void flushSum() {
        sum = 0;
        for (DailyBook dailyBook : dailyBooks.values()) {
            sum += dailyBook.getDailySum();
        }
    }

    private boolean isSameDay(Date dateInBook, Date dateWillWrite) {
        return Util.trunc(dateInBook).equals(Util.trunc(dateWillWrite));
    }

    public long getSum() {
        return sum;
    }
}
