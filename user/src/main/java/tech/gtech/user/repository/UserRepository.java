package tech.gtech.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.gtech.user.domain.UserModel;
import tech.gtech.user.dto.UserDto;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
