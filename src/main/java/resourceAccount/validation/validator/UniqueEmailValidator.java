package resourceAccount.validation.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import resourceAccount.repository.UserRepository;
import resourceAccount.validation.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private static final Logger logger = LoggerFactory.getLogger(UniqueEmailValidator.class);
	
	@Autowired
	private UserRepository repository;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email != null && !email.isBlank()) {
			long quantos = repository.countByEmailContainingIgnoreCase(email);
			logger.debug("Email {} aparece no BD {} vezes", email, quantos);
			return quantos == 0;
		} else {
			return true;
		}
	}

}
