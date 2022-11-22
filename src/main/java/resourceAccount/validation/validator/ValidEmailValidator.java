package resourceAccount.validation.validator;


import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resourceAccount.validation.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {

	private static final Logger logger = LoggerFactory.getLogger(ValidEmailValidator.class);
		
	//Usando commons.validator
	private static final EmailValidator validator = EmailValidator.getInstance();

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email != null && !email.isBlank()) {
			boolean resultado = validator.isValid(email);
			logger.debug("O e-mail {} foi considerado {}", email, resultado?"válido":"inválido");
			return resultado;
		}
		return true;
	}

}