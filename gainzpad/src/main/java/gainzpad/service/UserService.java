package gainzpad.service;

import gainzpad.model.dto.UserRegisterDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean registerUser(UserRegisterDTO dto);
}
