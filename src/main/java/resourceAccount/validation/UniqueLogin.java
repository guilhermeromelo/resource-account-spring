package resourceAccount.validation;

import resourceAccount.validation.validator.UniqueLoginValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueLoginValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface UniqueLogin {

	public String message() default "Já existe um usuário com esse login";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default{};

}