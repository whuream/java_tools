package me.sutong.java.constraintValidators;

import com.alibaba.fastjson.JSON;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by tong on 8/17/16.
 */
public class Validator {

    public static void validate(Object object, Consumer<? super String> errMsgConsumer) {
        Set<ConstraintViolation<Object>> result =
            Validation.buildDefaultValidatorFactory().getValidator().validate(object);
        String errMsg = result.stream().map(t -> String
            .format("field %s value %s illegal: %s", t.getPropertyPath().toString(), JSON.toJSON(t.getInvalidValue()),
                t.getMessage())).collect(Collectors.joining("\n"));
        if (errMsg.length() > 0) {
            errMsgConsumer.accept(errMsg);
        }

    }
}
