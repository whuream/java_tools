package me.sutong.java;

import com.alibaba.fastjson.JSON;
import me.sutong.java.constraints.ElementMin;
import me.sutong.java.constraints.ElementNotNull;
import me.sutong.java.constraints.SecondsFuture;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tong on 3/31/16.
 */
public class ConstraintsTest {

    public static class Students {
        @ElementMin(value = "0") private List<Long> ages;

        @ElementNotNull private List<String> names;

        @SecondsFuture private Long future;

        public List<Long> getAges() {
            return ages;
        }

        public void setAges(List<Long> ages) {
            this.ages = ages;
        }

        public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }

        public Long getFuture() {
            return future;
        }

        public void setFuture(Long future) {
            this.future = future;
        }

        public Students(List<Long> ages, List<String> names, Long future) {
            this.ages = ages;
            this.names = names;
            this.future = future;
        }

        public Students() {

        }
    }

    public static String validate(Object object) {
        Set<ConstraintViolation<Object>> result =
            Validation.buildDefaultValidatorFactory().getValidator().validate(object);

        return result.stream().map(t -> String
            .format("field %s value %s illegal: %s", t.getPropertyPath().toString(), JSON.toJSON(t.getInvalidValue()),
                t.getMessage())).collect(Collectors.joining("\n"));

    }

    @Test
    public void test() {
        Students students = new Students(Arrays.asList(1l, 2l, -1l), Arrays.asList("", "SuTong", null), 100l);

        System.out.println(validate(students));
    }
}
