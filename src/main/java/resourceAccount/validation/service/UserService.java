package resourceAccount.validation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resourceAccount.model.User;
import resourceAccount.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void remover(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public User getByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Transactional
    public User getById(Long id){
        return userRepository.findById(id).get();
    }

    @Transactional
    public boolean deleteUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
