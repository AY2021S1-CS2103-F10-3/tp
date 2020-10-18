package seedu.fma.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_JUMPING_JACKS;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalExercises.EXERCISE_A;
import static seedu.fma.testutil.TypicalExercises.EXERCISE_C;

import org.junit.jupiter.api.Test;

import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.util.Name;
import seedu.fma.testutil.ExerciseBuilder;

public class ExerciseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // null name
        assertThrows(NullPointerException.class, () -> new Exercise(null, 123));
    }

    @Test
    public void constructor_invalidCaloriesPerRep_throwsIllegalArgumentException() {
        // invalid caloriesPerRep
        assertThrows(IllegalArgumentException.class, () ->
                new Exercise(new Name("jumping jacks"), -10));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise exerciseCopy = new ExerciseBuilder(EXERCISE_A).build();
        assertEquals(exerciseCopy, EXERCISE_A);

        // same object -> returns true
        assertEquals(exerciseCopy, exerciseCopy);

        // different object -> returns false
        assertNotEquals(new ExerciseNotFoundException(), EXERCISE_A);

        // null -> returns false
        assertNotEquals(EXERCISE_A, null);

        // different exercise -> returns false
        assertNotEquals(EXERCISE_C, EXERCISE_A);

        // different name -> returns false
        Exercise editedSitUp = new ExerciseBuilder(EXERCISE_A).withName(VALID_EXERCISE_JUMPING_JACKS).build();
        assertNotEquals(editedSitUp, EXERCISE_A);
    }

    @Test
    void testHashCode() {
        assertEquals(EXERCISE_A.hashCode(), EXERCISE_A.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Flying kicks CaloriesPerRep: 15", EXERCISE_A.toString());
    }
}
