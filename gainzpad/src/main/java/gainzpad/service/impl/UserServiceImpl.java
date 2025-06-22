package gainzpad.service.impl;

import gainzpad.model.dto.UserRegisterDTO;
import gainzpad.model.entity.user.UserEntity;
import gainzpad.repository.UserRepository;
import gainzpad.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerUser(UserRegisterDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())){
            return false;
        }

        UserEntity user = new UserEntity()
                .setUsername(dto.getUsername())
                .setEmail(dto.getEmail())
                .setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return true;
    }
}
