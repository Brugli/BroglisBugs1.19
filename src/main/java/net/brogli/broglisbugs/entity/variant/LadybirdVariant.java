package net.brogli.broglisbugs.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum LadybirdVariant {
    DEFAULT(0),
    YELLOW(1),
    BLACK(2),
    ORANGE(3);

    private static final LadybirdVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(LadybirdVariant::getId)).toArray(LadybirdVariant[]::new);
    private final int id;

    LadybirdVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static LadybirdVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
