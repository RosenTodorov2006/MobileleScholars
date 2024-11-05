package bg.soft_uni.mobilelelele.repositories;

import bg.soft_uni.mobilelelele.models.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
