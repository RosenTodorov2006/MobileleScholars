package bg.soft_uni.mobilelelele.init;

import bg.soft_uni.mobilelelele.models.entities.BrandNames;
import bg.soft_uni.mobilelelele.models.entities.Model;
import bg.soft_uni.mobilelelele.models.entities.ModelNames;
import bg.soft_uni.mobilelelele.repositories.BrandRepository;
import bg.soft_uni.mobilelelele.repositories.ModelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ModelInit implements CommandLineRunner {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;



    public ModelInit(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(modelRepository.count() == 0){
            for (ModelNames modelName : ModelNames.values()){
                this.modelRepository.save(new Model(1, LocalDateTime.now(), 2000
                        , "https://images.pexels.com/photos/3729464/pexels-photo-3729464.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        LocalDateTime.now(), modelName.name(), 2002, brandRepository.findByName("BMW").get()));
            }
        }
    }
}
