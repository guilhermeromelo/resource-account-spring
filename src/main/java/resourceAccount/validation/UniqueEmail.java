package resourceAccount.validation;

import resourceAccount.validation.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface UniqueEmail {

	public String message() default "Já existe um usuário com esse e-mail";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default{};

}