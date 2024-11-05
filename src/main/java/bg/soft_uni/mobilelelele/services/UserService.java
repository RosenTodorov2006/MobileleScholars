package bg.soft_uni.mobilelelele.services;

import bg.soft_uni.mobilelelele.models.dtos.RegisterSeedDto;

public interface UserService {
    void saveNewUser(RegisterSeedDto registerSeedDto);

    boolean isNotValidEmail(String email);


}
