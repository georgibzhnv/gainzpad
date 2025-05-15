package gainzpad.service;

import gainzpad.model.dto.WorkoutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkoutService {
    List<WorkoutDTO>getAll();
}
