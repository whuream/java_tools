package me.sutong.java.tools;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by tong on 8/11/16.
 */
public class MergeTest {

    public static class User {
        private Long id;
        private String name;

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            name = name;
        }
    }


    public static class UserInfo {
        private Long id;
        private String info;

        public UserInfo(Long id, String info) {
            this.id = id;
            this.info = info;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    @Test
    public void testMerge() throws Exception {

        List<UserInfo> userInfoList = Arrays.asList(new UserInfo(1l, null), new UserInfo(2l, null));

        Stream<User> streamT = Stream.of(new User(1l, "sutong"), new User(2l, "tallbear"));

        Merge.merge(userInfoList.stream(), streamT, User::getId, User::getName, UserInfo::getId, UserInfo::setInfo);

        System.out.println(JSON.toJSONString(userInfoList));
    }
}