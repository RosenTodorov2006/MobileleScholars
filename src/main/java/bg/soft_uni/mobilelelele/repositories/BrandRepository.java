package bg.soft_uni.mobilelelele.repositories;

import bg.soft_uni.mobilelelele.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
