package net.brogli.broglisbugs.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum SnailVariant {
    DEFAULT(0),
    STRIPED(1);

    private static final SnailVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(SnailVariant::getId)).toArray(SnailVariant[]::new);
    private final int id;

    SnailVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static SnailVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
