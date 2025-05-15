package gainzpad;

import gainzpad.model.entity.RoleEntity;
import gainzpad.model.entity.UserEntity;
import gainzpad.model.enums.UserRoleEnum;
import gainzpad.repository.RoleRepository;
import gainzpad.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//public class DBInit implements CommandLineRunner {
//
//    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;
//
//    public DBInit(RoleRepository roleRepository, UserRepository userRepository) {
//        this.roleRepository = roleRepository;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        initUsers();
//    }
//
//    public void initUsers(){
//            RoleEntity adminRole = new RoleEntity();
//            adminRole.setRole(UserRoleEnum.ADMIN);
//
//            RoleEntity userRole = new RoleEntity();
//            userRole.setRole(UserRoleEnum.USER);
//
//            RoleEntity trainerRole = new RoleEntity();
//            trainerRole.setRole(UserRoleEnum.TRAINER);
//
//            roleRepository.saveAll(List.of(adminRole,userRole,trainerRole));
//
//            UserEntity admin = new UserEntity();
//            admin.setUsername("gbozhinov")
//                    .setEmail("gbozhinov17@gmail.com")
//                    .setPassword("topsecret")
//                    .setRole(List.of(adminRole,userRole));
//
//            userRepository.save(admin);
//
//            UserEntity stefi = new UserEntity();
//            stefi.setUsername("stefani")
//                    .setEmail("stefani@gmail.com")
//                    .setPassword("stefani")
//                    .setRole(List.of(userRole));
//
//            userRepository.save(stefi);
//        }
//}
