package resourceAccount.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import resourceAccount.dto.NewUserDTO;
import resourceAccount.model.User;
import resourceAccount.response.ResponseHandler;
import resourceAccount.validation.service.UserService;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.time.Duration;

@RestController
@RequestMapping("/public/user")
public class UserControllerPublic {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserControllerPublic(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserControllerPublic.class);
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity postNewUser(@RequestBody @Valid NewUserDTO newUserDTO, BindingResult result) {
        if (result.hasErrors()) {
            logger.debug("O usuário recebido para inserir não é válido");
            logger.debug("Erros encontrados:");
            String erros = "";
            for(FieldError erro : result.getFieldErrors()) {
                logger.debug("{}", erro.getDefaultMessage());
                erros += erro.getDefaultMessage() + "; ";
            }
            for(ObjectError erro: result.getGlobalErrors()) {
                logger.debug("{}", erro.getDefaultMessage());
                erros += erro.getDefaultMessage();
            }
            return ResponseHandler.generateResponse("Usuário Não foi Cadastrado! " + erros, HttpStatus.UNPROCESSABLE_ENTITY, newUserDTO);
        } else {
            User newUser = new User(newUserDTO);
            newUser.setSenha(passwordEncoder.encode(newUserDTO.getSenha()));
            userService.save(newUser);
            createOauth2Client(newUser);
            return ResponseHandler.generateResponse("Usuário Cadastrado com sucesso!", HttpStatus.CREATED,newUser);
        }
    }

    private void createOauth2Client(User user){
        RegisteredClient newClient = RegisteredClient
                .withId(user.getId().toString())
                .clientId(user.getLogin())
                .clientSecret(user.getSenha())
                .scope("ownuser.admin")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(10))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .build();

        JdbcRegisteredClientRepository registeredClientRepository =
                new JdbcRegisteredClientRepository(jdbcTemplate);
        registeredClientRepository.save(newClient);
    }
}
