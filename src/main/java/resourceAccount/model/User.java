package resourceAccount.model;

import lombok.*;
import resourceAccount.dto.NewUserDTO;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@Getter @Setter
@ToString @EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String email;
    private String senha;

    public User() { }

    public User(NewUserDTO newUserDTO) {
        this.id = newUserDTO.getId();
        this.nome = newUserDTO.getNome();
        this.login = newUserDTO.getLogin();
        this.email = newUserDTO.getEmail();
        this.senha = newUserDTO.getSenha();
    }
}
