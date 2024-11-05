package bg.soft_uni.mobilelelele.service;

import bg.soft_uni.mobilelelele.models.dtos.RegisterSeedDto;
import bg.soft_uni.mobilelelele.models.entities.Role;
import bg.soft_uni.mobilelelele.models.entities.User;
import bg.soft_uni.mobilelelele.repositories.RoleRepository;
import bg.soft_uni.mobilelelele.repositories.UserRepository;
import bg.soft_uni.mobilelelele.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Random random;
    @Mock
    private RoleRepository roleRepository;
    private UserServiceImpl userService;
    @BeforeEach
    public void setUp(){
        this.userService = new UserServiceImpl(userRepository, modelMapper, passwordEncoder, random, roleRepository);
    }
    @Test
    public void saveNewUserTest(){
        RegisterSeedDto registerSeedDto = new RegisterSeedDto();
        registerSeedDto.setEmail("random@gmail.com");
        registerSeedDto.setPassword("password");
        registerSeedDto.setFirstName("Rosen");
        registerSeedDto.setLastName("Todorov");

        User user = new User();
        user.setEmail("random@gmail.com");
        user.setPassword("password");
        user.setFirstName("Rosen");
        user.setLastName("Todorov");
        user.setRole(new Role());

        Mockito.when(passwordEncoder.encode(registerSeedDto.getPassword())).thenReturn(registerSeedDto.getPassword()+registerSeedDto.getPassword());
        Mockito.when(random.nextDouble()).thenReturn(25.0);
        Mockito.when(modelMapper.map(registerSeedDto, User.class)).thenReturn(user);
        this.userService.saveNewUser(registerSeedDto);
        verify(userRepository).saveAndFlush(userArgumentCaptor.capture());
        User value = userArgumentCaptor.getValue();
        Assertions.assertEquals(value.getPassword(), "passwordpassword");
        Assertions.assertEquals(value.getEmail(), user.getEmail());
        Assertions.assertEquals(value.getFirstName(), user.getFirstName());
        Assertions.assertEquals(value.getLastName(), user.getLastName());
    }
}
