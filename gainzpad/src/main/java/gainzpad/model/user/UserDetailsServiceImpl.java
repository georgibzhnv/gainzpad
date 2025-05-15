//package gainzpad.model.user;
//
//import gainzpad.model.entity.UserEntity;
//import gainzpad.repository.UserRepository;
//import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import java.util.Optional;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserEntity>userOpt = userRepository.findOneByEmail(username);
//
//        LOGGER.debug("Trying to load user{}. Successful? {}",
//                username,userOpt.isPresent());
//
//        return null;
//    }
//
//}
