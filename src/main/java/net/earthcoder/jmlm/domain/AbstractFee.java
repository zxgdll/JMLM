package net.earthcoder.jmlm.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFee<E extends Human> implements Fee<E> {

    protected Book book;

    public AbstractFee(CapStrategy capStrategy) {
       book = new Book(capStrategy);
    }

    public long sum() {
        return book.getSum();
    }

    public abstract long defaultValue();
    public abstract void add(Date date, E people);

    public void add(Date date, E people, long fee) {
        try {
            this.book.write(date, people, fee);
        } catch (OverDailyCapException e) {
            e.printStackTrace();
        }
    }
}
