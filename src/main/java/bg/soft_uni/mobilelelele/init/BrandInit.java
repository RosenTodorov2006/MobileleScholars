package bg.soft_uni.mobilelelele.init;

import bg.soft_uni.mobilelelele.models.entities.Brand;
import bg.soft_uni.mobilelelele.models.entities.BrandNames;
import bg.soft_uni.mobilelelele.models.entities.Model;
import bg.soft_uni.mobilelelele.models.entities.ModelNames;
import bg.soft_uni.mobilelelele.repositories.BrandRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class BrandInit implements CommandLineRunner {
    private final BrandRepository brandRepository;

    public BrandInit(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(brandRepository.count() == 0){
            for (BrandNames brandNames : BrandNames.values()){
                this.brandRepository.save(new Brand(LocalDateTime.now(), LocalDateTime.now(), brandNames.name()));
            }
        }
    }
}
