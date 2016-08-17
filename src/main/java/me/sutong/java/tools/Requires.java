package me.sutong.java.tools;

import java.util.function.Supplier;

/**
 * Created by tong on 8/16/16.
 */
public class Requires {
    public static <T> T requireNotNull(T t, Supplier<? extends RuntimeException> supplier) {
        if (t == null) {
            throw supplier.get();
        }

        return t;
    }

    public static void requireTrue(boolean b, Supplier<? extends RuntimeException> supplier) {
        if (!b) {
            throw supplier.get();
        }
    }

    public static void requireFalse(boolean b, Supplier<? extends RuntimeException> supplier) {
        if (b) {
            throw supplier.get();
        }
    }
}
