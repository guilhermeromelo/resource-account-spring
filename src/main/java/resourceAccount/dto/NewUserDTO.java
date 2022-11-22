package resourceAccount.dto;

import resourceAccount.validation.UniqueEmail;
import resourceAccount.validation.UniqueLogin;
import resourceAccount.validation.ValidEmail;
import resourceAccount.validation.ValidPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class NewUserDTO {

    private Long id;
    @NotBlank(message = "O nome é obrigatório; ")
    @Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
    private String nome;
    @NotBlank(message = "O login é obrigatório; ")
    @Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
    @UniqueLogin
    private String login;
    @NotBlank(message = "O email é obrigatório; ")
    @Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
    @ValidEmail
    @UniqueEmail
    private String email;
    @NotBlank(message = "A senha é obrigatória; ")
    @Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
    @ValidPassword
    private String senha;

    //GETTERS AND SETTERS ----------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    //ToString, Equals and HashCode ----------------------------------------------------------------------
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + senha + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewUserDTO newUserDTO = (NewUserDTO) o;
        return Objects.equals(id, newUserDTO.id) && Objects.equals(nome, newUserDTO.nome) &&
                Objects.equals(login, newUserDTO.login) && Objects.equals(email, newUserDTO.email) &&
                Objects.equals(senha, newUserDTO.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, login, email, senha);
    }
}
