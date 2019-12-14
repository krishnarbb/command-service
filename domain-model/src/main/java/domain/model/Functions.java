
package domain.model;

import static domain.model.Constants.PAYLOAD_SEPARATOR;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Implementation of the various tasks of {@link TaskExecutor}
 */
public class Functions {
    /**
     * Function to perform addition.
     */
    public static TaskExecutor executeAddition = job -> {
        final String[] args = job.payload.split(PAYLOAD_SEPARATOR);
        final BigDecimal addResult = Arrays.stream(args)
                .map(arg -> new BigDecimal(arg))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new JobResult(job.task, job.payload, addResult.toString());
    };

    /**
     * Function to perform multiplication.
     */
    public static TaskExecutor executeMultiplication = job -> {
        final String[] args = job.payload.split(PAYLOAD_SEPARATOR);
        final BigDecimal addResult = Arrays.stream(args)
                .map(arg -> new BigDecimal(arg))
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
        return new JobResult(job.task, job.payload, addResult.toString());
    };

}
