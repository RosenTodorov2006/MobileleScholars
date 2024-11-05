package bg.soft_uni.mobilelelele.repositories;

import bg.soft_uni.mobilelelele.models.entities.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(@NotNull String email, String password);
}
