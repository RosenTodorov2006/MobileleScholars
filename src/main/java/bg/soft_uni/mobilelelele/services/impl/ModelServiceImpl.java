package bg.soft_uni.mobilelelele.services.impl;

import bg.soft_uni.mobilelelele.repositories.ModelRepository;
import bg.soft_uni.mobilelelele.services.ModelService;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }
}
