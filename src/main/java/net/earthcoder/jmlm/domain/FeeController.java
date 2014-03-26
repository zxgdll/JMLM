package net.earthcoder.jmlm.domain;

import java.util.Date;

public final class FeeController {
    
    private static final long OUT_LEVEL = 600000;

    private boolean frozen = false;
    
    private Fee counselingFee = new CounselingFee();
    private Fee operatingExpenses = new OperatingExpenses();
    private Fee initialFee = new InitialFee();

    private void addFee(Date date, Human content, Fee fee) {
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

    public void addInitialFee(Date date, Human content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, initialFee);
        }
    }

    public void addCounselingFee(Date date, Human content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, counselingFee);
        }
    }

    public void addOperatingExpenses(Date date, Human content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, operatingExpenses);
        }
    }

    public Fee getCounselingFee() {
        return counselingFee;
    }

    public Fee getOperatingExpenses() {
        return operatingExpenses;
    }

    public Fee getInitialFee() {
        return initialFee;
    }
}
