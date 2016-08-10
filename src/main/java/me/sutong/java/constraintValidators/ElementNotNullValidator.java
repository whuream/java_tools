package me.sutong.java.constraintValidators;


import me.sutong.java.constraints.ElementNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Map;

/**
 * Created by tong on 1/14/16.
 */
public class ElementNotNullValidator implements ConstraintValidator<ElementNotNull, Object> {

    @Override
    public void initialize(ElementNotNull constraintAnnotation) {

    }

    private boolean isValidCollection(Collection value) {
        for (Object item : value) {
            if (item == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (value instanceof Collection) {
            return isValidCollection((Collection) value);
        } else
            return value instanceof Map && isValidCollection(((Map) value).values());
    }
}