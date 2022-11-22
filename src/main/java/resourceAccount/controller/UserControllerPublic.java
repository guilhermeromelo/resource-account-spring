package resourceAccount.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import resourceAccount.dto.NewUserDTO;
import resourceAccount.model.User;
import resourceAccount.response.ResponseHandler;
import resourceAccount.service.UserService;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/user")
public class UserControllerPublic {

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
            userService.save(newUser);
            return ResponseHandler.generateResponse("Usuário Cadastrado com sucesso!", HttpStatus.CREATED,newUser);
        }
    }
}
