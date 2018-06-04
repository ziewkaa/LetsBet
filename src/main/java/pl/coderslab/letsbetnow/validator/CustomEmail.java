package pl.coderslab.letsbetnow.validator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = CustomEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface CustomEmail {

    String message() default "{customEmail.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
