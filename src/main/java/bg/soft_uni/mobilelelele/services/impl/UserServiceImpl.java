package bg.soft_uni.mobilelelele.services.impl;

import bg.soft_uni.mobilelelele.models.dtos.RegisterSeedDto;
import bg.soft_uni.mobilelelele.models.entities.Role;
import bg.soft_uni.mobilelelele.models.entities.User;
import bg.soft_uni.mobilelelele.repositories.RoleRepository;
import bg.soft_uni.mobilelelele.repositories.UserRepository;
import bg.soft_uni.mobilelelele.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final Random random;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, Random random, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.random = random;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveNewUser(RegisterSeedDto registerSeedDto) {
        User map = modelMapper.map(registerSeedDto, User.class);
        map.setCreated(LocalDateTime.now());
        String encode = passwordEncoder.encode(map.getPassword());
        map.setPassword(encode);
        if(random.nextDouble()%5==0){
            Role role = roleRepository.findByName("Admin");
            map.setRole(role);
        }else{
            Role role = roleRepository.findByName("User");
            map.setRole(role);
        }
        this.userRepository.saveAndFlush(map);
    }

    @Override
    public boolean isNotValidEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
