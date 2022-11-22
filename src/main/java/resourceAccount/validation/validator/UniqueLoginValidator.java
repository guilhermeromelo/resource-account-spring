package resourceAccount.validation.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import resourceAccount.repository.UserRepository;
import resourceAccount.validation.UniqueEmail;
import resourceAccount.validation.UniqueLogin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
	private static final Logger logger = LoggerFactory.getLogger(UniqueLoginValidator.class);
	@Autowired
	private UserRepository repository;

	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
		if (login != null && !login.isBlank()) {
			long quantos = repository.countByLoginContainingIgnoreCase(login);
			logger.debug("Login {} aparece no BD {} vezes", login, quantos);
			return quantos == 0;
		} else {
			return true;
		}
	}

}
