package me.sutong.java.constraintValidators;


import me.sutong.java.constraints.ElementUnique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by tong on 1/14/16.
 */
public class ElementUniqueValidator implements ConstraintValidator<ElementUnique, Object> {
    private BigDecimal value;

    @Override
    public void initialize(ElementUnique constraintAnnotation) {
    }

    private Boolean isValidCollection(Collection collection) {
        return new HashSet<>(collection).size() == collection.size();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof Set) {
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
