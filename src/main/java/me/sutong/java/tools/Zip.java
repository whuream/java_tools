package me.sutong.java.tools;


import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by tong on 3/29/16.
 */
public class Zip {
    public static <T, U, R> Stream<R> zip(final Stream<? extends T> streamT, final Stream<? extends U> streamU,
        final BiFunction<? super T, ? super U, ? extends R> biFunction) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(streamT);
        Objects.requireNonNull(streamU);

        Spliterator<? extends T> spliteratorT = streamT.spliterator();
        Spliterator<? extends U> spliteratorU = streamU.spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int characteristics = spliteratorT.characteristics() & spliteratorU.characteristics() &
            ~(Spliterator.DISTINCT | Spliterator.SORTED);

        long zipSize = ((characteristics & Spliterator.SIZED) != 0) ?
            Math.min(spliteratorT.getExactSizeIfKnown(), spliteratorU.getExactSizeIfKnown()) :
            -1;

        Iterator<T> iteratorT = Spliterators.iterator(spliteratorT);
        Iterator<U> iteratorU = Spliterators.iterator(spliteratorU);
        Iterator<R> iteratorR = new Iterator<R>() {
            @Override
            public boolean hasNext() {
                return iteratorT.hasNext() && iteratorU.hasNext();
            }

            @Override
            public R next() {
                return biFunction.apply(iteratorT.next(), iteratorU.next());
            }
        };

        Spliterator<R> split = Spliterators.spliterator(iteratorR, zipSize, characteristics);
        return (streamT.isParallel() || streamU.isParallel()) ?
            StreamSupport.stream(split, true) :
            StreamSupport.stream(split, false);
    }
}
