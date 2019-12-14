
package domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;

import lombok.Value;

/**
 * Job.
 * <p>
 * This has the task to be executed and the payload needed to perform the task.
 * For ex: Json representation of a job to perform addition :
 * '{"task":"add", "payload":"1,2"}'
 */
@Value
@JsonDeserialize
public final class Job {

    /**
     * Task to execute.
     */
    public final String task;

    /**
     * Payload needed by the task.
     */
    public final String payload;

    /**
     * Job constructor.
     *
     * @param task
     *            task to execute
     * @param payload
     *            payload to be used by the task
     */
    @JsonCreator
    public Job(final String task, final String payload) {
        this.task = Preconditions.checkNotNull(task, "task cannot be null");
        this.payload = Preconditions.checkNotNull(payload, "payload cannot be null");
    }
}
