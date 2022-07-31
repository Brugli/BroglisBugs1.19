package net.brogli.broglisbugs.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum SlugVariant {
    DEFAULT(0),
    BROWN(1);

    private static final SlugVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(SlugVariant::getId)).toArray(SlugVariant[]::new);
    private final int id;

    SlugVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static SlugVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
