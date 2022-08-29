package net.brogli.broglisbugs.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum HerculesBeetleVariant {
    DEFAULT(0),
    LIGHT(1);

    private static final HerculesBeetleVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(HerculesBeetleVariant::getId)).toArray(HerculesBeetleVariant[]::new);
    private final int id;

    HerculesBeetleVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static HerculesBeetleVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
