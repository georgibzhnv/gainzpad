package gainzpad.service;

import gainzpad.model.dto.TrainersWorkoutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainersWorkoutService {

    List<TrainersWorkoutDTO>getAll();

    void save(TrainersWorkoutDTO trainersWorkoutDTO);
}
