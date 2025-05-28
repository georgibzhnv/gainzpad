package gainzpad.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", referencedColumnName = "id")
    private WorkoutEntity workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    private ExerciseEntity exercise;
    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SetEntity> sets = new ArrayList<>();
    @Column(nullable = false)
    private long restTime;
    private long timeSpent;


    public List<SetEntity> getSets() {
        return sets;
    }

    public WorkoutExercise setSets(List<SetEntity> sets) {
        this.sets = sets;
        return this;
    }

    public Long getId() {
        return id;
    }

    public WorkoutExercise setId(Long id) {
        this.id = id;
        return this;
    }

    public long getRestTime() {
        return restTime;
    }

    public WorkoutExercise setRestTime(long restTime) {
        this.restTime = restTime;
        return this;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public WorkoutExercise setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public WorkoutExercise setWorkout(WorkoutEntity workout) {
        this.workout = workout;
        return this;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public WorkoutExercise setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
        return this;
    }

}
