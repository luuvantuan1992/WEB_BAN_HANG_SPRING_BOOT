package vn.t3h.be2204.dto.validation.gmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GmailValidation implements ConstraintValidator<GmailIF, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.endsWith("@gmail.com");
    }
}
