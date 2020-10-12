package seedu.fma.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.fma.model.exercise.Exercise;

/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercises {

    public static final Exercise SIT_UP = new ExerciseBuilder().withName("Sit up")
            .withCaloriesPerRep(20).build();
    public static final Exercise JUMPING_JACK = new ExerciseBuilder().withName("Jumping jacks")
            .withCaloriesPerRep(15).build();
    public static final Exercise PULL_UP = new ExerciseBuilder().withName("Pull up")
            .withCaloriesPerRep(30).build();

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns an {@code LogBook} with all the typical exercises.
     */
    // TODO: Add test method for addressbook for exercises
    //    public static LogBook getTypicalLogBook() {
    //        LogBook ab = new LogBook();
    //        for (Exercise exercise : getTypicalExercises()) {
    //            ab.addExercise(exercise);
    //        }
    //        return ab;
    //    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(SIT_UP, JUMPING_JACK, PULL_UP));
    }
}
