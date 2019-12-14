
package domain.model;

import static domain.model.Constants.ERROR_EMPTY_PAYLOAD;
import static domain.model.Constants.ERROR_EMPTY_TASK;
import static domain.model.Constants.ERROR_PAYLOAD_MORE_THAN_2_NUMBERS;
import static domain.model.Constants.ERROR_PAYLOAD_NOT_COMMA_SEPARATED;
import static domain.model.Constants.ERROR_UNSUPPORTED_TASK;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

/**
 * Unit tests for defaultValidation rules in {@link Validator}.
 */
public class ValidatorTest {

    @Test
    public void shouldValidateEmptyTask() {
        final String emptyTask = "";
        final Job job = new Job(emptyTask, "1,2");

        final Optional<String> error = Validator.defaultValidator(job);
        assertEquals(ERROR_EMPTY_TASK, error.get());
    }

    @Test
    public void shouldValidateUnsupportedTask() {
        final Job job = new Job("unsupported", "1,2");
        final Optional<String> error = Validator.defaultValidator(job);
        assertEquals(ERROR_UNSUPPORTED_TASK, error.get());
    }

    @Test
    public void shouldValidateEmptyPayload() {
        final String emptyPayload = "";
        final Job job = new Job(Task.ADD.name, emptyPayload);

        final Optional<String> error = Validator.defaultValidator(job);
        assertEquals(ERROR_EMPTY_PAYLOAD, error.get());
    }

    @Test
    public void shouldValidatePayloadWithoutCommaSeparator() {
        final String payloadWithOutCommas = "1;2";
        final Job job = new Job(Task.ADD.name, payloadWithOutCommas);

        final Optional<String> error = Validator.defaultValidator(job);
        assertEquals(ERROR_PAYLOAD_NOT_COMMA_SEPARATED, error.get());
    }

    @Test
    public void shouldValidatePayloadWithMoreThan2Values() {
        final String payloadWithMoreValues = "1,2,3";
        final Job job = new Job(Task.MULTIPLY.name, payloadWithMoreValues);

        final Optional<String> error = Validator.defaultValidator(job);
        assertEquals(ERROR_PAYLOAD_MORE_THAN_2_NUMBERS, error.get());
    }

}
