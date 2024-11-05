package bg.soft_uni.mobilelelele.services.impl;

import bg.soft_uni.mobilelelele.repositories.RoleRepository;
import bg.soft_uni.mobilelelele.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
