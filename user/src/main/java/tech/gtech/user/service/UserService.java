package tech.gtech.user.service;

import jakarta.transaction.Transactional;
import jdk.jfr.StackTrace;
import org.springframework.stereotype.Service;
import tech.gtech.user.domain.UserModel;
import tech.gtech.user.exceptions.EmailAlreadyExistsException;
import tech.gtech.user.producer.UserProducer;
import tech.gtech.user.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel createUser(UserModel user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email já existe: " + user.getEmail());
        }
        UserModel newUser = userRepository.save(user);
        userProducer.sendUserCreatedEvent(newUser);
        return newUser;
    }

    public UserModel getUserById(UUID id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    public Iterable<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public UserModel updateUser(UserModel userModel) {
        return userRepository.save(userModel);
    }
}
