package resourceAccount.controller;

import com.sun.net.httpserver.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import resourceAccount.dto.UpdateUserEmailDTO;
import resourceAccount.model.User;
import resourceAccount.response.ResponseHandler;
import resourceAccount.shared.JwtUtils;
import resourceAccount.validation.service.UserService;

import javax.validation.Valid;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("private/user")
public class UserControllerPrivate {
    private static final Logger logger = LoggerFactory.getLogger(UserControllerPrivate.class);
    @Autowired
    private UserService userService;

        @GetMapping("/getOwnUserData")
    public ResponseEntity getOwnUserData(@RequestHeader LinkedHashMap<String,String> headers) {
        String jwtToken = headers.get("authorization");
        String userLogin = JwtUtils.getUserLoginFromJwtToken(jwtToken);
        User user = userService.getByLogin(userLogin);
        ResponseEntity response = user != null ?
                ResponseHandler.generateResponse("Usuário Logado!", HttpStatus.OK, user)
                : ResponseHandler.generateResponse("Usuário não encontrado!", HttpStatus.BAD_REQUEST, null);
        return response;
    }



    @PutMapping("/update-email")
    public ResponseEntity updateUserEmail(@RequestBody @Valid UpdateUserEmailDTO updateUserEmailDTO, BindingResult result) {
        if (result.hasErrors()) {
            logger.debug("O email recebido para alterar não é válido");
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
            return ResponseHandler.generateResponse("Usuário Não foi Alterado! " + erros, HttpStatus.UNPROCESSABLE_ENTITY, updateUserEmailDTO);
        } else {
            User userToUpdate = this.userService.getById(updateUserEmailDTO.getId());
            if(userToUpdate != null){
                userToUpdate.setEmail(updateUserEmailDTO.getEmail());
                userService.save(userToUpdate);
                return ResponseHandler.generateResponse("Email alterado com sucesso!", HttpStatus.CREATED, userToUpdate);
            } else {
                return ResponseHandler.generateResponse("O usuário não foi encontrado!", HttpStatus.NOT_FOUND, null);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Boolean userWasDeleted = this.userService.deleteUserById(id);
        if(userWasDeleted)
            return ResponseHandler.generateResponse("O usuário foi removido com sucesso!", HttpStatus.OK, null);
        else
            return ResponseHandler.generateResponse("O usuário não foi encontrado!", HttpStatus.NOT_FOUND, null);
    }
}
