package tech.gtech.user.service;

import jakarta.transaction.Transactional;
import jdk.jfr.StackTrace;
import org.springframework.stereotype.Service;
import tech.gtech.user.domain.UserModel;
import tech.gtech.user.producer.UserProducer;
import tech.gtech.user.repository.UserRepository;

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
        UserModel newUser = userRepository.save(user);
        userProducer.sendUserCreatedEvent(newUser);
        return newUser;
    }

}
