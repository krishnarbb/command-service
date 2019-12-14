
package domain.model;

import static domain.model.Constants.ERROR_EMPTY_PAYLOAD;
import static domain.model.Constants.ERROR_EMPTY_TASK;
import static domain.model.Constants.ERROR_PAYLOAD_MORE_THAN_2_NUMBERS;
import static domain.model.Constants.ERROR_PAYLOAD_NOT_COMMA_SEPARATED;
import static domain.model.Constants.ERROR_UNSUPPORTED_TASK;
import static domain.model.Constants.PAYLOAD_SEPARATOR;

import java.util.Optional;

/**
 * Validates {@link Job}
 */
public interface Validator {
    /**
     * Validates the specified job parameters.
     *
     * @param job
     * @return
     *         error message if there is an error.
     */
    Optional<String> validate(final Job job);

    /**
     * Default validation rules applied before processing a job.
     *
     * @param job
     * @return
     *         if there is an error returns an error message
     */
    static Optional<String> defaultValidator(final Job job) {
        final String error = null;

        if (job.task == null || job.task.isEmpty()) {
            return Optional.of(ERROR_EMPTY_TASK);
        }

        if (Task.get(job.task.toLowerCase()) == null) {
            return Optional.of(ERROR_UNSUPPORTED_TASK);
        }

        if (job.payload == null || job.payload.isEmpty()) {
            return Optional.of(ERROR_EMPTY_PAYLOAD);
        }

        String[] args = new String[] {};
        try {
            args = job.payload.split(PAYLOAD_SEPARATOR);
        } catch (final Exception e) {
            return Optional.of(ERROR_PAYLOAD_NOT_COMMA_SEPARATED);
        }

        if (args.length <= 1) {
            return Optional.of(ERROR_PAYLOAD_NOT_COMMA_SEPARATED);
        }

        if (args.length > 2) {
            return Optional.of(ERROR_PAYLOAD_MORE_THAN_2_NUMBERS);
        }

        return Optional.empty();
    }
}
