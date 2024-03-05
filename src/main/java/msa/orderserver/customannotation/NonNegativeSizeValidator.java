package msa.orderserver.customannotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class NonNegativeSizeValidator implements ConstraintValidator<NonNegativeSize, Integer> {

    @Override
    public void initialize(NonNegativeSize constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value >= 0;
    }


}
