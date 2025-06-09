package gainzpad.service.impl;

import gainzpad.model.dto.TrainersWorkoutDTO;
import gainzpad.model.entity.TrainersWorkoutEntity;
import gainzpad.model.mapper.TrainersWorkoutMapper;
import gainzpad.repository.TrainersWorkoutRepository;
import gainzpad.service.TrainersWorkoutService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainersWorkoutServiceImpl implements TrainersWorkoutService {

    private final TrainersWorkoutRepository trainersWorkoutRepository;
    private final TrainersWorkoutMapper trainersWorkoutMapper;

    public TrainersWorkoutServiceImpl(TrainersWorkoutRepository trainersWorkoutRepository, TrainersWorkoutMapper trainersWorkoutMapper) {
        this.trainersWorkoutRepository = trainersWorkoutRepository;
        this.trainersWorkoutMapper = trainersWorkoutMapper;
    }

    @Override
    public List<TrainersWorkoutDTO> getAll() {
        return trainersWorkoutRepository.findAll()
                .stream().map(trainersWorkoutMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(TrainersWorkoutDTO trainersWorkoutDTO) {
        TrainersWorkoutEntity trainersWorkoutEntity = trainersWorkoutMapper.toEntity(trainersWorkoutDTO);
        trainersWorkoutRepository.save(trainersWorkoutEntity);
    }

    @Override
    public TrainersWorkoutDTO getById(Long id) {
        TrainersWorkoutEntity trainersWorkoutEntity = trainersWorkoutRepository.findById(id).orElseThrow();
        return trainersWorkoutMapper.toDto(trainersWorkoutEntity);
    }

    @Override
    public void deleteById(Long id) {
        trainersWorkoutRepository.deleteById(id);
    }

}
