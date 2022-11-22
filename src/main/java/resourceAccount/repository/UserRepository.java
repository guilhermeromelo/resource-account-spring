package resourceAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resourceAccount.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    long countByEmailContainingIgnoreCase(String email);

    long countByLoginContainingIgnoreCase(String login);

    User findByLoginAndSenha(String login, String password);

}
