package me.sutong.java;

import com.alibaba.fastjson.JSON;
import me.sutong.java.constraintValidators.Validator;
import me.sutong.java.constraints.ElementMax;
import me.sutong.java.constraints.ElementMin;
import me.sutong.java.constraints.ElementNotNull;
import me.sutong.java.constraints.ElementUnique;
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

        @ElementMax(value = "2") private List<Long> eyes;

        @ElementUnique private List<Long> uid;

        public List<Long> getUid() {
            return uid;
        }

        public void setUid(List<Long> uid) {
            this.uid = uid;
        }

        public List<Long> getEyes() {
            return eyes;
        }

        public void setEyes(List<Long> eyes) {
            this.eyes = eyes;
        }

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

        public Students(List<Long> ages, List<String> names, Long future, List<Long> eyes, List<Long> uid) {
            this.ages = ages;
            this.names = names;
            this.future = future;
            this.eyes = eyes;
            this.uid = uid;
        }

        public Students() {

        }
    }

    @Test
    public void test() {
        Students students = new Students(Arrays.asList(1l, 2l, -1l), Arrays.asList("", "SuTong", null), 100l,
            Arrays.asList(1l, 2l, 3l, null), Arrays.asList(1l, null, null));

        Validator.validate(students, t -> System.out.printf(t));
    }
}
