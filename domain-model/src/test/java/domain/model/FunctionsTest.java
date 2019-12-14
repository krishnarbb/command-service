
package domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for the functions in {@link Functions}.
 */
public class FunctionsTest {
    @Test
    public void executeAdditionShouldAdd() {
        final Job job = new Job(Task.ADD.name, "1,2");
        final JobResult jobResult = Functions.executeAddition.execute(job);
        assertEquals("3", jobResult.result);
    }

    @Test
    public void executeMultiplicationShouldMultiply() {
        final Job job = new Job(Task.MULTIPLY.name, "5,3");
        final JobResult jobResult = Functions.executeMultiplication.execute(job);
        assertEquals("15", jobResult.result);
    }
}
