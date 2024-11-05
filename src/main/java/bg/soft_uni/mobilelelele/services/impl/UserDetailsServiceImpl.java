package bg.soft_uni.mobilelelele.services.impl;

import bg.soft_uni.mobilelelele.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(UserDetailsServiceImpl::mapToUser)
                .orElseThrow(()->new UsernameNotFoundException("Invalid email"));
    }
    private static UserDetails mapToUser(bg.soft_uni.mobilelelele.models.entities.User user){
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_"+user.getRole().getName()))
                .disabled(false)
                .build();
    }
}
