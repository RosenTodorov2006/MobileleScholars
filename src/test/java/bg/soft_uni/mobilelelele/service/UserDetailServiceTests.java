package bg.soft_uni.mobilelelele.service;

import bg.soft_uni.mobilelelele.models.entities.Role;
import bg.soft_uni.mobilelelele.models.entities.User;
import bg.soft_uni.mobilelelele.repositories.UserRepository;
import bg.soft_uni.mobilelelele.services.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceTests {
    @Mock
    private UserRepository userRepository;

    private UserDetailsServiceImpl test;
    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setPassword("123");
        testUser.setEmail("random@fmail.com");
        testUser.setRole(new Role());
        test = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void testLoadUserByUsername() {
        Mockito.when(userRepository.findByEmail("random@fmail.com"))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = this.test.loadUserByUsername("random@fmail.com");

        Assertions.assertEquals(testUser.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
    }
    @Test
    public void invalidTestLoadUserByUsername(){
        Assertions.assertThrows(UsernameNotFoundException.class, ()-> this.test.loadUserByUsername("wddwdwdwdwd"));
    }
}
