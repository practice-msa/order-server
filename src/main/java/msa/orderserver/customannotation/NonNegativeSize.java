package msa.orderserver.customannotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NonNegativeSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNegativeSize {
    String message() default "Value must be positive or zero";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}