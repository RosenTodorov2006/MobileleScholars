package bg.soft_uni.mobilelelele.repositories;

import bg.soft_uni.mobilelelele.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findByName(String model);
}
