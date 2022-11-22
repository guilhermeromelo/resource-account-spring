package resourceAccount.validation.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resourceAccount.validation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

//pelo menos uma letra
//pelo menos 1 caractere especial
//tamanho mínimo 5
//tamanho máximo 12
public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

	private static final Logger logger = LoggerFactory.getLogger(ValidPasswordValidator.class);
	
	private static final List<String> LISTA_CARACTERES_ESPECIAIS = Arrays.asList("!", "@", "#", "$", "%", "&", "*", ".", ",", "~", "^", "/",
		"\\", "+", ":", ";", "=", "'", "`", "[", "]", "(", ")", "{", "}", "<", ">", "-", "_");

	private String mensagemFinal = "";
	
	@Override
	public boolean isValid(String nomeUsuario, ConstraintValidatorContext constraintValidatorContext) {
		mensagemFinal = "";
		if (nomeUsuario != null) {
			//Vai mostrar todos os erros de uma vez.
			boolean valido = peloMenosUmaLetra(nomeUsuario);
			valido &= peloMenosUmCaractereEspecial(nomeUsuario);
			valido &= tamanhoMinimo(nomeUsuario);
			valido &= tamanhoMaximo(nomeUsuario);


			if (!valido) {
				constraintValidatorContext.buildConstraintViolationWithTemplate(mensagemFinal)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
			}
			return valido;
		} else {
			return true;
		}
	}

	private boolean peloMenosUmaLetra(String nomeUsuario) {
		for (int pos = 0; pos < nomeUsuario.length(); pos++) {
			if (Character.isAlphabetic(nomeUsuario.charAt(pos))) {
				return true;
			}
		}
		acrescentarNaMensagemFinal("A senha deve possuir ao menos uma letra");
		return false;
	}
	
	private boolean peloMenosUmCaractereEspecial(String nomeUsuario) {
		logger.debug("Recebeu o nomeUsuario: {}", nomeUsuario);
		for (int pos = 0; pos < nomeUsuario.length(); pos++) {
			if (LISTA_CARACTERES_ESPECIAIS.contains(String.valueOf(nomeUsuario.charAt(pos)))) {
				logger.debug("O nomeUsuario: {} tem um caractere especial na posição: {}", nomeUsuario, pos);
				return true;
			}
		}
		acrescentarNaMensagemFinal("A senha deve possuir ao menos um caractere especial");
		return false;
	}
	
	private boolean tamanhoMinimo(String nomeUsuario) {
		if (nomeUsuario.length() > 5) {
			return true;
		} else {
			acrescentarNaMensagemFinal("A senha deve possuir um tamanho mínimo de 5 caracteres");
			return false;
		}
	}

	private boolean tamanhoMaximo(String nomeUsuario) {
		if (nomeUsuario.length() < 13) {
			return true;
		} else {
			acrescentarNaMensagemFinal("A senha deve possuir um tamanho máximo de 12 caracteres");
			return false;
		}
	}
	
	private void acrescentarNaMensagemFinal(String mensagem) {
		if (!mensagemFinal.isBlank()) {
			mensagemFinal += "; ";
		}
		mensagemFinal += mensagem;
	}
}