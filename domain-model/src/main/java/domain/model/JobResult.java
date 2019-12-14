
package domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;

import lombok.Value;

/**
 * Result of performing the {@link Job}.
 * Ex. Json represenation of JobResult : {"task":"add","payload":"1,2","result":"3"}
 */
@Value
@JsonDeserialize
public final class JobResult {

    /**
     * Task executed.
     */
    public final String task;

    /**
     * Payload used by the task.
     */
    public final String payload;
    /**
     * Result of executing the task.
     */
    public final String result;

    /**
     * JobResult Constructor.
     *
     * @param task
     *            executed task
     * @param payload
     *            payload used by the task
     * @param result
     *            result of executing the task
     */
    @JsonCreator
    public JobResult(final String task, final String payload, final String result) {
        this.task = Preconditions.checkNotNull(task, "task");
        this.payload = Preconditions.checkNotNull(payload, "payload");
        this.result = Preconditions.checkNotNull(result, "result");
    }
}
