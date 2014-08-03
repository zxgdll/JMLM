package net.earthcoder.jmlm.domain;

/**
 * Created by Wei on 2014/8/3.
 */
public final class NodeSales {

    private static final long SALES_INIT_VALUE = 0;

    private long[] history = {SALES_INIT_VALUE, SALES_INIT_VALUE};
    private long[] current = {SALES_INIT_VALUE, SALES_INIT_VALUE};

    protected void leftResultAdd(long feeValue) {
        history[0] += feeValue;
        flushCurrentSales();
    }

    protected void rightResultAdd(long feeValue) {
        history[1] += feeValue;
        flushCurrentSales();
    }

    private void flushCurrentSales() {
        long tmp = history[0] - history[1];
        if (tmp > 0) {
            current[0] = tmp;
            current[1] = 0;
        } else if (tmp < 0) {
            current[0] = 0;
            current[1] = -tmp;
        } else {
            current[0] = current[1] = tmp;
        }
    }

    protected long getLeftHistorySales() {
        return history[0];
    }

    protected long getRightHistorySales() {
        return history[1];
    }

    protected long getLeftCurrentSales() {
        return current[0];
    }

    protected long getRightCurrentSales() {
        return current[1];
    }
}
