package gainzpad.service.impl;

import gainzpad.model.dto.ExerciseDTO;
import gainzpad.model.mapper.ExerciseMapper;
import gainzpad.repository.ExerciseRepository;
import gainzpad.service.ExerciseService;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper = Mappers.getMapper(ExerciseMapper.class);

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(exerciseMapper::toDto)
                .collect(Collectors.toList());
    }
}
