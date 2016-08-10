package me.sutong.java.constraintValidators;


import me.sutong.java.constraints.ElementMin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Created by tong on 1/14/16.
 */
public class ElementMinValidator implements ConstraintValidator<ElementMin, Object> {
    private BigDecimal value;

    @Override
    public void initialize(ElementMin constraintAnnotation) {
        this.value = new BigDecimal(constraintAnnotation.value());
    }

    private Boolean isValidCollection(Collection collection) {
        for (Object item : collection) {
            BigDecimal itemValue = new BigDecimal(item.toString());

            if (itemValue.compareTo(this.value) == -1) {
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
        }
        if (value instanceof Map) {
            return isValidCollection(((Map) value).values());
        }

        return false;
    }
}
