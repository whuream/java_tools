package me.sutong.java.tools;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tong on 8/10/16.
 */
public class Merge {
    public static <R, T, K, V> void merge(Stream<? extends R> r, final Stream<? extends T> t,
        final Function<? super T, ? extends K> keyMapperT, final Function<? super T, ? extends V> valueMapperT,
        final Function<? super R, ? extends K> keyMapperR, final BiConsumer<? super R, ? super V> consumerR) {

        Map<K, V> map = t.collect(Collectors.toMap(keyMapperT, valueMapperT, (a, b) -> b));

        r.forEach(item -> consumerR.accept(item, map.get(keyMapperR.apply(item))));
    }
}
