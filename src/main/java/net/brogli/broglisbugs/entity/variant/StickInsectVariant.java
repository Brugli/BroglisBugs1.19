package net.brogli.broglisbugs.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum StickInsectVariant {
    DEFAULT(0),
    DARK(1);

    private static final StickInsectVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(StickInsectVariant::getId)).toArray(StickInsectVariant[]::new);
    private final int id;

    StickInsectVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static StickInsectVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
