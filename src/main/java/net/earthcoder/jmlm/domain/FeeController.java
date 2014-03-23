package net.earthcoder.jmlm.domain;

import java.util.Date;

public final class FeeController<E extends Human> {
    
    private static final long OUT_LEVEL = 600000;

    private boolean frozen = false;
    
    private Fee counselingFee = new CounselingFee();
    private Fee operatingExpenses = new OperatingExpenses();

    public Fee getCounselingFee() {
        return counselingFee;
    }

    public Fee getOperatingExpenses() {
        return operatingExpenses;
    }
    
    private void addFee(Date date, E content, Fee fee) {
        long sum = counselingFee.sum() + operatingExpenses.sum();
        if (sum + fee.defaultValue() < OUT_LEVEL) {
            fee.add(date, content, fee.defaultValue());
        } else if (sum + fee.defaultValue() == OUT_LEVEL) {
            fee.add(date, content, fee.defaultValue());
            frozen = true;
        } else {
            fee.add(date, content, OUT_LEVEL - sum);
            frozen = true;
        }
    }

    public void addCounselingFee(Date date, E content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, counselingFee);
        }
    }

    public void addOperatingExpenses(Date date, E content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, operatingExpenses);
        }
    }
}
