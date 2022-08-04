package vn.t3h.be2204.dto.validation.password;

import vn.t3h.be2204.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidation implements ConstraintValidator<PasswordAnotation, UserDto> {
    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        return value.getPassword().equals(value.getRePassword());
    }
}
