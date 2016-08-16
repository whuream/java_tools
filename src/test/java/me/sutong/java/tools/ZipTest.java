package me.sutong.java.tools;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tong on 8/10/16.
 */
public class ZipTest {

    @Test
    public void testZip() throws Exception {
        System.out.println(Zip.zip(Stream.of(1l, 2l, 3l), Stream.of(4l, 5l, 6l), (aLong, aLong2) -> aLong + aLong2)
            .collect(Collectors.toList()));

        System.out.println(JSON.toJSONString(
            Zip.zip(Stream.of(1l, 2l, 3l), Stream.of(4l, 5l, 6l), new BiFunction<Long, Long, Pair<Long, Long>>() {
                @Override
                public Pair<Long, Long> apply(Long aLong, Long aLong2) {
                    return Pair.of(aLong, aLong2);
                }
            }).collect(Collectors.toList())));

        System.out.println(JSON.toJSONString(
            Zip.zip(Stream.of(1l, 2l, 3l), Stream.of(4l, 5l, 6l), Zip.toPair()).collect(Collectors.toList())));


        System.out.println(JSON.toJSONString(
            Zip.zip(Stream.of(1l, 2l, 3l), Stream.of(4l, 5l, 6l), Pair::of).collect(Collectors.toList())));
    }
}