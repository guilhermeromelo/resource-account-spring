package resourceAccount.validation;

import resourceAccount.validation.validator.ValidEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface ValidEmail {

	public String message() default "O formato do e-mail não é válido";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default{};

}