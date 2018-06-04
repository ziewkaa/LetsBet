package pl.coderslab.letsbetnow.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomEmailValidator implements ConstraintValidator<CustomEmail,String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String CUSTOM_EMAIL = "^[A-Za-z0-9-_]+(.[A-Za-z0-9-_]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public void initialize(CustomEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        pattern = Pattern.compile(CUSTOM_EMAIL);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
