package es.otreze.services.implementations;

import es.otreze.dtos.SaveUserDTO;
import es.otreze.persistence.repositories.RoleRepository;
import es.otreze.persistence.security.Role;
import es.otreze.persistence.security.User;
import es.otreze.persistence.repositories.UserRepository;
import es.otreze.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    @Transactional
    public User registerOneCustomer(SaveUserDTO newUser) {
        User user = new User();
        user.setFullName(newUser.getFullName());
        user.setEmail(newUser.getEmail());
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        Optional<Role> rolesOptional = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        if (rolesOptional.isPresent()){
            roles.add(rolesOptional.orElseThrow());
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }
}
