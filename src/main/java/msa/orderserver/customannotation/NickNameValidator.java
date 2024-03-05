package msa.orderserver.customannotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NickNameValidator implements ConstraintValidator<NickName,String> {
    @Override
    public void initialize(NickName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("[a-zA-Z0-9 ]{1,10}");
    }
}
